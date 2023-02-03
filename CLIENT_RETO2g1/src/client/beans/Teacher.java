package client.beans;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ioritz This is the teacher entity class
 */
@XmlRootElement
public class Teacher extends User {

    private static final long serialVersionUID = 1L;

    /**
     * A collection of the actually teaching courses of the teacher
     */
    private Set<Course> teachingCourses;

    /**
     * A collection with the specialized subject of this teacher
     */
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
