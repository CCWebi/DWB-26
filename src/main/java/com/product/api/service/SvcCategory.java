package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.entity.Category;
import com.product.api.dto.in.DtoCategoryIn;
import com.commons.dto.ApiResponse;

/**
 * Interfaz dedicvada a clases de servicio para acceso controlado.
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.5.0
 * @beta
 */
public interface SvcCategory {
    
    /**
     * Obtiene todas las categorías
     */
    public List<Category> findAll();
    
    /**
     * Obtiene todas las categorías activas
     */
    public List<Category> findActive();
    
    /**
     * Crea una categoría
     */
    public ApiResponse create(DtoCategoryIn in);
    
    /**
     * Actualiza una categoría
     */
    public ApiResponse update(DtoCategoryIn in, Integer id);
    
    /**
     * Habilita una categoría
     */
    public ApiResponse enable(Integer id);
    
    /**
     * Deshabilita una categoría
     */
    public ApiResponse disable(Integer id);

}
