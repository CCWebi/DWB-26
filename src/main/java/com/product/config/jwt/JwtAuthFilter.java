package com.product.config.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Clase dedicada a interceptar tókens y procesarlos, guardando información de manera local
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    /**
     * Utilidades para tókens JWT
     */
	private final JwtUtil jwtUtil;

    /**
     * Constructor de la clase
     * @param jwtUtil utilidades para tókens
     */
    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Filtrado de tókens
     * @param request información de la petición hecha
     * @param response información de la respuesta brindada
     * @param chain encadenado de filtros 
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        List<HashMap<String, String>> permisos = jwtUtil.extractPermisos(token);
        
        List<String> permisosList = permisos.stream().map(i -> i.get("authority")).toList();
        

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = User.withUsername(username)
                    .password("")
                    .authorities(permisosList.toArray(new String[0]))
                    .build();

            UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        chain.doFilter(request, response);
    }
}