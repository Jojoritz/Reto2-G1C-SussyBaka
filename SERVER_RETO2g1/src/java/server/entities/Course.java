package server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joritz
 *
 * This is the Course entity class
 */
@NamedQueries({
    @NamedQuery(
            name = "findCourse",
            query = "SELECT c FROM Course c, Teacher t, Subject s WHERE c.courseId = :courseId AND c.teacher.id = t.id AND c.subjects.subjectId = s.subjectId"
    )
    ,
        
        @NamedQuery(
            name = "findAllCourses",
            query = "SELECT c FROM Course c, Teacher t, Subject s WHERE c.teacher.id = t.id AND c.subjects.subjectId = s.subjectId"
    )
    ,
    
        @NamedQuery(
            name = "findCourseByName", query = "SELECT c FROM Course c, Teacher t, Subject s WHERE c.teacher.id = t.id AND c.subjects.subjectId = s.subjectId AND c.name LIKE :name"
    )
    ,
        
        @NamedQuery(
            name = "findCourseByDate", query = "SELECT c FROM Course c, Teacher t, Subject s WHERE c.teacher.id = t.id AND c.subjects.subjectId = s.subjectId AND CAST(c.startDate as date) =:startdate"
    )
    ,
        @NamedQuery(
            name = "findCoursesOfStudent", query = "SELECT c FROM Course c JOIN c.courseStudents s WHERE s.id = :userId"
    )
    ,
       @NamedQuery(
            name = "findTeacherCourses", query = "SELECT c FROM Course c JOIN c. teacher t WHERE t.id = :userId"
    )
})

@Entity
@Table(name = "course", schema = "reto2_g1c_sussybaka")
@XmlRootElement
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID field for the course entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private Integer courseId;

    /**
     * String field containing the name of the Course
     */
    @Column
    private String name;

    /**
     * Timestamp field saves the time when the Course was created
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date startDate;

    /**
     * Boolean field that contains the visibility of the Course
     */
    @Column
    private Boolean isVisible;

    /**
     * Boolean field that contains if the Course is private
     */
    @Column
    private Boolean isPrivate;

    @ManyToMany(mappedBy = "studyingCourses")
    private Set<Student> courseStudents;

    /**
     * This is the actual Teacher of the course
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    /**
     * @associates <{Post}>
     * This is a collection with the actual post of the course
     */
    @JsonIgnore
    @XmlTransient
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Collection<Post> coursePosts;

    /**
     * This is the actual Subject of the course
     */
    @ManyToOne(fetch = FetchType.EAGER)
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
     * @return Boolean
     */
    public Boolean getIsVisible() {
        return isVisible;
    }

    /**
     * Sets if the course is visible or not
     *
     * @param isVisible
     */
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Gets if the course is private or not
     *
     * @return Boolean
     */
    public Boolean getIsPrivate() {
        return isPrivate;
    }

    /**
     * Sets if the course is private or not
     *
     * @param isPrivate
     */
    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * Gets the students that are in the course
     *
     * @return The students in the course
     */
    @JsonIgnore
    @XmlTransient
    public Set<Student> getCourseStudents() {
        return courseStudents;
    }

    /**
     * Sets the students that are in the course
     *
     * @param courseStudents
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
     * @param teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Gets the posts of the course
     *
     * @return Course's posts
     */
    @JsonIgnore
    @XmlTransient
    public Collection<Post> getCoursePosts() {
        return coursePosts;
    }

    /**
     * Sets the posts of the course
     *
     * @param coursePosts
     */
    public void setCoursePosts(Collection<Post> coursePosts) {
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
     * @param subject
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
        return "Course{" + "courseId=" + courseId + ", name=" + name + ", startDate=" + startDate + ", isVisible=" + isVisible + ", isPrivate=" + isPrivate + ", courseStudents=" + courseStudents + ", teacher=" + teacher + ", coursePosts=" + coursePosts + ", subject=" + subjects + '}';
    }

}
