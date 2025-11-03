package com.commons.dto;

/**
 * Clase que representa una respuesta para la API
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.5.0
 * @beta
 */
public class ApiResponse {

    /**
     * Mensaje a mostrar en la respuesta
     */
    private String message;

    /**
     * Constructor de la clase
     * @param message Mensaje a mostrar en la respuesta
     */
    public ApiResponse(String message) {
        this.message = message;
    }

    /**
     * Regresa el mensaje a mostrar en la respuesta
     * @return El mensaje a mostrar en la respuesta
     */
    public String getMessage() {
        return message;
    }

    /**
     * Actualiza el mensaje a mostrar en la respuesta
     * @param message el mensaje a mostrar en la respuesta
     */
    public void setMessage(String message) {
        this.message = message;
    }
}