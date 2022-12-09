package model;

import java.io.Serializable;

import java.sql.Timestamp;

public class SignIn implements Serializable{
    private Timestamp lastSignIn;

    public SignIn() {
        super();
    }
}
