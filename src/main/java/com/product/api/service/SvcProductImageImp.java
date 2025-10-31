package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
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

    /**
     * Ruta del directorio para almacenar imagenes de producto
     */
    @Value("${app.upload.dir}")
    private String uploadDir;


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

            ProductImage productImage = new ProductImage();
            productImage.setProductImage_id(in.getProductImage_id());
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
}

