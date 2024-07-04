package com.mia.dto.dynamic.service;

import com.mia.dto.dynamic.entity.RoleDtoMapping;
import com.mia.dto.dynamic.repository.RoleDtoMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleBasedFieldService {
    private final RoleDtoMappingRepository roleDtoMappingRepository;

    public <T> Map<String, Object> filterFields(T dto, Long roleId) {
        if (dto == null) {
            return null;
        }

        RoleDtoMapping roleDtoMapping = roleDtoMappingRepository.findByDtoNameAndRoleId(dto.getClass().getSimpleName(), roleId)
                .orElseThrow();
        List<String> allowedFields = roleDtoMapping.getAllowedFields();

        return mapDtoFields(dto, allowedFields);
    }

    private <T> Map<String, Object> mapDtoFields(T dto, List<String> allowedFields) {
        if (dto == null) {
            return null;
        }

        Map<String, Object> filteredDto = new HashMap<>();
        Field[] fields = dto.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            List<String> nestedAllowedFields = allowedFields.stream()
                    .filter(f -> f.equals(fieldName) || f.startsWith(fieldName + "."))
                    .collect(Collectors.toList());

            if (!nestedAllowedFields.isEmpty()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(dto);
                    if (value != null && !isPrimitiveOrWrapper(value.getClass())) {
                        // Nested mapping
                        List<String> nestedFieldNames = nestedAllowedFields.stream()
                                .filter(i->i.contains("."))
                                .map(f -> f.substring(fieldName.length() + 1))
                                .collect(Collectors.toList());
                        Map<String, Object> nestedFilteredDto = mapDtoFields(value, nestedFieldNames);
                        filteredDto.put(fieldName, nestedFilteredDto);
                    } else if (nestedAllowedFields.contains(fieldName)) {
                        filteredDto.put(fieldName, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return filteredDto;
    }

    private boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return clazz.isPrimitive() ||
                clazz == Boolean.class ||
                clazz == Byte.class ||
                clazz == Character.class ||
                clazz == Double.class ||
                clazz == Float.class ||
                clazz == Integer.class ||
                clazz == Long.class ||
                clazz == Short.class ||
                clazz == String.class;
    }
}
