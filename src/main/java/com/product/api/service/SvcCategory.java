package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.entity.Category;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;
import com.product.api.dto.in.DtoCategoryIn;
import com.commons.dto.ApiResponse;

/**
 * Interfaz dedicada a clases de servicio para acceso controlado de categorías.
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.5.0
 * @beta
 */
public interface SvcCategory {
    
    /**
     * Obtiene todas las categorías
     * @return Una lista con todas categorías
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     */
    public List<Category> findAll();
    
    /**
     * Obtiene todas las categorías activas
     * @return Una lista con las categorías activas
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     */
    public List<Category> findActive();
    
    /**
     * Crea una categoría
     * @param in objeto de transferencia de una categoría
     * @return Una respuesta que indíca el éxito en la operación
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El nombre de la categoría
     *                      La etiqueta de la categoría
     */
    public ApiResponse create(DtoCategoryIn in);
    
    /**
     * Actualiza una categoría
     * @param in objeto de transferencia de una categoría
     * @param id identificador de la categoría buscada
     * @return Una respuesta que indíca el éxito en la operación
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El identificador de la categoría
     *                      Campo ya registrado en el nombre de la categoría
     *                      Campo ya registrado en la etiqueta de la categoría
     */
    public ApiResponse update(DtoCategoryIn in, Integer id);
    
    /**
     * Habilita una categoría
     * @param id identificador de la categoría buscada
     * @return Una respuesta que indíca el éxito en la operación
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El identificador de la categoría
     */
    public ApiResponse enable(Integer id);
    
    /**
     * Deshabilita una categoría
     * @param id identificador de la categoría buscada
     * @return Una respuesta que indíca el éxito o error en la operación
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El identificador de la categoría
     */
    public ApiResponse disable(Integer id);

}
