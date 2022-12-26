/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.interfaces.UserEJBLocal;
import entities.Course;
import entities.User;
import entities.enumerations.UserPrivilege;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author ioritz
 */
public class UserEJB extends UserEJBLocal {

    @Override
    public User getUserData(User user) throws ReadException {
        
        
        try {
           
            //TODO a condition where if the user is a teacher executes one named query and if it is a student another to obtain corresponding relationships data
            if (user.getPrivilege().equals(UserPrivilege.STUDENT)) {
                
            }
            else if (user.getPrivilege().equals(UserPrivilege.TEACHER)) {
                
                
                
            }
        } catch (Exception e) {
            throw new ReadException();
        }

        return user;
    }

    @Override
    public User find(Object obj) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
