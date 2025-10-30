package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Clase encargada de ser un Objeto de Transferencia de Datos (DTO) para lo relacionado a la entrada de datos de productos
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
public class DtoProductIn {

	/**
     * Número Global de Artículo Comercial
	 * No puede tener un valor nulo.
     */
	@JsonProperty("gtin")
	@Pattern(regexp = "^\\+?\\d{13}$", message = "El gtin tiene un formato inválido")
	@NotNull(message="El gtin es obligatorio")
	private String gtin;
	
	/**
     * Nombre del producto
	 * No puede tener un valor nulo.
     */
	@JsonProperty("product")
	@NotNull(message="El product es obligatorio")
	private String product;
	
	/**
     * Descripción del producto
	 * No puede tener un valor nulo.
     */
	@JsonProperty("description")
	@NotNull(message="El description es obligatorio")
	private String description;
	
	/**
     * Precio del producto
	 * No puede tener un valor nulo.
	 * El valor no puede ser negativo
     */
	@JsonProperty("price")
	@Min(value = 0)
	@NotNull(message="El price es obligatorio")
	private Float price;
	
	/**
     * Cantidad restante de producto
	 * No puede tener un valor nulo.
     */
	@JsonProperty("stock")
	@NotNull(message="El stock es obligatorio")
	private Integer stock;

	/**
     * Identificador de la categoría
	 * No puede tener un valor nulo.
     */
	@JsonProperty("category_id")
	@NotNull(message="El category_id es obligatorio")
	private Integer category_id;

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
}
