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
    @JsonProperty("product_image_id")
	@NotNull(message="El identificador de la imagen de producto es obligatorio")
    private Integer productImageId;

    /**
     * Imagen de producto
     */
    @JsonProperty("image")
    @NotNull(message="La imagen es obligatoria")
    private String image;

    /**
     * Regresa un identificador de la imagen del producto
     * @return un identificador de la imagen del producto
     */
    public Integer getProductImage_id() {
        return productImageId;
    }

    /**
     * Actualiza el identificador de la imagen de producto
     * @param productImageId identificador de la imagen de producto
     */
    public void setProductImage_id(Integer productImageId) {
        this.productImageId = productImageId;
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
     * @param productImageId imagen de producto
     */
    public void setImage(String image) {
        this.image = image;
    }
}
