package server.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.List;
import server.entities.enumerations.UserPrivilege;
import server.entities.enumerations.UserStatus;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the user entity class
 *
 * @author ioritz
 */
@NamedQueries({
    @NamedQuery(
            name = "getUserLogin", query = "SELECT u FROM User u WHERE u.login = :login AND u.password = :password"
    )
    ,
        @NamedQuery(
            name = "findAllUsers", query = "SELECT u FROM User u"
    )
})

@Entity
@Table(name = "USERS", schema = "reto2_g1c_sussybaka")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "privilege", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "ADMIN")
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The id of the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    /**
     * The user to login in the application
     */
    @Column(unique = true)
    private String login;

    /**
     * The user email
     */
    @Column(unique = true)
    private String email;

    /**
     * The user full name
     */
    @Column
    private String fullName;

    /**
     * The password of the account
     */
    @Column(name = "user_password")
    private String password;

    /**
     * A timestamp with the date of latest password changes
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date lastPasswordChange;

    /**
     * The status of the user
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus status;

    /**
     * The privilege of the user in the application
     */
    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private UserPrivilege privilege;

    /**
     * A collection with the date of the last sign in in the application
     */
    @ElementCollection(targetClass = Date.class, fetch = FetchType.EAGER)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @CollectionTable(name = "user_signInHistory", schema = "reto2_g1c_sussybaka")
    private List<Date> signInHistory;

    //Constructor
    public User() {
        super();
    }

    //Getters and setters
    /**
     * Gets the ID of user
     *
     * @return User ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id of user
     *
     * @param id the user id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the login of user
     *
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login of user
     *
     * @param login the login of the user
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the email of the user
     *
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user
     *
     * @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the full name of the user
     *
     * @return full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the user
     *
     * @param fullName the full name of the user
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the password of the user
     *
     * @return password the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user
     *
     * @param password the password of the user 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the last time where the user changed his password
     *
     * @return Date a date of last password change
     */
    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    /**
     * Sets the last time where the user changed his password
     *
     * @param lastPasswordChange the las password change date
     */
    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    /**
     * Gets the status of the user
     *
     * @return the status of the user
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the user
     *
     * @param status the status of the user
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Gets the privilege of the user
     *
     * @return the privilege of the user
     */
    public UserPrivilege getPrivilege() {
        return privilege;
    }

    /**
     * Sets the privilege of the user
     *
     * @param privilege the privilege of the user
     */
    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }

    /**
     * Gets the times that the user has signed in
     *
     * @return a list with the dates of the sign in history
     */
    public List<Date> getSignInHistory() {
        return signInHistory;
    }

    /**
     * Sets the times that the user has signed in
     *
     * @param signInHistory the list with the sign in history
     */
    public void setSignInHistory(List<Date> signInHistory) {
        this.signInHistory = signInHistory;
    }
    //HashCode, equals and toString

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.login);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.fullName);
        hash = 89 * hash + Objects.hashCode(this.password);
        hash = 89 * hash + Objects.hashCode(this.lastPasswordChange);
        hash = 89 * hash + Objects.hashCode(this.status);
        hash = 89 * hash + Objects.hashCode(this.privilege);
        hash = 89 * hash + Objects.hashCode(this.signInHistory);
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
