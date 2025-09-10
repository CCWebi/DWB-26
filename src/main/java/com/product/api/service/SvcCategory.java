package com.product.api.service;

import java.util.List;

import com.product.api.entity.Category;

/**
 * Interfaz dedicvada a clases de servicio para acceso controlado.
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.3.0
 * @beta
 */
public interface SvcCategory {
    /**
     * Función que obtiene todas las categorías 
     */
    List<Category> getCategories();

    /**
     * Función que obtiene todas las categorías activas
     */
    List<Category> getActiveCategories();
}
