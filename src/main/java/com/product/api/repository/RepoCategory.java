package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

/**
 * Interfaz de repositorio para categorías en la DB
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.3.0
 * @beta
 */
@Repository
public interface RepoCategory extends JpaRepository<Category, Integer> {
    
    /**
     * Función que obtiene todas las categorías 
     */
    @Query(value = "SELECT * FROM category ORDER BY category", nativeQuery = true)
    List<Category> getCategories();

    /**
     * Función que obtiene todas las categorías activas
     */
    @Query(value = "SELECT * FROM category WHERE status=1 ORDER BY category", nativeQuery = true)
    List<Category> getActiveCategories();

}
