/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.rest;

/**
 * Enumeration for the type that can respond or send to the restful service
 *
 * @author Henri
 */
public enum ReturnType {
    /**
     * XML type if the server or client can use this language
     */
    XML, 
    /**
     * JSON type if the server or client can use this language
     */
    JSON
}
