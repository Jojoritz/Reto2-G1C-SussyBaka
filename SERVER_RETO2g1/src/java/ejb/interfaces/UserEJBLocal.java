/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interfaces;

import entities.User;
import exception.CreateException;
import exception.ReadException;
import javax.ejb.Local;

/**
 * The interface that extends to add user use case methods
 * @author ioritz
 */
@Local
public interface UserEJBLocal extends InterfaceEJBCRUD<User>{
    /**
     * A method to obtain all data from the users and the relationship of the user
     * @param user the user we are searching for
     * @return The user with the data, relationships included
     * @throws ReadException if the login data is wrong or dont exist
     */
    public User getUserData(User user) throws ReadException;
}
