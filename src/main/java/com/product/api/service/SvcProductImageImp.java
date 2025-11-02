package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProduct;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

/**
 * Clase de servicio para imagenes de productos.
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @beta
 */
@Service
public class SvcProductImageImp implements SvcProductImage {

    /**
	 * Repositorio para imagenes de producto
	 */
	@Autowired
	RepoProductImage repo;

    @Autowired
    RepoProduct repoProduct;

    /**
     * Ruta del directorio para almacenar imagenes de producto
     */
    @Value("${app.upload.dir}")
    private String uploadDir;

    /**
     * Obtiene la lista de imagenes de un producto dado
     * @param id identificador del producto
     * @return Una respuesta que indíca el éxito en la operación
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      Al codificar las imagenes
     *                      El identificador del producto
     */
    @Override
    public List<ProductImage> findAll(Integer id) {
        try {
            validateProductId(id);

            return repo.findByProductIdOrderByProductImageIdAsc(id);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    /**
     * Sube una imagen para un producto
     * @param in objeto de transferenica de una imagen para un producto
     * @return Una respuesta que indíca el éxito en la operación
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      Al guardar archivo
     */
	@Override
	public ApiResponse upload(DtoProductImageIn in) {
		try {
            ProductImage productImage = repo.findByProductId(in.getProduct_id());
            if(productImage == null)
                throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");

		    // Decodifica la cadena Base64 a bytes
            byte[] imageBytes = Base64.getDecoder().decode(in.getImage());

            // Genera un nombre único para la imagen (se asume extensión PNG)
            String fileName = UUID.randomUUID().toString() + ".png";

            // Construye la ruta completa donde se guardará la imagen
            Path imagePath = Paths.get(uploadDir, "img", "customer", fileName);

            // Asegurarse de que el directorio exista
            Files.createDirectories(imagePath.getParent());

            // Escribir el archivo en el sistema de archivos
            Files.write(imagePath, imageBytes);

            productImage = new ProductImage();
            productImage.setImage("/" + uploadDir + "/img/customer/" + fileName);
            productImage.setStatus(1); 

            // Guardar la ruta de la imagen
            repo.save(productImage);

            // Eliminar el prefijo "data:image/png;base64," si existe
            if (in.getImage().startsWith("data:image")) {
                int commaIndex = in.getImage().indexOf(",");
                if (commaIndex != -1)
                    in.setImage(in.getImage().substring(commaIndex + 1));
            }

            return new ApiResponse("La imagen del producto ha sido actualizada");
		} catch (DataAccessException e) {
            throw new DBAccessException(e);
		} catch (IOException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
        }
	}

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
    public ApiResponse delete(Integer id, Integer product_image_id) {
        try {
        validateProductImageId(product_image_id);
        validateProductId(id);
        validateRelation(id, product_image_id);

        repo.deleteById(product_image_id);
        return new ApiResponse("La imagen ha sido eliminada");
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    /**
     * Valida que el Identificador de la imagen de producto exista
     * @param id identificador de la imagen de producto a validar
     * @throws ApiException Si el identificador de la imagen de producto no está registrado
     */
    private void validateProductImageId(Integer id) {
        if (repo.findById(id).isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "El id de la imagen de producto no existe");
    }

    /**
     * Valida que el Identificador del producto exista
     * @param id identificador del producto a validar
     * @throws ApiException Si el identificador del producto no está registrado
     */
    private void validateProductId(Integer id) {
        if (repoProduct.findById(id).isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");
    }

    /**
     * Valida que el Identificador del producto exista
     * @param id identificador de la imagen de producto a validar
     * @throws ApiException Si el identificador del producto no está registrado
     */
    private void validateRelation(Integer id, Integer product_image_id) {
        if (repo.findById(product_image_id).get().getProduct_id() != id)
            throw new ApiException(HttpStatus.BAD_REQUEST, "La imagen no corresponde al producto buscado");
    }
}

