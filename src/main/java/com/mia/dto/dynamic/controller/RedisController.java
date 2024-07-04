package com.mia.dto.dynamic.controller;

import com.mia.dto.dynamic.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "api/redis", headers = "API-VERSION=1")
@RestController
@RequiredArgsConstructor
public class RedisController {
    private final RedisService redisService;


    @PostMapping
    public void set(@RequestParam String key,  @RequestParam String obj)
    {
        redisService.save(key,obj);
    }
    @GetMapping
    public Object get(@RequestParam  String key)
    {
        return redisService.find(key);
    }
}
