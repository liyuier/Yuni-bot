package com.yuier.yuni.core.config;

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
 * @Title: AopConfig
 * @Author yuier
 * @Package com.yuier.yuni.core.config
 * @Date 2024/4/10 0:31
 * @description: AOP配置类
 */

@EnableAsync
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.yuier.yuni.common")
public class YuniCoreConfig {
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
