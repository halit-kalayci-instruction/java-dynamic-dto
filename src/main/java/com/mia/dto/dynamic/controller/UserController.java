package com.mia.dto.dynamic.controller;

import com.mia.dto.dynamic.dto.AddressDto;
import com.mia.dto.dynamic.dto.CityDto;
import com.mia.dto.dynamic.dto.UserDetailsDto;
import com.mia.dto.dynamic.service.RoleBasedFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final RoleBasedFieldService roleBasedFieldService;


    @GetMapping
    public Map<String, Object> getDetails(@RequestParam Long roleId)
    {
        UserDetailsDto dto = new UserDetailsDto(1, "Halit","Kalaycı", "12345", new AddressDto(new CityDto("Ankara","06"),"abc"));

        return roleBasedFieldService.filterFields(dto, roleId);
    }

}
