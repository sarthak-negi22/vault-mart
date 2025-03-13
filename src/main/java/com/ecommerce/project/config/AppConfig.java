package com.ecommerce.project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration      // used to define custom beans via @Bean
public class AppConfig {

    @Bean       // tells spring that the object returns by this method should be registered in the Spring Application Context
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
