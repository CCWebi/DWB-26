package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.api.dto.out.DtoProductOut;
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

	/**
	 * Obtiene un producto con su categoría mediante un INNER JOIN
	 * @param product_id identificador del producto
	 * @return Un objeto de transferencia con los datos del producto y su categoría
	 */
	@Query(value = "SELECT p.*, c.category "
			+ "FROM product p "
			+ "INNER JOIN category c ON c.category_id = p.category_id "
			+ "WHERE p.product_id = :product_id", nativeQuery = true)
	DtoProductOut getProduct(Integer product_id);
}