package com.yuier.yuni.dd.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

/**
 * @Title: YuniDdConfig
 * @Author yuier
 * @Package com.yuier.yuni.dd.config
 * @Date 2024/5/14 22:48
 * @description: dd 模块的配置类
 */
@EnableAsync
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.yuier.yuni.common")
public class YuniDdConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.deserializerByType(Number.class, new NumberToLongDeserializer());
        };
    }

    private static class NumberToLongDeserializer extends JsonDeserializer<Number> {
        @Override
        public Number deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (p.getCurrentToken().isNumeric()) {
                // Assuming all numbers should be deserialized as long
                return p.getLongValue();
            }
            // Fallback to default deserializer if not a number
            return (Number) ctxt.handleUnexpectedToken(Number.class, p);
        }
    }
}
