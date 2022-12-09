package model;

import enumerations.UserPrivilege;
import enumerations.UserStatus;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collection;
import java.util.List;

public class User implements Serializable{
    private Integer id;
    private String login;
    private String email;
    private String fullName;
    private String password;
    private Timestamp lastPasswordChange;
    private UserStatus status;
    private UserPrivilege privilege;

    /**
     * @associates <{model.SignIn}>
     */
    private List signInHistory;



    public User() {
        super();
    }

    
}
