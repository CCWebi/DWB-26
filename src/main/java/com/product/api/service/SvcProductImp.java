package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.commons.dto.ApiResponse;
import com.commons.mapper.MapperProduct;
import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.Product;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProduct;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

/**
 * Clase de servicio para productos.
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
@Service
public class SvcProductImp implements SvcProduct{

    private final RepoProductImage repoProductImage;

	/**
	 * Repositorio para productos
	 */
	@Autowired
	RepoProduct repo;
	
	/**
	 * Convertidor de estructuras especializado en productos
	 */
	@Autowired
	MapperProduct mapper;
	
	/**
	 * Ruta del directorio para almacenar imagenes
	 */
	@Value("${app.upload.dir}")
	private String uploadDir;

    SvcProductImp(RepoProductImage repoProductImage) {
        this.repoProductImage = repoProductImage;
    }

	/**
     * Obtiene una respuesta con todos los productos
     * @return Una respuesta con todos los productos
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     */
	@Override
	public ResponseEntity<List<DtoProductListOut>> getProducts() {
		try {
			List<Product> products = repo.findAll();
			return new ResponseEntity<>(mapper.fromProductList(products), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	/**
	 * Obtiene una respuesta con los datos de un producto específico dado su identificador
	 * @param id identificador del producto
	 * @return Una respuesta con los datos de un producto específico dado su identificador
	 * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
	 * @throws ApiException Al encontrar un error en el contenido de:
	 *                      El identificador del producto
	 */
	@Override
	public ResponseEntity<DtoProductOut> getProduct(Integer id) {
		try {
			DtoProductOut product = repo.getProduct(id);
			if(product == null)
				throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");
			
			String image = readProductImageFile(id);
			product.setImage(image);
			
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	/**
     * Crea un producto
     * @param in objeto de transferencia de un producto
     * @return Una respuesta que indíca el éxito en la operación
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El GTIN del producto
     *                      El nombre del producto
	 *                      El identificador de la categoría asociado al producto
     */
	@Override
	public ResponseEntity<ApiResponse> createProduct(DtoProductIn in) {
		try {
			Product product = mapper.fromDto(in);
			product = repo.save(product);
			
			ProductImage productImage = new ProductImage();
			productImage.setProduct_id(product.getProduct_id());
			productImage.setImage("");
			productImage.setStatus(1);
			repoProductImage.save(productImage);
			
			return new ResponseEntity<>(new ApiResponse("El producto ha sido registrado"), HttpStatus.CREATED);
		} catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_product_gtin"))
				throw new ApiException(HttpStatus.CONFLICT, "El gtin del producto ya está registrado");
			if (e.getLocalizedMessage().contains("ux_product_product"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre del producto ya está registrado");
			if (e.getLocalizedMessage().contains("fk_product_category"))
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de categoría no existe");

			throw new DBAccessException(e);
		}
	}

	/**
     * Actualiza un producto
     * @param in objeto de transferencia de una categoría
     * @param id identificador del producto
     * @return Una respuesta que indíca el éxito en la operación
	 * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
	 * 				        El identificador del producto
     *                      Campo ya registrado en el GTIN del producto
     *                      Campo ya registrado en el nombre del producto
	 * 				        El identificador de la categoría asociado al producto
     */
	@Override
	public ResponseEntity<ApiResponse> updateProduct(Integer id, DtoProductIn in) {
		try {
			validateProductId(id);
			Product product = mapper.fromDto(id, in);
			repo.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido actualizado"), HttpStatus.OK);
		} catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_product_gtin"))
				throw new ApiException(HttpStatus.CONFLICT, "El gtin del producto ya está registrado");
			if (e.getLocalizedMessage().contains("ux_product_product"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre del producto ya está registrado");
			if (e.getLocalizedMessage().contains("fk_product_category"))
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de categoría no existe");

			throw new DBAccessException(e);
		}
	}

	/**
     * Habilita un producto
     * @param id identificador del producto
     * @return Una respuesta que indíca el éxito en la operación
	 * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El identificador del producto
     */
	@Override
	public ResponseEntity<ApiResponse> enableProduct(Integer id) {
		try {
			validateProductId(id);
			Product product = repo.findById(id).get();
			product.setStatus(1);
			repo.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido activado"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	/**
     * Deshabilita un producto
     * @param id identificador del producto
     * @return Una respuesta que indíca el éxito en la operación
	 * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El identificador del producto
     */
	@Override
	public ResponseEntity<ApiResponse> disableProduct(Integer id) {
		try {
			validateProductId(id);
			Product product = repo.findById(id).get();
			product.setStatus(0);
			repo.save(product);
			return new ResponseEntity<>(new ApiResponse("El producto ha sido desactivado"), HttpStatus.OK);
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
     * Valida que el identificador del producto exista
     * @param id identificador de la categoría a validar
	 * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Si el identificador de la categoría no está registrado
     */
	private void validateProductId(Integer id) {
		try {
			if (repo.findById(id).isEmpty())
				throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
	/**
	 * Lee la imagen de un producto y la convierte a Base64
	 * @param product_id identificador del producto
	 * @return La imagen del producto en formato Base64, o cadena vacía si no existe
	 * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
	 * @throws ApiException Al encontrar un error al leer el archivo
	 */
	private String readProductImageFile(Integer product_id) {
		try {
			ProductImage productImage = repoProductImage.findByProductId(product_id);
			if(productImage == null)
				return "";
			
			String imageUrl = productImage.getImage();
			
			// Si la URL comienza con "/" la eliminamos para obtener la ruta relativa
			if (imageUrl.startsWith("/")) {
				imageUrl = imageUrl.substring(1);
			}
			
			// Construir el Path
			Path imagePath = Paths.get(uploadDir, imageUrl);
			
			// Verifica que el archivo exista
			if (!Files.exists(imagePath))
				return "";
			
			// Leer los bytes de la imagen y codificarlos a Base64
			byte[] imageBytes = Files.readAllBytes(imagePath);
			return Base64.getEncoder().encodeToString(imageBytes);
			
		} catch (DataAccessException e) {
			throw new DBAccessException(e);
		} catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al leer el archivo");
		}
	}

}
