package org.example.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    // 存值
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 存值（带过期时间，秒）
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    // 取值
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}
