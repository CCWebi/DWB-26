package com.product.api.controller;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.commons.dto.ApiResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

/**
 * Controlador para producto
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.5.0
 * @docsTag
 * @beta
 */
@Tag(name = "Product", description = "Endpoints para productos")
@RestController
@RequestMapping("/category")
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
     * @estado 400 - Operación fallida
     */
    @Operation(summary = "Obtiene todas las categorías", description = "Regresa las categorías registradas")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operación realizada con éxito", 
            content = { @Content(
                    array = @ArraySchema(schema = @Schema(implementation = Category.class)),
                    mediaType = "aplication/json"
            )}
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error de conexión")
    })
    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(svc.findAll());
    }

    /**
     * Regresa la lista de categorías disponible
     * @return La lista de categorías disponible
     * @mapeo /category/active
     * @metodoHTTP GET
     * @estado 200 - Operación realizada con éxito
     * @estado 400 - Operación fallida
     */
    @Operation(summary = "Obtiene todas las categorías activas", description = "Regresa las categorías registradas con estado activo (1)")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operación realizada con éxito", 
            content = { @Content(
                    array = @ArraySchema(schema = @Schema(implementation = Category.class)),
                    mediaType = "aplication/json"
            )}
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error de conexión")
    })
    @GetMapping("/active")
    public ResponseEntity<List<Category>> findActive(){
        return ResponseEntity.ok(svc.findActive());
    }

    /**
     * Crea una categoría
     * @return Una respuesta de éxito o fallo
     * @mapeo /category
     * @metodoHTTP POST
     * @estado 200 - Operación realizada con éxito
     * @estado 400 - Operación fallida
     */
    @Operation(summary = "Registra una categoría", description = "Crea y agrega una categoría, con id único y activa por defecto")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categoría registrada con éxito", 
            content = { @Content(
                    schema = @Schema(implementation = ApiResponse.class),
                    mediaType = "aplication/json"
            )}
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Argumentos no válidos")
    })
    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody DtoCategoryIn in){
        return ResponseEntity.ok(svc.create(in));
    }

    /**
     * Actualiza los datos de una categoría
     * @return Una respuesta de éxito o fallo
     * @mapeo /category/{id}
     * @metodoHTTP PUT
     * @estado 200 - Operación realizada con éxito
     * @estado 400 - Operación fallida
     */
    @Operation(summary = "Actualiza una categoría", description = "Actualiza los datos de una categoría")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categoría actualizada con éxito", 
            content = { @Content(
                    schema = @Schema(implementation = ApiResponse.class),
                    mediaType = "aplication/json"
            )}
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Categoría no encontrada / Argumentos no válidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody DtoCategoryIn in, @PathVariable("id") Integer id){
        return ResponseEntity.ok(svc.update(in, id));
    }

    /**
     * Habilita una categoría
     * @return Una respuesta de éxito o fallo
     * @mapeo /category/{id}/enable
     * @metodoHTTP PATCH
     * @estado 200 - Operación realizada con éxito
     * @estado 400 - Operación fallida
     */
    @Operation(summary = "Habilita una categoría", description = "Cambia el estado de una categoría a activo (1)")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categoría habilitada con éxito", 
            content = { @Content(
                    schema = @Schema(implementation = ApiResponse.class),
                    mediaType = "aplication/json"
            )}
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Categoría no encontrada")
    })
    @PatchMapping("/{id}/enable")
    public ResponseEntity<ApiResponse> enable(@PathVariable Integer id) {
        return ResponseEntity.ok(svc.enable(id));
    }

    /**
     * Deshabilita una categoría
     * @return Una respuesta de éxito o fallo
     * @mapeo /category/{id}/disable
     * @metodoHTTP PATCH
     * @estado 200 - Operación realizada con éxito
     * @estado 400 - Operación fallida
     */
    @Operation(summary = "Deshabilita una categoría", description = "Cambia el estado de una categoría a desactivado (0)")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Categoría deshabilitada con éxito", 
            content = { @Content(
                    schema = @Schema(implementation = ApiResponse.class),
                    mediaType = "aplication/json"
            )}
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Categoría no encontrada")
    })
    @PatchMapping("/{id}/disable")
    public ResponseEntity<ApiResponse> disable(@PathVariable Integer id) {
        return ResponseEntity.ok(svc.disable(id));
    }


}
