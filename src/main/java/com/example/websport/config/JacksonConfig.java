package com.example.websport.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Configure Page serialization
        objectMapper.registerModule(new com.fasterxml.jackson.databind.module.SimpleModule() {
            {
                addDeserializer(PageImpl.class, new com.fasterxml.jackson.databind.JsonDeserializer<PageImpl>() {
                    @Override
                    public PageImpl deserialize(com.fasterxml.jackson.core.JsonParser p,
                            com.fasterxml.jackson.databind.DeserializationContext ctxt) {
                        return new PageImpl<>(java.util.Collections.emptyList(),
                                PageRequest.of(0, 10), 0);
                    }
                });
            }
        });

        return objectMapper;
    }
}