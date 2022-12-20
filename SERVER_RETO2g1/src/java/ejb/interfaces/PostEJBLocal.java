/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interfaces;

import entities.Comment;
import entities.Post;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author yeguo
 */
@Local
public interface PostEJBLocal extends InterfaceEJBCRUD<Post>{

    
}
