package client.beans;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joritz
 *
 * This is the Course entity class
 */
@XmlRootElement
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID field for the course entity
     */
    private Integer courseId;

    /**
     * String field containing the name of the Course
     */
    private String name;

    /**
     * Timestamp field saves the time when the Course was created
     */
    private Date startDate;

    /**
     * Boolean field that contains the visibility of the Course
     */
    private Boolean isVisible;

    /**
     * Boolean field that contains if the Course is private
     */
    private Boolean isPrivate;

    /**
     * 
     */
    private Set<Student> courseStudents;

    /**
     * This is the actual Teacher of the course
     */
    private Teacher teacher;

    /**
     * This is a collection with the actual post of the course
     */
    private Set<Post> coursePosts;

    /**
     * This is the actual Subject of the course
     */
    private Subject subjects;

    //Constructors
    public Course() {
        super();
    }

    //Getters & Setters
    /**
     * Gets the course ID
     *
     * @return ID of the course
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * Sets the course ID
     *
     * @param courseId Passes ID of the course
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * Gets the name of the course
     *
     * @return Name of the course
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the course
     *
     * @param name Name of the course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the creation date of the course
     *
     * @return Timestamp with the date of creation of the course
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the creation date of the course
     *
     * @param startDate Starting Date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets if the course is visible or not
     *
     * @return Boolean if the course is visible or not
     */
    public Boolean getIsVisible() {
        return isVisible;
    }

    /**
     * Sets if the course is visible or not
     *
     * @param isVisible if the course is visible or not
     */
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Gets if the course is private or not
     *
     * @return Boolean if the course is priate or not
     */
    public Boolean getIsPrivate() {
        return isPrivate;
    }

    /**
     * Sets if the course is private or not
     *
     * @param isPrivate if the course is private or not
     */
    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * Gets the students that are in the course
     *
     * @return The students in the course
     */
    public Set<Student> getCourseStudents() {
        return courseStudents;
    }

    /**
     * Sets the students that are in the course
     *
     * @param courseStudents the students of the course
     */
    public void setCourseStudents(Set<Student> courseStudents) {
        this.courseStudents = courseStudents;
    }

    /**
     * Gets the Teacher of the course
     *
     * @return Teacher of the course
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Sets the teacher of the group
     *
     * @param teacher the teacher of the course
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Gets the posts of the course
     *
     * @return Course's posts
     */
    public Set<Post> getCoursePosts() {
        return coursePosts;
    }

    /**
     * Sets the posts of the course
     *
     * @param coursePosts the course posts
     */
    public void setCoursePosts(Set<Post> coursePosts) {
        this.coursePosts = coursePosts;
    }

    /**
     * Gets the subject of the course
     *
     * @return Subject of the course
     */
    public Subject getSubject() {
        return subjects;
    }

    /**
     * Sets the subject of the course
     *
     * @param subject the subejct of the course
     */
    public void setSubject(Subject subject) {
        this.subjects = subject;
    }

    //hascode, equals & toString
    @Override
    public int hashCode() {
        return Objects.hash(courseId);
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
        Course other = (Course) obj;
        return Objects.equals(courseId, other.courseId);
    }

    @Override
    public String toString() {
        return "Course{" + "courseId=" + courseId + ", name=" + name + ", startDate=" + startDate + ", isVisible=" + isVisible + ", isPrivate=" + isPrivate + ", teacher=" + teacher + ", subjects=" + subjects + '}';
    }

}
