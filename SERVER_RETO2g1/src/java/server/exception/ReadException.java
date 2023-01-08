/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.exception;

/**
 * This class extends from {@link Exception} and its used to catch exceptions
 * when reading data
 *
 * @author yeguo
 */
public class ReadException extends Exception{

    /**
     * Empty constructor of the {@link ReadException}
     */
    public ReadException() {
    }

    /**
     * Constructor of the {@link ReadException} with message
     * 
     * @param message Message with details of the exception
     */
    public ReadException(String message) {
        super(message);
    }
    
}
