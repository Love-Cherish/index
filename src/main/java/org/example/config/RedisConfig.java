package org.example.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 字符串序列化
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        // JSON序列化
        Jackson2JsonRedisSerializer<Object> jacksonSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jacksonSerializer.setObjectMapper(om);

        // key 和 hashkey 使用字符串序列化
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        // value 和 hashvalue 使用JSON序列化
        template.setValueSerializer(jacksonSerializer);
        template.setHashValueSerializer(jacksonSerializer);

        template.afterPropertiesSet();
        return template;
    }
}