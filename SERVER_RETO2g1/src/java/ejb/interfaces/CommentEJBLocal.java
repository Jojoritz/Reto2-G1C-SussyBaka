/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interfaces;

import entities.Comment;
import javax.ejb.Local;

/**
 *
 * @author yeguo
 */
@Local
public interface CommentEJBLocal extends InterfaceEJBCRUD<Comment>{
    
}
