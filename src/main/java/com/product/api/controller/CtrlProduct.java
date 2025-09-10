package com.product.api.controller;

import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.3.0
 * @docsTag
 * @beta
 */
@Tag(name = "Product", description = "Endpoints para productos")
@RestController
public class CtrlProduct {
    
    /**
     * Clase de servicio para categorías.
     */
    @Autowired
    SvcCategory svc;

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
                        array = @ArraySchema(schema = @Schema(implementation = Category.class)),
                        mediaType = "aplication/json")
                    })
    @GetMapping("/category")
    public List<Category> getCategories() {
        return svc.getActiveCategories();
    }
}
