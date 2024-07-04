package com.mia.dto.dynamic.controller;

import com.mia.dto.dynamic.dto.AddressDto;
import com.mia.dto.dynamic.dto.CityDto;
import com.mia.dto.dynamic.dto.UserDetailsDto;
import com.mia.dto.dynamic.service.RoleBasedFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/user", headers = {"API-VERSION=2", "API-VERSION=3"})
@RequiredArgsConstructor
public class UserV2Controller {
    private final RoleBasedFieldService roleBasedFieldService;


    @GetMapping(value="")
    public String get2(@RequestParam String x) {
        return x;
    }
}

// /api/v1/users
// /api/v2/users

// /api/users?version=1

//