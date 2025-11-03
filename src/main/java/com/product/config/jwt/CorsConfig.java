package com.product.config.jwt;

import java.util.Arrays;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Clase de configuración de las políticas de Intercambio de Recursos de Origen Cruzado (CORS).
 */
@Component
public class CorsConfig implements CorsConfigurationSource {

	/**
	 * Regresa la configuración establecida para las políticas de CORS
	 * @return La configuración establecida para las políticas de CORS
	 */
	@Override
	public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.PUT.name(), HttpMethod.PATCH.name()));
		corsConfiguration.addAllowedHeader("*");

		return corsConfiguration;
		
	}

}
