package com.product.exception;

import org.springframework.http.HttpStatus;

/**
 * Clase dedicada para la excepción sobre la API
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.4.0
 * @beta
 */
public class ApiException extends RuntimeException {

    /**
     * Versión de serialziado
     */
    private static final long serialVersionUID = 1L;

    /**
     * Estado de la response HTTP
     */
	private HttpStatus status;
	
    /**
     * Constructor default
     * @param status Estado de la response HTTP
     * @param message Mensaje de la excepción
     */
	public ApiException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}   

    /**
     * Regresa el estado de response HTTP
     * @return El estado de response HTTP
     */
    public HttpStatus getStatus() {
		return status;
	}

    /**
     * Asigna el nuevo estado de response HTTP
     * @param status Nuevo estado de response HTTP
     */
	public void setStatus(HttpStatus status) {
		this.status = status;
	} 
}