/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic.exception;

/**
 *
 * @author ioritz
 */
public class UserAllReadyExistException extends Exception {

    /**
     * Creates a new instance of <code>UserAllReadyExistException</code> without
     * detail message.
     */
    public UserAllReadyExistException() {
    }

    /**
     * Constructs an instance of <code>UserAllReadyExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public UserAllReadyExistException(String msg) {
        super(msg);
    }
}
