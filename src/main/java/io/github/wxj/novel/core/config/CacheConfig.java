package io.github.wxj.novel.core.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.wxj.novel.core.constant.CacheConsts;
import org.checkerframework.checker.units.qual.C;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class CacheConfig {

    @Bean
    @Primary
    public CacheManager cacheManager(){
        SimpleCacheManager simpleCacheManager  = new SimpleCacheManager();

        List<CaffeineCache> caches = new ArrayList<>(CacheConsts.CacheEnum.values().length);

        for (CacheConsts.CacheEnum value : CacheConsts.CacheEnum.values()) {
          //
            if(value.isLocal()){
                Caffeine<Object,Object>  caffeine = Caffeine.newBuilder().recordStats().maximumSize(value.getMaxSize());
                if(value.getTtl()>0){
                    caffeine.expireAfterWrite(Duration.ofSeconds(value.getTtl()));
                }
                caches.add(new CaffeineCache(value.getName(),caffeine.build()));
            }
        }
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }

    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory factory){
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(factory);

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues().prefixCacheNameWith(CacheConsts.REDIS_CACHE_PREFIX);

        Map<String, RedisCacheConfiguration> cacheMap = new LinkedHashMap<>(CacheConsts.CacheEnum.values().length);
        for (CacheConsts.CacheEnum c : CacheConsts.CacheEnum.values()) {
            if (c.isRemote()) {
                if (c.getTtl() > 0) {
                    cacheMap.put(c.getName(), RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
                            .prefixCacheNameWith(CacheConsts.REDIS_CACHE_PREFIX).entryTtl(Duration.ofSeconds(c.getTtl())));
                } else {
                    cacheMap.put(c.getName(), RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
                            .prefixCacheNameWith(CacheConsts.REDIS_CACHE_PREFIX));
                }
            }
        }
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig, cacheMap);
        redisCacheManager.setTransactionAware(true);
        redisCacheManager.initializeCaches();
        return redisCacheManager;
    }
}
