package entities;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ioritz This is the teacher entity class
 */
@NamedQueries(
        @NamedQuery(
                name="getTeacherCourseData", query="SELECT tc FROM Teacher t, IN(t.teachingCourses) tc "
                        + "WHERE u.id = :id"
        )
)
@Entity
@DiscriminatorValue("teacher")
@XmlRootElement
public class Teacher extends User {

    private static final long serialVersionUID = 1L;

    /**
     * @associates <{entities.Course}>
     * A collection of the actually teaching courses of the teacher
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Course> teachingCourses;

    /**
     * @associates <{entities.Subject}>
     * A collection with the specialized subject of this teacher
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "specialized_subjects", schema = "reto2_g1c_sussybaka",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
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
    @XmlTransient
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

    //Equals, hashCode and toString
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.teachingCourses);
        hash = 97 * hash + Objects.hashCode(this.specializedSubjects);
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
        final Teacher other = (Teacher) obj;
        if (!Objects.equals(this.teachingCourses, other.teachingCourses)) {
            return false;
        }
        if (!Objects.equals(this.specializedSubjects, other.specializedSubjects)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Teacher{" + "teachingCourses=" + teachingCourses + ", specializedSubjects=" + specializedSubjects + '}';
    }

}
