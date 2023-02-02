package server.entities;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author ioritz This is the student entity class
 */
@Entity
@DiscriminatorValue("STUDENT")
@XmlRootElement
public class Student extends User {

    private static final long serialVersionUID = 1L;

    /**
     * This is a collection with the acctualy studying courses of the student
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "studying_courses", schema = "reto2_g1c_sussybaka",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @Fetch(value = FetchMode.SELECT)
    private List<Course> studyingCourses;

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
    public List<Course> getStudyingCourses() {
        return studyingCourses;
    }

    /**
     * Sets the courses that the students are studying
     *
     * @param studyingCourses a list of studying courses
     */
    public void setStudyingCourses(List<Course> studyingCourses) {
        this.studyingCourses = studyingCourses;
    }

    @Override
    public String toString() {
        return "Student{" + "studyingCourses=" + studyingCourses + '}';
    }

}
