/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interfaces;

import entities.Subject;
import exception.ReadException;
import java.util.Set;

/**
 *
 * @author ioritz the abstract class for the subject EJB
 */
public abstract class SubjectEJBLocal extends AbstractEJB<Subject>{
    /**
     * The method to search the subjects that has this name
     * @param obj the data we need to search the subject
     * @return The data of the subject with this name
     * @throws ReadException if any error happened when searching the subject
     */
    public abstract Subject searchByName(Subject obj) throws ReadException;
    /**
     * The method to search the subject of one concret type
     * @param obj the data we need to search the subject
     * @return a set with the data of the subjects of searching type
     * @throws ReadException if any error happened when searching the subject
     */
    public abstract Set<Subject> searchByType(Subject obj) throws ReadException;
    /**
     * The method to search the subject of on concret level
     * @param obj the data we need to search the subject
     * @return a set with the data of the subjects of searching level
     * @throws ReadException if any error happened when searching the subject
     */
    public abstract Set<Subject> searchByLevel(Subject obj) throws ReadException;
    
    /**
     * The method to obtain the data of the subject relationships with other entities
     * @param obj the subject to search
     * @return the subject with all the data
     * @throws ReadException if there any error happened when searching the subject data
     */
    public abstract Subject getSubjectRelationshipsData(Subject obj) throws ReadException;
}