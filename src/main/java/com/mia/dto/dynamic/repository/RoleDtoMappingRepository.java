package com.mia.dto.dynamic.repository;

import com.mia.dto.dynamic.entity.RoleDtoMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDtoMappingRepository extends JpaRepository<RoleDtoMapping,Long> {
    Optional<RoleDtoMapping> findByDtoNameAndRoleId(String dtoName, Long roleId);
}
