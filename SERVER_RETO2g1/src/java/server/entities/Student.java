package server.entities;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ioritz This is the student entity class
 */

@NamedQueries({
    @NamedQuery(
            name="getStudentCourseData", query="SELECT sc FROM Student s, IN(s.studyingCourses) sc WHERE s.id = :id"
    )
})
@Entity
@DiscriminatorValue("student")
@XmlRootElement
public class Student extends User {

    private static final long serialVersionUID = 1L;

    /**
     * @associates <{entities.Course}>
     * This is a collection with the acctualy studying courses of the student
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "studying_courses", schema = "reto2_g1c_sussybaka",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> studyingCourses;

    //Constructor
    public Student() {
        super();
    }

    //Getters and setters
    /**
     * Gets the courses that the students are studying
     *
     * @return Courses
     */
    @XmlTransient
    public Set<Course> getStudyingCourses() {
        return studyingCourses;
    }

    /**
     * Sets the courses that the students are studying
     *
     * @param studyingCourses
     */
    public void setStudyingCourses(Set<Course> studyingCourses) {
        this.studyingCourses = studyingCourses;
    }

    //hashCode, equals and toString
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.studyingCourses);
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.studyingCourses, other.studyingCourses)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student{" + "studyingCourses=" + studyingCourses + '}';
    }

}
