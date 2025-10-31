package com.product.api.service;

import com.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

/**
 * Interfaz dedicada a clases de servicio para acceso controlado de imagenes de productos.
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
public interface SvcProductImage {

    /**
     * Sube una imagen para un producto
     * @param in objeto de transferenica de una imagen para un producto
     * @return Una respuesta que indíca el éxito en la operación
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      Al guardar archivo
     */
    public ApiResponse upload(DtoProductImageIn in);

    /**
     * Obtiene la lista de imagenes de un producto dado
     * @param id identificador del producto
     * @return Una respuesta que indíca el éxito en la operación
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      Al codificar las imagenes
     *                      El identificador del producto
     */
    public ApiResponse findAll(Integer id);

    /**
     * Elimina una imagen de un producto
     * @param id identificador del producto
     * @param product_image_id identificador de la imagen del producto
     * @return Una respuesta que indica el éxito en la operación
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El identificador del producto
     *                      El identificador de la imagen de producto
     *                      La ruta de la imagen
     */
    public ApiResponse delete(Integer id, Integer product_image_id);

}
