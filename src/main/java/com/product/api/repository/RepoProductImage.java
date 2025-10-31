package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.api.entity.ProductImage;

/**
 * Interfaz de repositorio para las imagenes de los productos en la DB
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
@Repository
public interface RepoProductImage extends JpaRepository<ProductImage, Integer> {

    /**
     * Obtiene todas las imagenes de un producto con id dado
     * Ordenadas por nombre de manera ascendente
     * @param id identificador del producto
     * @return Todas las imagenes de un producto con id dado, a manera de lista
     */
    List<ProductImage> findByProductIdOrderByProductAsc(Integer id);
}
