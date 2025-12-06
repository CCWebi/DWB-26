package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

import jakarta.transaction.Transactional;

/**
 * Interfaz de repositorio para categorías en la DB
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.5.0
 * @beta
 */
@Repository
public interface RepoCategory extends JpaRepository<Category, Integer> {

    /**
     * Obtiene todas las categorías
     * @return Todas las categorías, a manera de lista
     */
    @Query(value = "SELECT * FROM category ORDER BY category", nativeQuery = true)
    List<Category> findAll();

    /**
     * Obtiene todas las categorías con el estado dado
     * Ordenadas por nombre de manera ascendente
     * @param status Estado de las categorías
     * @return Todas las categorías con el estado dado, a manera de lista
     */
    List<Category> findByStatusOrderByCategoryAsc(Integer status);

    /**
     * Registra una categoría
     * @param category Nombre de la categoría
     * @param tag Etiqueta de la categoría
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = "INSERT INTO category(category, tag, status) VALUES (:category, :tag, 1)", nativeQuery = true)
    void create(@Param("category") String category, @Param("tag") String tag);

    /**
     * Actualiza una categoría
     * @param category_id Identificador de la categoría
     * @param category Nombre de la categoría
     * @param tag Etiqueta de la categoría
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value ="UPDATE category SET category = :category, tag = :tag WHERE category_id = :category_id", nativeQuery = true)
	void update(@Param("category_id") Integer category_id, @Param("category") String category, @Param("tag") String tag);

    /**
     * Actualiza el estado una categoría
     * @param category_id Identificador de la categoría
     * @param status Estado de la categoría
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value ="UPDATE category SET status = :status WHERE category_id = :category_id", nativeQuery = true)
    void updateStatus(@Param("category_id") Integer category_id, @Param("status") Integer status);


}
