package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
/**
 * Clase encargada de ser un Objeto de Transferencia de Datos (DTO) para lo relacionado a la entrada de datos de las imagenes de un producto
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
public class DtoProductImageIn {

    /**
     * Identificador de la imagen de un producto
     */
    @JsonProperty("product_id")
	@NotNull(message="El identificador de producto es obligatorio")
    private Integer productId;

    /**
     * Imagen de producto
     */
    @JsonProperty("image")
    @NotNull(message="La imagen es obligatoria")
    private String image;

    /**
     * Regresa un identificador del producto
     * @return un identificador del producto
     */
    public Integer getProduct_id() {
        return productId;
    }

    /**
     * Actualiza el identificador del producto
     * @param productId identificador del producto
     */
    public void setProductImage_id(Integer productId) {
        this.productId = productId;
    }

    /**
     * Regresa la imagen de producto
     * @return la imagen de producto
     */
    public String getImage() {
        return image;
    }

    /**
     * Actualiza la imagen de producto
     * @param image imagen de producto
     */
    public void setImage(String image) {
        this.image = image;
    }
}
