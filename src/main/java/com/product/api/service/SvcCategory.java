package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.entity.Category;

/**
 * Interfaz dedicvada a clases de servicio para acceso controlado.
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.4.0
 * @beta
 */
public interface SvcCategory {
    /**
     * Función que obtiene todas las categorías
     */
    public ResponseEntity<List<Category>> getCategories();

    /**
     * Función que obtiene todas las categorías activas
     */
    public ResponseEntity<List<Category>> getActiveCategories();
}
