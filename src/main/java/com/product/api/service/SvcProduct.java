package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

/**
 * Interfaz dedicada a clases de servicio para acceso controlado de productos.
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
public interface SvcProduct {

	/**
     * Obtiene una respuesta con todos los productos
     * @return Una respuesta con todos los productos
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     */
	public ResponseEntity<List<DtoProductListOut>> getProducts();

	/**
	 * Obtiene una respuesta con los datos de un producto específico dado su identificador
	 * @param id identificador del producto
	 * @return Una respuesta con los datos de un producto específico dado su identificador
	 * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
	 * @throws ApiException Al encontrar un error en el contenido de:
	 * 						El identificador del producto
	 */
	public ResponseEntity<DtoProductOut> getProduct(Integer id);

	/**
     * Crea un producto
     * @return Una respuesta que indíca el éxito en la operación
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El GTIN del producto
     *                      El nombre del producto
	 * 						El identificador de la categoría asociado al producto
     */
	public ResponseEntity<ApiResponse> createProduct(DtoProductIn in);

	/**
     * Actualiza un producto
     * @return Una respuesta que indíca el éxito en la operación
	 * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
	 * 						El identificador del producto
     *                      Campo ya registrado en el GTIN del producto
     *                      Campo ya registrado en el nombre del producto
	 * 						El identificador de la categoría asociado al producto
     */
	public ResponseEntity<ApiResponse> updateProduct(Integer id, DtoProductIn in);

	/**
     * Habilita un producto
     * @return Una respuesta que indíca el éxito en la operación
	 * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El identificador del producto
     */
	public ResponseEntity<ApiResponse> enableProduct(Integer id);

	/**
     * Deshabilita un producto
     * @return Una respuesta que indíca el éxito en la operación
	 * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El identificador del producto
     */
	public ResponseEntity<ApiResponse> disableProduct(Integer id);

}
