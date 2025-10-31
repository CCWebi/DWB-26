package com.product.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa una imagen de un producto como entidad
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
@Entity
@Table(name = "product_image")
public class ProductImage {
    
    /**
     * Identificador de la imagen de un producto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Integer productImageId;

    /**
     * Identificador del producto
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * Imagen de producto
     */
    @Column(name = "image")
    private String image;

    /**
     * Estado de la imagen
     */
    @Column(name = "status")
    private Integer status;
    
    /**
     * Regresa un identificador de la imagen del producto
     * @return un identificador de la imagen del producto
     */
    public Integer getProductImage_id() {
        return productImageId;
    }

    /**
     * Regresa el identificador del producto
     * @return el identificador del producto
     */
    public Integer getProduct_id() {
        return productId;
    }

    /**
     * Regresa la imagen de producto
     * @return la imagen de producto
     */
    public String getImage() {
        return image;
    }

    /**
     * Regresa el estado de la imagen de producto
     * @return el estado de la imagen de producto
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Actualiza el identificador de la imagen de producto
     * @param productImageId identificador de la imagen de producto
     */
    public void setProductImage_id(Integer productImageId) {
        this.productImageId = productImageId;
    }

    /**
     * Actualiza el identificador del producto
     * @param productImageId identificador del producto
     */
    public void setProduct_id(Integer productId) {
        this.productId = productId;
    }

    /**
     * Actualiza la imagen de producto
     * @param productImageId imagen de producto
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Actualiza el estado de la imagen de producto
     * @param productImageId estado de la imagen de producto
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
