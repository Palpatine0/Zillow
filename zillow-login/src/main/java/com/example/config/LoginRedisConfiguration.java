package com.example.config;

import org.springframework.beans.factory.annotation.Configurable;
import com.example.cache.config.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configurable
public class LoginRedisConfiguration extends RedisConfiguration {
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        return super.redisTemplate(redisConnectionFactory);
    }

}
