package com.product.api.service;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.DBAccessException;

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
 * @version 0.4.0
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
     * Constructor de cadenas mutables.
     * Ayuda con la eficiencia al escribir varios caracteres
     */
    private StringBuilder sb;

    /**
     * Constructor del gestor de categorías
     */
    public SvcCategoryImp() {
        this.sb = new StringBuilder();
    }

    /**
     * Imprime las categorías activas (status 1).
     * Si no hay categorías activas, muestra un mensaje indicando que no existen
     */
    public void printActiveCategories() {
        sb.setLength(0);
        List<Category> activeCategories = getActiveCategories().getBody();
        
        if (activeCategories.isEmpty())
            System.out.println("No existen categorías registradas");
        else {
            sb.append("[");
            for (int i = 0; i < activeCategories.size(); i++) {
                sb.append(activeCategories.get(i).toString());
                if (i < activeCategories.size() - 1)
                    sb.append(", ");
            }
            sb.append("]");
            System.out.println(sb.toString());
        }
    }

    /**
     * Regresa una respuesta con la lista con todas categorías
     * @return Una respuesta con la lista con todas categorías
     * @estado 200 - Operación realizada con éxito
     */
    @Override
    public ResponseEntity<List<Category>> getCategories() {
        try {
            return new ResponseEntity<List<Category>>(repo.getCategories(), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }
    
    /**
     * Regresa una respuesta con la lista con las categorías activas
     * @return Una respuesta con la lista con las categorías activas
     * @estado 200 - Operación realizada con éxito
     */
    @Override
    public ResponseEntity<List<Category>> getActiveCategories() {
        try {
            return new ResponseEntity<List<Category>>(repo.getActiveCategories(), HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new DBAccessException(e);
        }
    }

    /**
     * Regresa un arreglo con todas categorías
     * @return Un arreglo con todas categorías
     */
    public Category[] getCategoriesArray() {
        return getCategories().getBody().toArray(new Category[0]);
    }

    /**
     * Regresa un arreglo con las categorías activas
     * @return Un arreglo con las categorías activas
     */
    public Category[] getActiveCategoriesArray() {
        return getActiveCategories().getBody().toArray(new Category[0]);
    }

    /**
     * Crea una Category.
     * El atributo <code>status</code>, por defecto es 1
     * @param category_id Identificador de la categoría
     * @param category Nombre de la categoría
     * @param tag Etiqueta de la categoría
     * @return <code>true</code> si fue creado con éxito, <code>false</code> si ocurrió un error
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    public boolean createCategory(Integer category_id, String category, String tag) {
        return createCategory(new Category(category_id, category, tag, 1));
    }

    /**
     * Crea una nueva categoría
     * @param newCategory La nueva categoría a crear
     * @return true si la categoría fue creada exitosamente, false si ya existe o es nula
     */
    private boolean createCategory(Category newCategory) {
        if (newCategory == null) {
            return false;
        }
        // Verificar unicidad de category_id, category y tag
        for (Category existing : getCategories().getBody()) {
            if (existing.getCategoryId().equals(newCategory.getCategoryId()) ||
                existing.getCategory().equalsIgnoreCase(newCategory.getCategory()) ||
                existing.getTag().equalsIgnoreCase(newCategory.getTag())) {
                return false;
            }
        }
        getCategories().getBody().add(newCategory);
        return true;
    }

    /**
     * Elimina una categoría por su ID
     * @param categoryId El ID de la categoría a eliminar
     * @return true si la categoría fue eliminada exitosamente, false si no existe o el ID es nulo
     */
    public boolean deleteCategory(Integer categoryId) {
        if (categoryId == null)
            return false;
        for (Category category : getCategories().getBody()) {
            if (category.getCategoryId().equals(categoryId)) {
                if (category.getStatus() == 0)
                    throw new IllegalStateException("El intento es fallido, pues la categoría está inactiva");
                category.setStatus(0);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si un ID es único
     * @param id El ID a verificar
     * @return true si es único, false si ya existe
     */
    private boolean isIdUnique(Integer id) {
        for (Category category : getCategories().getBody()) {
            if (category.getCategoryId().equals(id))
                return false;
        }
        return true;
    }

    /**
     * Verifica si un nombre de categoría es único
     * @param category El nombre a verificar
     * @return true si es único, false si ya existe
     */
    private boolean isCategoryUnique(String category) {
        for (Category existing : getCategories().getBody()) {
            if (existing.getCategory().equalsIgnoreCase(category))
                return false;
        }
        return true;
    }

    /**
     * Verifica si un tag es único
     * @param tag El tag a verificar
     * @return true si es único, false si ya existe
     */
    private boolean isTagUnique(String tag) {
        for (Category existing : getCategories().getBody()) {
            if (existing.getTag().equalsIgnoreCase(tag))
                return false;
        }
        return true;
    }
}