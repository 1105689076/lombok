package com.jiang.config;

import org.hibernate.type.ByteType;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return (configuration)->{
            //开启驼峰命名
            configuration.setMapUnderscoreToCamelCase(true);
        };
    }


}
