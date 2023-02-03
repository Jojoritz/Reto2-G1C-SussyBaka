/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.exception;

/**
 * This class extends from {@link Exception} and its used to catch exceptions
 * when encrypting/decrypting data
 *
 *
 * @author Henri
 */
public class EncriptionException extends Exception {

    /**
     * Empty constructor of the {@link Exception}
     */
    public EncriptionException() {
    }

    /**
     * Constructor of the {@link Exception} with message
     *
     * @param message Message with details of the exception
     */
    public EncriptionException(String message) {
        super(message);
    }

}
