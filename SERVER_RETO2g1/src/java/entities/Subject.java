package entities;

import java.io.Serializable;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This is the entity class of the subject
 *
 * @author ioritz
 */
@Entity
@Table(name = "SUBJECT", schema = "reto2_g1c_sussybaka")
@XmlRootElement
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * This is the id of the entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "SUBJECT")
    private Collection<Course> courseWithSubject;

    /**
     * @associates <{entities.Teacher}>
     * The collection of teacher that are specialized in this subject
     */
    @ManyToMany
    @JoinTable(name = "studying_courses", schema = "reto2_g1c_sussybaka")
    private Collection<Teacher> teachersSpecializedInSubject;

    //Constructor
    public Subject() {
        super();
    }

    //Getters and setters
    /**
     * Gets the ID of the subject
     *
     * @return Subject ID
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * Sets the ID of the subject
     *
     * @param subjectId
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Gets the name of the subject
     *
     * @return Subject Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the subject
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of subject
     *
     * @return Subject type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of subject
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the Century where the subject arose
     *
     * @return Subject Century
     */
    public String getCentury() {
        return century;
    }

    /**
     * Sets the Century where the subject arose
     *
     * @param century
     */
    public void setCentury(String century) {
        this.century = century;
    }

    /**
     * Gets the level of the subject
     *
     * @return Subject level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the level of the subject
     *
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Gets the courses that includes the subject
     *
     * @return Course
     */
    @XmlTransient
    public Collection<Course> getCourseWithSubject() {
        return courseWithSubject;
    }

    /**
     * Sets the courses that includes the subject
     *
     * @param courseWithSubject
     */
    public void setCourseWithSubject(Collection<Course> courseWithSubject) {
        this.courseWithSubject = courseWithSubject;
    }

    /**
     * Gets the teacher that imparts the subject
     *
     * @return
     */
    @XmlTransient
    public Collection<Teacher> getTeachersSpecializedInSubject() {
        return teachersSpecializedInSubject;
    }

    /**
     * Sets the teacher that imparts the subject
     *
     * @param teachersSpecializedInSubject
     */
    public void setTeachersSpecializedInSubject(Collection<Teacher> teachersSpecializedInSubject) {
        this.teachersSpecializedInSubject = teachersSpecializedInSubject;
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
        hash = 97 * hash + Objects.hashCode(this.teachersSpecializedInSubject);
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
        if (!Objects.equals(this.teachersSpecializedInSubject, other.teachersSpecializedInSubject)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Subject{" + "subjectId=" + subjectId + ", name=" + name + ", type=" + type + ", century=" + century + ", level=" + level + ", courseWithSubject=" + courseWithSubject + ", teachersSpecialized=" + teachersSpecializedInSubject + '}';
    }

}
