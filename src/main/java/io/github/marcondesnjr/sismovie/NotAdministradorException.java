/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.sismovie;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class NotAdministradorException extends Exception {

    public NotAdministradorException() {
    }

    public NotAdministradorException(String message) {
        super(message);
    }

    public NotAdministradorException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAdministradorException(Throwable cause) {
        super(cause);
    }

    public NotAdministradorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
