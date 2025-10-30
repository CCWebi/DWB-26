package com.product.api.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase encargada de ser un Objeto de Transferencia de Datos (DTO) para lo relacionado a la salida de datos para lista de productos
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
public class DtoProductListOut {
	
	/**
     * Identificador del producto
     */
	@JsonProperty("product_id")
	private Integer product_id;
	
	/**
     * Número Global de Artículo Comercial
     */
	@JsonProperty("gtin")
	private String gtin;

	/**
     * Nombre del producto
     */
	@JsonProperty("product")
	private String product;

	/**
     * Precio del producto
     */
	@JsonProperty("price")
	private Float price;

	/**
     * Estado del producto (desactivado 0 / activo 1)
     */
	@JsonProperty("status")
	private Integer status;

	/**
	 * Constructor de la clase
	 * @param product_id identificador del producto
	 * @param gtin Número Global de Artículo Comercial
	 * @param product nombre del producto
	 * @param price precio del producto
	 * @param status estado del producto
	 */
	public DtoProductListOut(Integer product_id, String gtin, String product, Float price, Integer status) {
		super();
		this.product_id = product_id;
		this.gtin = gtin;
		this.product = product;
		this.price = price;
		this.status = status;
	}

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
