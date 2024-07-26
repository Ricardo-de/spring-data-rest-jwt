// src/main/java/com/api/productos/mypackages/configurations/RestTemplateConfig.java
package com.api.productos.mypackages.configurations;

import com.api.productos.mypackages.configurations.jwt.JwtRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new JwtRequestInterceptor("Bearer your-jwt-token"));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}