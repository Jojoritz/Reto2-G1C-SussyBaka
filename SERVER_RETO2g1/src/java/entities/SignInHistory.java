/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *The class to save the last sign in history
 * @author ioritz
 */
@Embeddable
public class SignInHistory implements Serializable {
    
    /**
     * The date of the signIn
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date signIn;
    
    
    //Constructors
    public SignInHistory(){
        super();
    }
    
    //Getters and setters
    public void setSignIn(Date signIn){
        this.signIn = signIn;
    }
    
    public Date getSignIn(){
        return this.signIn;
    }

    //HashCode, equals and toString
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.signIn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SignInHistory other = (SignInHistory) obj;
        if (!Objects.equals(this.signIn, other.signIn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SignInHistory{" + "signIn=" + signIn + '}';
    }
   
}
