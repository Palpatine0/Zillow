package com.example.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import com.example.cache.config.RedisConfiguration;

@Configurable
public class DetailsConfiguration extends RedisConfiguration {
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        return super.cacheManager(redisConnectionFactory);
    }
}
