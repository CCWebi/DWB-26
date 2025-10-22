package com.product.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.product.config.jwt.CorsConfig;
import com.product.config.jwt.JwtAuthFilter;

/**
 * Clase de configuración de seguridad para acceso y permisos a endpoints
 */
@Configuration
public class SecurityConfig {

	/**
	 * Interceptor de JWT's
	 */
	@Autowired
	private JwtAuthFilter jwtFilter;
	
	/**
	 * Construcción con filtrado de seguridad
	 * @param http Constructor de webs con seguridad
	 * @param corsConfig configuración de intercambio de rescursos 
	 * @return Construcción web con la seguridad definida
	 * @throws Exception Ante errores de construcción
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfig corsConfig) throws Exception {
	
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(
				auth -> auth
                // Docs and extra
				.requestMatchers("/error", "/swagger-ui/**", "/v3/api-docs/**", "/api-docs", "/documentation.html", "/actuator/info", "/actuator/health").permitAll()
                // Category
				.requestMatchers(HttpMethod.GET, "/category/active").hasAnyAuthority("ADMIN", "CUSTOMER")
				.requestMatchers("/category/**").hasAuthority("ADMIN")
                // Customer
                .requestMatchers(HttpMethod.GET, "/customer/*").hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers("/customer/**").hasAuthority("ADMIN")
                // Customer images
                .requestMatchers("/customer-image/**").hasAnyAuthority("ADMIN", "CUSTOMER")
		)
		.cors(cors -> cors.configurationSource(corsConfig))
		.httpBasic(AbstractHttpConfigurer::disable)
		.formLogin(form -> form.disable())
		.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}
}
