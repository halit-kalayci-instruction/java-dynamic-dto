package com.mia.dto.dynamic.controller;

import com.mia.dto.dynamic.dto.AddressDto;
import com.mia.dto.dynamic.dto.CityDto;
import com.mia.dto.dynamic.dto.UserDetailsDto;
import com.mia.dto.dynamic.service.RoleBasedFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/user", headers = "API-VERSION=1")
@RequiredArgsConstructor
public class UserController {
    private final RoleBasedFieldService roleBasedFieldService;


    @GetMapping(value="")
    @Cacheable(value="users", key = "#roleId",cacheManager = "cacheManager")
    public Map<String, Object> getDetails(@RequestParam Long roleId)
    {
        UserDetailsDto dto = new UserDetailsDto(1, "Halit","Kalaycı", "12345", new AddressDto(new CityDto("Ankara","06"),"abc"));

        return roleBasedFieldService.filterFields(dto, roleId);
    }
    @GetMapping(value="/get2")
    @Cacheable(value="users2", key = "#roleId",cacheManager = "cacheManager2")
    public Map<String, Object> getDetails2(@RequestParam Long roleId)
    {
        UserDetailsDto dto = new UserDetailsDto(1, "Halit","Kalaycı", "12345", new AddressDto(new CityDto("Ankara","06"),"abc"));

        return roleBasedFieldService.filterFields(dto, roleId);
    }
}

// /api/v1/users
// /api/v2/users

// /api/users?version=1

//