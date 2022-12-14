package entities;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author ioritz This is the student entity class
 */
@Entity
@DiscriminatorValue("student")
public class Student extends User {

    private static final long serialVersionUID = 1L;

    /**
     * @associates <{entities.Course}>
     * This is a collection with the acctualy studying courses of the student
     */
    @ManyToMany
    private Collection<Course> studyingCourses;

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
    public Collection<Course> getStudyingCourses() {
        return studyingCourses;
    }

    /**
     * Sets the courses that the students are studying
     *
     * @param studyingCourses
     */
    public void setStudyingCourses(Collection<Course> studyingCourses) {
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
