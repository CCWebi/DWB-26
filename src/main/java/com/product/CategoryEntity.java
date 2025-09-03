package com.product;

/**
 * Clase que representa una categoría.
 * @assignment Práctica 1
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version JDK 21 LTS
 * @beta
 */
public class CategoryEntity {

    /**
     * Identificador de la categoría
     */
    private Integer category_id;

    /**
     * Nombre de la categoría
     */
    private String category;

    /**
     * Etiqueta de la categoría
     */
    private String tag;

    /**
     * Estado de la categoría
     */
    private Integer status;

    /**
     * Constructor de la clase Category.
     * <code>status</code> por defecto es 1.
     * @param category_id Identificador de la categoría
     * @param category Nombre de la categoría
     * @param tag Etiqueta de la categoría
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    public CategoryEntity(Integer category_id, String category, String tag) {
        if (category_id == null || category_id < 0 || category == null || category.isEmpty() || 
            tag == null || tag.isEmpty())
            throw new IllegalArgumentException("Todos los parámetros deben ser no nulos y válidos.");

        this.category_id = category_id;
        this.category = category;
        this.tag = tag;
        this.status = 1;
    }

    /**
     * Regresa el identificador de la categoría
     * @return El identificador de la categoría
     */
    public Integer getCategoryId() {
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
     * Regresa el Estado de la categoría
     * @return El Estado de la categoría
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Establece el estado de la categoría.
     * @param status Nuevo estado (0 o 1)
     * @throws IllegalArgumentException si el estado es inválido
     */
    public void setStatus(Integer status) {
        if (status == null || status < 0 || status > 1) {
            throw new IllegalArgumentException("El estado debe ser 0 o 1.");
        }
        this.status = status;
    }

    /**
     * Regresa una representación en cadena de la categoría
     * @return Una representación en cadena de la categoría
     */
    @Override
    public String toString() {
        return "{" + category_id + ", \"" + category + "\", \"" + tag + "\", " + status + "}";
    }
}
