package com.mia.dto.dynamic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String,Object> redisTemplate;


    public void save(String key, Object value)
    {
        redisTemplate.opsForValue().set(key,value);
    }
    public Object find(String key)
    {
        return redisTemplate.opsForValue().get(key);
    }
    public void delete(String key)
    {
        redisTemplate.delete(key);
    }
    // GetCarById
    // GetCarList

    // Cars
    public void saveWithExpiration(String key, Object value, long timeout, TimeUnit unit)
    {
        redisTemplate.opsForValue().set(key,value,timeout,unit);
    }

    public <T> T get(String key, Class<T> type)
    {
        Object value = redisTemplate.opsForValue().get(key);
        if(value != null && type.isInstance(value))
        {
            return type.cast(value);
        }
        return null;
    }
}
