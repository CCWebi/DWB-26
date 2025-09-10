package com.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador para producto
 * @assignment Práctica 2
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version JDK 21 LTS
 * @docsTag
 */
@Tag(name = "Product", description = "Endpoints para productos")
@RestController
public class CtrlProduct {
    
    /**
     * Manejador de Categorías.
     */
    CategoryManager categoryManager = new CategoryManager();

    /**
     * Constructor de la clase
     * Vacío
     */
    public CtrlProduct() {}
    
    /**
     * Regresa la lista de categorías disponible
     * @return La lista de categorías disponible
     * @mapeo /category
     * @metodoHTTP GET
     * @estado 200 - Operación realizada con éxito
     */
    @Operation(summary = "Obtiene todas las categorías", description = "Regresa las categorías registradas")
    @ApiResponse(responseCode = "200", description = "Operación realizada con éxito", 
                    content = { @Content(
                        array = @ArraySchema(schema = @Schema(implementation = CategoryEntity.class)),
                        mediaType = "aplication/json")
                    })
    @GetMapping("/category")
    public CategoryEntity[] getCategories() {
    // public ResponseEntity<CategoryEntity[]> getCategories() {
        // Recurso no persistente
        categoryManager.createCategory(0, "Mexicana", "mx");
        categoryManager.createCategory(1, "Italiana", "it");
        categoryManager.createCategory(2, "Japonesa", "jp");
        categoryManager.createCategory(4, "Brasileña", "br");
        categoryManager.createCategory(5, "Turca", "tr");
        categoryManager.createCategory(3, "Americana", "us");

        return categoryManager.getCategoriesArray();
        //  return new ResponseEntity<>(categoryManager.getCategoriesArray(), HttpStatus.OK);
    }
}
