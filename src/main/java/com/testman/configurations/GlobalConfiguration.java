package com.testman.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: kunitin
 * Created: 19/03/22
 * Info: Contains all configurations which are injected at application start
 **/

@Configuration
public class GlobalConfiguration {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
