package com.product.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.service.SvcProductImage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controlador para imagenes de producto
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @docsTag
 * @beta
 */
@Tag(name = "Product-Image", description = "Endpoints relacionados con las imagenes de los productos")
@RestController
@RequestMapping("/product/{id}/image")
public class CtrlProductImage {

    /**
     * Clase de servicio para imagenes de producto
     */
    @Autowired
    SvcProductImage svc;

    /**
     * Regresa la lista de imagenes de un producto disponible
     * @return La lista de imagenes de un producto disponible
     * @mapeo /product/{id}/image
     * @metodoHTTP GET
     * @estado 200 - Operación realizada con éxito
     * @estado 400 - Ocurrió un error inesperado
     */
    @Operation(summary = "Obtiene todas las imagenes de un producto", description = "Regresa las imagenes registradas asociadas a un producto específico")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operación realizada con éxito", 
            content = { @Content(
                    array = @ArraySchema(schema = @Schema(implementation = ProductImage.class)),
                    mediaType = "aplication/json",
                    examples = @ExampleObject(value = "{\"product_image_id\": 1, \"product_id\": 2, \"status\": 0, \"image\": \"92489398420...\"}")
            )}
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error de conexión", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
    })
    @GetMapping
    public ResponseEntity<ApiResponse> getProductImages(@PathVariable Integer id) {
        return ResponseEntity.ok(svc.findAll(id));
    }
    
    /**
     * Crea una imagen para un producto
     * @param id identificador del producto al que se asociará la imagen
     * @param in objeto de transferencia de entrada que representa una imagen de producto (body)
     * @return Una respuesta de éxito o fallo
     * @mapeo /product/{id}/image
     * @metodoHTTP POST
     * @estado 200 - La categoría ha sido registrada
     * @estado 400 - Ocurrió un error inesperado
     * @estado 404 - El id del producto no existeó
     * @estado 409 - 
     */
    @Operation(summary = "Registra una categoría", description = "Crea y agrega una categoría, con id único y activa por defecto")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "La imagen ha sido registrada", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "La categoría ha sido registrada"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error inesperado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "El id del producto no existe", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El id del producto no existe"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "")))
    })
    @PostMapping
    public ResponseEntity<ApiResponse> createProductImage(@PathVariable Integer id, @Valid @RequestBody DtoProductImageIn in){
        return ResponseEntity.ok(svc.upload(in));
    }

    /**
     * Elimina una imagen para un producto
     * @param id identificador del producto al del que se eliminará la imagen
     * @param product_image_id identificador del producto al del que se eliminará la imagen
     * @return Una respuesta de éxito o fallo
     * @mapeo /product/{id}/image/{product-image-id}
     * @metodoHTTP POST
     * @estado 200 - La categoría ha sido registrada
     * @estado 400 - Ocurrió un error inesperado
     * @estado 404 - El id del producto no existeó
     * @estado 409 - 
     */
    @Operation(summary = "Registra una categoría", description = "Crea y agrega una categoría, con id único y activa por defecto")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "La imagen ha sido eliminada", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "La categoría ha sido registrada"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error inesperado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "El id del producto no existe", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El id del producto no existe"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "")))
    })
    @DeleteMapping("/{product-image-id}")
    public ResponseEntity<ApiResponse> deleteProductImage(@PathVariable Integer id, @PathVariable("product-image-id") Integer product_image_id) {
        return ResponseEntity.ok(svc.delete(id, product_image_id));
    }

}
