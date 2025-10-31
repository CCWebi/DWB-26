package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commons.dto.ApiResponse;
import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.Category;
import com.product.api.entity.Product;
import com.product.api.service.SvcProduct;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controlador para producto
 * 
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.7.0
 * @docsTag
 * @beta
 */
@Tag(name = "Product", description = "Endpoints relacionados con los productos")
@RestController
@RequestMapping("/product")
public class CtrlProduct {

	/**
	 * Clase de servicio para productos
	 */
	@Autowired
	SvcProduct svc;

	/**
	 * Regresa la lista de prodcutos
	 * @return La lista de productos
	 * @mapeo /product
	 * @metodoHTTP GET
	 * @estado 200 - Operación realizada con éxito
	 * @estado 400 - Ocurrió un error inesperado
	 */
	@Operation(summary = "Obtiene todos los productos", description = "Regresa los productos registradas")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operación realizada con éxito", 
            content = { @Content(
                    array = @ArraySchema(schema = @Schema(implementation = Product.class)),
                    mediaType = "aplication/json",
                    examples = @ExampleObject(value = "{\"product_id\": 1, \"gtin\": 7501428800778, \"description\": \"Limpiador de pizarrones\", \"price\": 59.99, \"stock\": 16, \"category_id\": 3, \"status\": 1}")
            )}
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error de conexión", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string")))
    })
	@GetMapping
	public ResponseEntity<List<DtoProductListOut>> getProducts() {
		return svc.getProducts();
	}

	/**
	 * Regresa la información del producto buscado
     * @param id identificador del producto (visto en el path)
	 * @return La información del producto buscado
	 * @mapeo /product/{id}
	 * @metodoHTTP GET
	 * @estado 200 - Operación realizada con éxito
	 * @estado 400 - Ocurrió un error inesperado
	 * @estado 404 - ID no encontrado
	 */
	@Operation(summary = "Obtiene un producto", description = "Regresa el producto registrado con el identificador brindado")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Operación realizada con éxito", 
            content = { @Content(
                    schema = @Schema(implementation = Product.class),
                    mediaType = "aplication/json",
                    examples = @ExampleObject(value = "{\"product_id\": 1, \"gtin\": 7501428800778, \"description\": \"Limpiador de pizarrones\", \"price\": 59.99, \"stock\": 16, \"category_id\": 3, \"status\": 1}")
            )}
        ),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error de conexión", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "ID no encontrado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El id del producto no existe")))
    })
	@GetMapping("/{id}")
	public ResponseEntity<DtoProductOut> getProduct(@PathVariable Integer id) {
		return svc.getProduct(id);
	}

	/**
     * Crea un producto
     * @param in objeto de transferencia de entrada que representa un producto (body)
     * @return Una respuesta de éxito o fallo
     * @mapeo /product
     * @metodoHTTP POST
     * @estado 200 - El producto ha sido registrado
     * @estado 400 - Ocurrió un error inesperado
     * @estado 409 - Un dato ingresado ha sido detectado con algún error
     */
	@Operation(summary = "Registra un producto", description = "Crea y agrega un producto con id único")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "El producto ha sido registrado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "La categoría ha sido registrada"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error inesperado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "El id de categoría no existe", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El id de categoría no existe"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "El gtin del producto ya está registrado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El gtin del producto ya está registrado"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "El nombre del producto ya está registrado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El nombre del producto ya está registrado")))
    })
	@PostMapping
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody DtoProductIn in) {
		return svc.createProduct(in);
	}

	/**
     * Actualiza los datos de un producto
     * @param id identificador del producto (visto en el path)
     * @param in objeto de transferencia de entrada que representa un producto (body)
     * @return Una respuesta de éxito o fallo
     * @mapeo /product/{id}
     * @metodoHTTP PUT
     * @estado 200 - El producto ha sido actualizado
     * @estado 400 - Ocurrió un error inesperado
     * @estado 404 - ID no encontrado
     * @estado 409 - Un dato ingresado ha sido detectado con algún error
     */
	@Operation(summary = "Actualiza un producto", description = "Actualiza los datos de un producto")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "El producto ha sido actualizado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "La categoría ha sido actualizada"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error inesperado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "El id del producto no existe", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El id del producto no existe"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "El id de categoría no existe", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El id de categoría no existe"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "El gtin del producto ya está registrado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El gtin del producto ya está registrado"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "El nombre del producto ya está registrado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El nombre del producto ya está registrado")))
    })
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer id, @Valid @RequestBody DtoProductIn in) {
		return svc.updateProduct(id, in);
	}

    /**
     * Habilita un producto
     * @param id identificador del producto (visto en el path)
     * @return Una respuesta de éxito o fallo
     * @mapeo /product/{id}/enable
     * @metodoHTTP PATCH
     * @estado 200 - La categoría ha sido activada
     * @estado 400 - Ocurrió un error inesperado
     * @estado 404 - ID no encontrado
     */
	@Operation(summary = "Habilita un producto", description = "Cambia el estado de un producto a activo (1)")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "El producto ha sido activado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El producto ha sido activado"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error inesperado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "El id del producto no existe", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El id del producto no existe"))),
    })
	@PatchMapping("/{id}/enable")
	public ResponseEntity<ApiResponse> enableProduct(@PathVariable Integer id) {
		return svc.enableProduct(id);
	}

	/**
     * Deshabilita un producto
     * @param id identificador del producto (visto en el path)
     * @return Una respuesta de éxito o fallo
     * @mapeo /product/{id}/disable
     * @metodoHTTP PATCH
     * @estado 200 - La categoría ha sido desactivada
     * @estado 400 - Ocurrió un error inesperado
     * @estado 404 - ID no encontrado
     */
	@Operation(summary = "Deshabilita un producto", description = "Cambia el estado de un producto a desactivado (0)")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "El producto ha sido desactivado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El producto ha sido desactivado"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Ocurrió un error inesperado", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "El id del producto no existe", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"), examples = @ExampleObject(value = "El id del producto no existe"))),
    })
	@PatchMapping("/{id}/disable")
	public ResponseEntity<ApiResponse> disableProduct(@PathVariable Integer id) {
		return svc.disableProduct(id);
	}
}
