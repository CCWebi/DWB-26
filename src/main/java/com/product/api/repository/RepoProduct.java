package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Product;

/**
 * Interfaz de repositorio para productos en la DB
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
@Repository
public interface RepoProduct extends JpaRepository<Product, Integer> {

}
