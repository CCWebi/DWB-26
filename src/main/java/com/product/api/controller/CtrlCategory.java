package com.product.api.controller;

import com.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;

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
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

/**
 * Controlador para categoría
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.5.0
 * @docsTag
 * @beta
 */
@Tag(name = "Category", description = "Endpoints relacionados con las categorías de productos")
@RestController
@RequestMapping("/category")
public class CtrlCategory {
    
    /**
     * Clase de servicio para categorías.
     */
    @Autowired
    SvcCategory svc;

    /**
     * Constructor de la clase
     * Vacío
     */
    public CtrlCategory() {}
    
    /**
     * Regresa la lista de categorías disponible
     * @return La lista de categorías disponible
     * @mapeo /category
     * @metodoHTTP GET
     * @estado 200 - Operación realizada con éxito
     * @estado 400 - Ocurrió un error inesperado
     */
    @Operation(summary = "Obtiene todas las categorías", description = "Regresa las categorías registradas")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operación realizada con éxito", 
            content = { @Content(
                    array = @ArraySchema(schema = @Schema(implementation = Category.class)),
                    mediaType = "aplication/json",
                    examples = @ExampleObject(value = "{\"category\": \"Mexicana\", \"tag\": \"MX\", \"status\": 0, \"categoryid\": \"3\"}")
            )}
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error de conexión", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
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
     * @estado 400 - Ocurrió un error inesperado
     */
    @Operation(summary = "Obtiene todas las categorías activas", description = "Regresa las categorías registradas con estado activo (1)")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operación realizada con éxito", 
            content = { @Content(
                    array = @ArraySchema(schema = @Schema(implementation = Category.class)),
                    mediaType = "aplication/json",
                    examples = @ExampleObject(value = "{\"category\": \"Mexicana\", \"tag\": \"MX\", \"status\": 1, \"categoryid\": 3}")
            )}
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error de conexión", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
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
     * @estado 200 - La categoría ha sido registrada
     * @estado 400 - Ocurrió un error inesperado
     * @estado 409 - Nombre de la categoría repetida
     * @estado 409 - Etiqueta de la categoría repetida
     */
    @Operation(summary = "Registra una categoría", description = "Crea y agrega una categoría, con id único y activa por defecto")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "La categoría ha sido registrada", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "La categoría ha sido registrada"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error inesperado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Nombre de la categoría repetida", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El nombre de la categoría ya está registrado"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Etiqueta de la categoría repetida", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El tag de la categoría ya está registrado")))
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
     * @estado 200 - La categoría ha sido actualizada
     * @estado 400 - Ocurrió un error inesperado
     * @estado 404 - ID no encontrado
     * @estado 409 - Nombre de la categoría repetida
     * @estado 409 - Etiqueta de la categoría repetida
     */
    @Operation(summary = "Actualiza una categoría", description = "Actualiza los datos de una categoría")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "La categoría ha sido actualizada", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "La categoría ha sido actualizada"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error inesperado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "ID no encontrado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El id de la categoría no existe"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Nombre de la categoría repetida", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El nombre de la categoría ya está registrado"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Etiqueta de la categoría repetida", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El tag de la categoría ya está registrado")))
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
     * @estado 200 - La categoría ha sido activada
     * @estado 400 - Ocurrió un error inesperado
     * @estado 404 - ID no encontrado
     */
    @Operation(summary = "Habilita una categoría", description = "Cambia el estado de una categoría a activo (1)")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "La categoría ha sido activada", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "La categoría ha sido activada"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error inesperado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "ID no encontrado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El id de la categoría no existe")))
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
     * @estado 200 - La categoría ha sido desactivada
     * @estado 400 - Ocurrió un error inesperado
     * @estado 404 - ID no encontrado
     */
    @Operation(summary = "Deshabilita una categoría", description = "Cambia el estado de una categoría a desactivado (0)")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "La categoría ha sido desactivada", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "La categoría ha sido desactivada"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error inesperado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "ID no encontrado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El id de la categoría no existe")))
    })
    @PatchMapping("/{id}/disable")
    public ResponseEntity<ApiResponse> disable(@PathVariable Integer id) {
        return ResponseEntity.ok(svc.disable(id));
    }
}
