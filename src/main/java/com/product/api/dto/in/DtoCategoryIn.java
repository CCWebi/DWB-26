package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

/**
 * Clase encargada de ser un Objeto de Transferencia de Datos (DTO) para lo relacionado a categorías
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.5.0
 * @beta
 */
public class DtoCategoryIn {

    /**
     * Nombre de la categoría
     * No puede tener un valor nulo.
     */
    @JsonProperty("category")
    @NotNull(message="El nombre de la categoría es obligatoria")
    private String category;

    /**
     * Etiqueta de la categoría
     * No puede tener un valor nulo.
     */
    @JsonProperty("tag")
    @NotNull(message="El tag es obligatorio")
    private String tag;

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
     * Actualiza el nombre de la categoría
     * @param category Nombre de la categoría
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Actualiza la etiqueta de la categoría
     * @param tag Etiqueta de la categoría
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

}