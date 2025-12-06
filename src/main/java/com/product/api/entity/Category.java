package com.product.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Clase que representa una categoría como entidad
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.5.0
 * @beta
 */
@Entity
@Table(name = "category")
public class Category {

    /**
     * Identificador de la categoría
     */
    @JsonProperty("category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer category_id;

    /**
     * Nombre de la categoría
     */
    @JsonProperty("category")
    @Column(name = "category")
    private String category;
    
    /**
     * Etiqueta de la categoría
     */
    @JsonProperty("tag")
    @Column(name = "tag")
    private String tag;
    
    /**
     * Estado de la categoría
     */
    @JsonProperty("status")
    @Column(name = "status")
    private Integer status;

    /**
     * Constructor por defecto
     * @hidden
     */
    private Category(){}

    /**
     * Regresa el identificador de la categoría
     * @return El identificador de la categoría
     */
    public Integer getCategory_id() {
        return category_id;
    }

    /**
     * Regresa el nombre de la categoría
     * @return El nombre de la categoría
     */
    public String getCategory() {
        return category;
    }

    /**
     * Regresa la etiqueta de la categoría
     * @return La etiqueta de la categoría
     */
    public String getTag() {
        return tag;
    }

    /**
     * Regresa el estado de la categoría
     * @return El estado de la categoría
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Actualiza el identificador de la categoría
     * @param category_id identificador de la categoría
     */
    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    /**
     * Actualiza el nombre de la categoría
     * @param category nombre de la categoría
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Actualiza la etiqueta de la categoría
     * @param tag etiqueta de la categoría
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}
