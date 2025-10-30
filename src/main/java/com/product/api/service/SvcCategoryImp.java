package com.product.api.service;

import com.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.DBAccessException;
import com.product.exception.ApiException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Clase de servicio para categorías.
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.5.0
 * @beta
 */
@Service
public class SvcCategoryImp implements SvcCategory {

    /**
     * Repositorio para categorias
     */
    @Autowired
    private RepoCategory repo;

    /**
     * Obtiene todas las categorías
     * @return Una lista con todas categorías
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     */
    @Override
    public List<Category> findAll() {
        try {
            return repo.findAll();
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
    
    /**
     * Obtiene todas las categorías activas
     * @return Una lista con las categorías activas
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     */
    @Override
    public List<Category> findActive() {
        try {
            return repo.findByStatusOrderByCategoryAsc(1);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
    
    /**
     * Crea una categoría
     * @return Una respuesta que indíca el éxito en la operación
     * @throws DBAccessException Al encontrar un error sobre la capa de persistencia
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El nombre de la categoría
     *                      La etiqueta de la categoría
     */
    public ApiResponse create(DtoCategoryIn in) {
        try {
            repo.create(in.getCategory(), in.getTag());
            return new ApiResponse("La categoría ha sido registrada");
        } catch (DataAccessException e) {
            if (e.getLocalizedMessage().contains("ux_category"))
            throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoría ya está registrado");
            if (e.getLocalizedMessage().contains("ux_tag"))
            throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoría ya está registrado");
            throw new DBAccessException(e);
        }
    }
    
    /**
     * Actualiza una categoría
     * @return Una respuesta que indíca el éxito en la operación
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El identificador de la categoría
     *                      Campo ya registrado en el nombre de la categoría
     *                      Campo ya registrado en la etiqueta de la categoría
     */
    public ApiResponse update(DtoCategoryIn in, Integer id) {
        try {
            validateCategoryId(id);
            repo.update(id, in.getCategory(), in.getTag());
            return new ApiResponse("La categoría ha sido actualizada");
        } catch (DataAccessException e) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoría no existe");
        }
    }
    
    /**
     * Habilita una categoría
     * @return Una respuesta que indíca el éxito en la operación
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El identificador de la categoría
     */
    public ApiResponse enable(Integer id) {
        try {
            validateCategoryId(id);
            repo.updateStatus(id, 1);
            return new ApiResponse("La categoría ha sido activada");
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
    
    /**
     * Deshabilita una categoría
     * @return Una respuesta que indíca el éxito o error en la operación
     * @throws ApiException Al encontrar un error en el contenido de:
     *                      El identificador de la categoría
     */
    public ApiResponse disable(Integer id) {
        try {
            validateCategoryId(id);
            repo.updateStatus(id, 0);
            return new ApiResponse("La categoría ha sido desactivada");
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    /**
     * Valida que el Identificador de la categoría exista
     * @param id identificador de la categoría a validar
     * @throws ApiException Si el identificador de la categoría no está registrado
     */
    private void validateCategoryId(Integer id) {
        if (repo.findById(id).isEmpty())
            throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoría no existe");
    }
}