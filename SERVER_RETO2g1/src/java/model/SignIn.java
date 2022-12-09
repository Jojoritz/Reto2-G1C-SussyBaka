package model;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.Objects;

public class SignIn implements Serializable {
    private static final long serialVersionUID = 1L;
    private Timestamp lastSignIn;

    public SignIn() {
        super();
    }

    public Timestamp getLastSignIn() {
        return lastSignIn;
    }

    public void setLastSignIn(Timestamp lastSignIn) {
        this.lastSignIn = lastSignIn;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.lastSignIn);
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
        final SignIn other = (SignIn) obj;
        if (!Objects.equals(this.lastSignIn, other.lastSignIn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SignIn{" + "lastSignIn=" + lastSignIn + '}';
    }

}
