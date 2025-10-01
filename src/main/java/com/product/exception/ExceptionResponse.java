package com.product.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Clase dedicada para la respuesta de excepciones en formato JSON
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.4.0
 * @beta
 */
public class ExceptionResponse {

    /**
     * Momento de la excepción
     * Formato para JSON
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;

    /**
     * Estado actual
     */
	private Integer status;

    /**
     * Estado de error de la response HTTP
     */
	private HttpStatus error;

    /**
     * Mensaje de error
     */
	private String message;

    /**
     * Camino de origen de la excepción
     */
	private String path;
    
    /**
     * Obtiene el momento de la excepción
     * @return el momento de la excepción
     */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

    /**
     * Asigna el nuevo momento de la exception
     * @param timestamp el nuevo momento de la exception
     */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

    /**
     * Obtiene el estado actual
     * @return el estado actual
     */
	public Integer getStatus() {
		return status;
	}

    /**
     * Asigna el estado actual
     * @param status el estado actual
     */
	public void setStatus(Integer status) {
		this.status = status;
	}

    /**
     * Obtiene el estado de error de la response HTTP
     * @return el estado de error de la response HTTP
     */
	public HttpStatus getError() {
		return error;
	}

    /**
     * Asigna el estado de error de la response HTTP
     * @param error el estado de error de la response HTTP
     */
	public void setError(HttpStatus error) {
		this.error = error;
	}

    /**
     * Obtiene el mensaje de error
     * @return el mensaje de error
     */
	public String getMessage() {
		return message;
	}

    /**
     * Asigna el mensaje de error
     * @param message el mensaje de error
     */
	public void setMessage(String message) {
		this.message = message;
	}

    /**
     * Obtiene el camino de origen de la excepción
     * @return el camino de origen de la excepción
     */
	public String getPath() {
		return path;
	}

    /**
     * Asigna el camino de origen de la excepción
     * @param path el camino de origen de la excepción
     */
	public void setPath(String path) {
		this.path = path;
	}
}