package entities;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collection;
import java.util.List;
import entities.enumerations.UserPrivilege;
import entities.enumerations.UserStatus;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="USER", schema="reto2_g1c_sussybaka")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    @Column
    private String login;
    @Column
    private String email;
    @Column
    private String fullName;
    @Column
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Set<Timestamp> lastPasswordChange;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Enumerated(EnumType.STRING)
    private UserPrivilege privilege;

    /**
     * @associates <{model.SignIn}>
     */
    @OneToMany
    private List signInHistory;

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserPrivilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }

    public List getSignInHistory() {
        return signInHistory;
    }

    public void setSignInHistory(List signInHistory) {
        this.signInHistory = signInHistory;
    }

    public Set<Timestamp> getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(Set<Timestamp> lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.login);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.fullName);
        hash = 67 * hash + Objects.hashCode(this.password);
        hash = 67 * hash + Objects.hashCode(this.lastPasswordChange);
        hash = 67 * hash + Objects.hashCode(this.status);
        hash = 67 * hash + Objects.hashCode(this.privilege);
        hash = 67 * hash + Objects.hashCode(this.signInHistory);
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
        final User other = (User) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.lastPasswordChange, other.lastPasswordChange)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.privilege != other.privilege) {
            return false;
        }
        if (!Objects.equals(this.signInHistory, other.signInHistory)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login=" + login + ", email=" + email + ", fullName=" + fullName + ", password=" + password + ", lastPasswordChange=" + lastPasswordChange + ", status=" + status + ", privilege=" + privilege + ", signInHistory=" + signInHistory + '}';
    }

   
    
    

}
