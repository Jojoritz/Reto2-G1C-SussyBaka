package client.beans;

import java.io.Serializable;
import java.util.HashSet;

import java.util.Objects;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the entity class of the subject
 *
 * @author ioritz
 */
@XmlRootElement
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * This is the id of the entity
     */
    private Integer subjectId;

    /**
     * The name of the subject
     */
    private String name;

    /**
     * The type of the subject
     */
    private String type;

    /**
     * The century of the subject
     */
    private String century;

    /**
     * The level of the subject
     */
    private String level;

    /**
     * The collection of courses that contains this subject
     */
    private Set<Course> courseWithSubject;
    /**
     * The collection of teacher that are specialized in this subject
     */
    private Set<Teacher> teachersSpecializedInSubject;

    //Constructor
    public Subject() {
        super();
        this.teachersSpecializedInSubject = new HashSet<>();
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
     * @param subjectId the id of the subject
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
     * @param name the name of the subject
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
     * @param type the type of the subject
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
     * @param century the century when the subject was created
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
     * @param level the level of the subject
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Gets the courses that includes the subject
     *
     * @return Course
     */
    public Set<Course> getCourseWithSubject() {
        return courseWithSubject;
    }

    /**
     * Sets the courses that includes the subject
     *
     * @param courseWithSubject a collection of course with the courses where the subject is teached
     */
    public void setCourseWithSubject(Set<Course> courseWithSubject) {
        this.courseWithSubject = courseWithSubject;
    }

    /**
     * Gets the teacher that imparts the subject
     *
     * @return a collection of teachers that are specialized in this subject
     */
    public Set<Teacher> getTeachersSpecializedInSubject() {
        return teachersSpecializedInSubject;
    }

    /**
     * Sets the teacher that imparts the subject
     *
     * @param teachersSpecializedInSubject a collection of teacher that are specialized in this subject
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
