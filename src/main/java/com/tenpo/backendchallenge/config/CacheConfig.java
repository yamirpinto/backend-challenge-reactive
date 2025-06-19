package com.tenpo.backendchallenge.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public Cache<String, Double> percentageCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();
    }
}
