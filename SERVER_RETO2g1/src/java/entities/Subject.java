package entities;

import java.io.Serializable;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *This is the entity class of the subject 
 * @author ioritz
 */
@Entity
@Table(name="SUBJECT", schema="reto2_g1c_sussybaka")
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * This is the id of the entity
     */
    @Id
    private Integer subjectId;
    /**
     * The name of the subject
     */
    @Column
    private String name;
    /**
     * The type of the subject
     */
    @Column
    private String type;
    /**
     * The century of the subject
     */
    @Column
    private String century;
    /**
     * The level of the subject
     */
    @Column
    private String level;

    /**
     * @associates <{entities.Course}>
     * The collection of courses that contains this subject
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy="SUBJECT")
    private Collection<Course> courseWithSubject;

    //Constructor
    public Subject() {
        super();
    }

    //Getters and setters
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCentury() {
        return century;
    }

    public void setCentury(String century) {
        this.century = century;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Collection getCourseWithSubject() {
        return courseWithSubject;
    }

    public void setCourseWithSubject(Collection courseWithSubject) {
        this.courseWithSubject = courseWithSubject;
    }

    //Hash code, equals and toString
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.subjectId);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.century);
        hash = 97 * hash + Objects.hashCode(this.level);
        hash = 97 * hash + Objects.hashCode(this.courseWithSubject);
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
        final Subject other = (Subject) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.century, other.century)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.subjectId, other.subjectId)) {
            return false;
        }
        if (!Objects.equals(this.courseWithSubject, other.courseWithSubject)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Subject{" + "subjectId=" + subjectId + ", name=" + name + ", type=" + type + ", century=" + century + ", level=" + level + ", courseWithSubject=" + courseWithSubject + '}';
    }

}
