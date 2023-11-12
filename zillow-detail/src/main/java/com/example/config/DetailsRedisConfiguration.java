package com.example.config;

import org.springframework.beans.factory.annotation.Configurable;
import com.example.cache.config.RedisConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configurable
public class DetailsRedisConfiguration extends RedisConfiguration {
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        return super.cacheManager(redisConnectionFactory);
    }
}
