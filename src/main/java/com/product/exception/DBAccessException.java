package com.product.exception;

import org.springframework.dao.DataAccessException;

/**
 * Clase dedicada para la excepción en Base de Datos
 * @author Isaac Robledo R
 * @author Alejandro Sánchez E
 * @version 0.4.0
 * @beta
 */
public class DBAccessException extends RuntimeException {

    /**
     * Versión de serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Excepción recibida
     */
    private DataAccessException exception;

    /**
     * Constructor de la clase
     * @param e Excepción a recibir
     */
    public DBAccessException(DataAccessException e) {
        this.exception = e;
    }

}
