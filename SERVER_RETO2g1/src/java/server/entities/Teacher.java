package server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ioritz This is the teacher entity class
 */
@Entity
@DiscriminatorValue("TEACHER")
@XmlRootElement
public class Teacher extends User {

    private static final long serialVersionUID = 1L;

    /**
     * @associates <{entities.Course}>
     * A collection of the actually teaching courses of the teacher
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Course> teachingCourses;

    /**
     * @associates <{entities.Subject}>
     * A collection with the specialized subject of this teacher
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "specialized_subjects", schema = "reto2_g1c_sussybaka",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    @JsonProperty("specializedSubjects")
    private Set<Subject> specializedSubjects;

    //Constructor
    public Teacher() {
        super();
    }

    //Getters and setters
    /**
     * Gets the courses of the teacher
     *
     * @return Course
     */
    @XmlTransient
    @JsonIgnore
    public Set<Course> getTeachingCourses() {
        return teachingCourses;
    }

    /**
     * Sets the course of the teacher
     *
     * @param teachingCourses
     */
    public void setTeachingCourses(Set<Course> teachingCourses) {
        this.teachingCourses = teachingCourses;
    }

    /**
     * Gets the subject that the teacher imparts
     *
     * @return Subject
     */
    public Set<Subject> getSpecializedSubjects() {
        return specializedSubjects;
    }

    /**
     * Sets the subject that the teacher imparts
     *
     * @param specializedSubjects
     */
    public void setSpecializedSubjects(Set<Subject> specializedSubjects) {
        this.specializedSubjects = specializedSubjects;
    }

    @Override
    public String toString() {
        return "Teacher{" + "teachingCourses=" + teachingCourses + ", specializedSubjects=" + specializedSubjects + '}';
    }

}
