package io.github.wxj.novel;

import io.github.wxj.novel.core.config.CorsProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
@EnableCaching
@Slf4j
@MapperScan("io.github.wxj.novel.dao.mapper")
public class NovelApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context){
        return args -> {
            Map<String, CacheManager> beansOfType = context.getBeansOfType(CacheManager.class);
            log.info("spring 加载了以下缓存管理器:");
            beansOfType.forEach((k,v)->{
                log.info("{}:{}",k,v.getClass().getName());
                log.info("缓存：{}",v.getCacheNames());
            });
        };
    }
}
