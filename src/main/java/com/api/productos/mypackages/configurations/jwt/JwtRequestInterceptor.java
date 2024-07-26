// src/main/java/com/api/productos/mypackages/configurations/JwtRequestInterceptor.java
package com.api.productos.mypackages.configurations.jwt;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class JwtRequestInterceptor implements ClientHttpRequestInterceptor {

    private String token;

    public JwtRequestInterceptor(String token) {
        this.token = token;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("Authorization", token);
        return execution.execute(request, body);
    }

    public void setToken(String token) {
        this.token = token;
    }
}