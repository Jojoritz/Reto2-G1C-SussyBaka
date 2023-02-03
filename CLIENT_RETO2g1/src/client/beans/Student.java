package client.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

;

/**
 *
 * @author ioritz This is the student entity class
 */
@XmlRootElement
public class Student extends User {

    private static final long serialVersionUID = 1L;

    /**
     * This is a collection with the acctualy studying courses of the student
     */
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
