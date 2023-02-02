package server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This is the entity class of the subject
 *
 * @author ioritz
 */
@NamedQueries(
        {
            @NamedQuery(
                    name = "findAllSubjects", query = "SELECT s FROM Subject s"
            )
            ,
       @NamedQuery(
                    name = "getSubjectByName", query = "SELECT s FROM Subject s WHERE s.name LIKE :name"
            )
            ,
        @NamedQuery(
                    name = "getSubjectsByType", query = "SELECT s FROM Subject s WHERE s.type LIKE :type"
            )
            ,
        @NamedQuery(
                    name = "getSubjectsByLevel", query = "SELECT s FROM Subject s WHERE s.level LIKE :level"
            )
            ,
         @NamedQuery(
                    name = "findSubjectsOfTeacher", query = "SELECT su FROM Subject su JOIN su.teachersSpecializedInSubject t WHERE t.id = :userId"
            )
        }
)

@Entity
@Table(name = "SUBJECTS", schema = "reto2_g1c_sussybaka")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * This is the id of the entity
     */
    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subjectId;

    /**
     * The name of the subject
     */
    @Column
    @JsonProperty("name")
    private String name;

    /**
     * The type of the subject
     */
    @Column
    @JsonProperty("type")
    private String type;

    /**
     * The century of the subject
     */
    @Column
    @JsonProperty("century")
    private String century;

    /**
     * The level of the subject
     */
    @Column(name = "subject_level")
    @JsonProperty("level")
    private String level;

    /**
     * @associates <{entities.Course}>
     * The collection of courses that contains this subject
     */
    @OneToMany(mappedBy = "subjects", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Course> courseWithSubject;
    /**
     * @associates <{entities.Teacher}>
     * The collection of teacher that are specialized in this subject
     */
    @ManyToMany(mappedBy = "specializedSubjects", fetch = FetchType.EAGER)
    private Set<Teacher> teachersSpecializedInSubject;

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
    @JsonIgnore
    public Set<Course> getCourseWithSubject() {
        return courseWithSubject;
    }

    /**
     * Sets the courses that includes the subject
     *
     * @param courseWithSubject
     */
    public void setCourseWithSubject(Set<Course> courseWithSubject) {
        this.courseWithSubject = courseWithSubject;
    }

    /**
     * Gets the teacher that imparts the subject
     *
     * @return
     */
    @XmlTransient
    @JsonIgnore
    public Set<Teacher> getTeachersSpecializedInSubject() {
        return teachersSpecializedInSubject;
    }

    /**
     * Sets the teacher that imparts the subject
     *
     * @param teachersSpecializedInSubject
     */
    public void setTeachersSpecializedInSubject(Set<Teacher> teachersSpecializedInSubject) {
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
