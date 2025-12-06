package com.product.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa un producto como entidad
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
@Entity
@Table(name = "product")
public class Product {
	
	/**
     * Identificador del producto
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer product_id;
	
	/**
     * Número Global de Artículo Comercial
     */
	@Column(name = "gtin")
	private String gtin;

	/**
     * Nombre del producto
     */
	@Column(name = "product")
	private String product;

	/**
     * Descripción del producto
     */
	@Column(name = "description")
	private String description;

	/**
     * Precio del producto
     */
	@Column(name = "price")
	private Float price;

	/**
     * Cantidad restante de producto
     */
	@Column(name = "stock")
	private Integer stock;

	/**
     * Identificador de la categoría
     */
	@Column(name = "category_id")
	private Integer category_id;

	/**
     * Estado del producto (desactivado 0 / activo 1)
     */
	@Column(name = "status")
	private Integer status;

	/**
	 * Regresa el identificador del producto
	 * @return El identificador del producto
	 */
	public Integer getProduct_id() {
		return product_id;
	}

	/**
	 * Actualiza el identificador del producto
	 * @param product_id identificador del producto
	 */
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	/**
	 * Regresa el Número Global de Artículo Comercial
	 * @return El Número Global de Artículo Comercial
	 */
	public String getGtin() {
		return gtin;
	}

	/**
	 * Actualiza el Número Global de Artículo Comercial
	 * @param gtin Número Global de Artículo Comercial
	 */
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	/**
	 * Regresa el nombre del producto
	 * @return El nombre del producto
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * Actualiza el nombre del producto
	 * @param product nombre del producto
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * Regresa la descripción del producto
	 * @return La descripción del producto
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Actualiza la descripción del producto
	 * @param description descripción del producto
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Regresa el precio del producto
	 * @return El precio del producto
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * Actualiza el precio de producto
	 * @param price precio de producto
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * Regresa la cantidad restante de producto
	 * @return La cantidad restante de producto
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * Actualiza la cantidad restante de producto
	 * @param stock cantidad restante de producto
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * Regresa el identificador de la categoría
	 * @return El identificador de la categoría
	 */
	public Integer getCategory_id() {
		return category_id;
	}

	/**
	 * Actualiza el identificador de la categoría
	 * @param category_id identificador de la categoría
	 */
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	/**
	 * Regresa el estado de la categoría
	 * @return El estado de la categoría
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Actualiza el estado de la categoría
	 * @param status estado de la categoría
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
