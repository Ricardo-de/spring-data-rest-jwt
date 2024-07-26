// src/main/java/com/api/productos/mypackages/controllers/AuthController.java
package com.api.productos.mypackages.controllers.autentication;

import com.api.productos.mypackages.configurations.jwt.JwtRequestInterceptor;
import com.api.productos.mypackages.configurations.jwt.JwtUtilConfiguration;
import com.api.productos.mypackages.models.request.login.LoginRequest;
import com.api.productos.mypackages.models.response.login.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsuario(),
                            loginRequest.getContrasenia()
                    )
            );
            JwtUtilConfiguration.addAuthentication(response, loginRequest.getUsuario());
            String token = response.getHeader("Authorization");
            ((JwtRequestInterceptor) restTemplate.getInterceptors().get(0)).setToken(token);
            return new LoginResponse("Login successful", token);
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new LoginResponse("Login failed", null);
        }
    }
}