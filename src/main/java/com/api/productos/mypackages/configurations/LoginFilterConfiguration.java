package com.api.productos.mypackages.configurations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.api.productos.mypackages.configurations.jwt.JwtUtilConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginFilterConfiguration extends AbstractAuthenticationProcessingFilter{

	  public LoginFilterConfiguration(String url, AuthenticationManager authManager) {
	        super(new AntPathRequestMatcher(url));
	        setAuthenticationManager(authManager);
	    }

	  @Override
	    public Authentication attemptAuthentication(
	            HttpServletRequest req, HttpServletResponse res)
	            throws AuthenticationException, IOException, ServletException {

	        InputStream body = req.getInputStream();

	        UsuarioConfiguration user = new ObjectMapper().readValue(body, UsuarioConfiguration.class);

	        return getAuthenticationManager().authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        user.getUsuario(),
	                        user.getContrasenia(),
	                        Collections.emptyList()
	                )
	        );
	    }
	  
	  @Override
	    protected void successfulAuthentication(
	            HttpServletRequest req,
	            HttpServletResponse res, FilterChain chain,
	            Authentication auth) throws IOException, ServletException {

	        JwtUtilConfiguration.addAuthentication(res, auth.getName());
	    }

}
