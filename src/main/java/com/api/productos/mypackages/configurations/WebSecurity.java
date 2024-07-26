package com.api.productos.mypackages.configurations;

import com.api.productos.mypackages.configurations.jwt.JwtFilterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.productos.mypackages.service.usuario.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	
	// ========= INYECCIÓN DE DEPENDENCIAS ==========
		@Autowired
		@Qualifier("UsuarioService")
		private UsuarioServiceImpl usuarioServiceImpl;

	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//Método para comprobar el user y password en la db
		auth.userDetailsService(usuarioServiceImpl);
		

		// Podemos Cargar el Usuario y Contraseña en Memoria sin usar la db
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");

		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/swagger-ui/**", "/v2/api-docs/**", "/swagger-resources/**", "/webjars/**", "/auth/login").permitAll()
				.anyRequest().authenticated()
				.and()
				// Las peticiones /auth/login pasaran previamente por este filtro
				.addFilterBefore(new LoginFilterConfiguration("/auth/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				// Las demás peticiones pasarán por este filtro para validar el token
				.addFilterBefore(new JwtFilterConfiguration(),
						UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
