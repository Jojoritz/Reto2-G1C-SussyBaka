package server.entities;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
                name="findCourse", query="SELECT new server.entities.dto.CourseDTO(c.courseId, c.name, c.startDate, c.isVisible, c.isPrivate, t.fullName, s.name) FROM Course c, Teacher t, Subject s WHERE c.courseId = :courseId AND c.id = t.id AND c.subjectId = s.subjectId"
        ),
        
        @NamedQuery(
                name="findAllCourses", query="SELECT new server.entities.Course(c.courseId, c.name, c.startDate, c.isVisible, c.isPrivate) FROM Course c"
        ),
    
        @NamedQuery(
                name="findCourseByName", query="SELECT c FROM Course c WHERE c.name LIKE :name"
        ),
        
        @NamedQuery(
                name="findCourseByDate", query="SELECT c FROM Course c WHERE CAST(c.startDate as date) =:startdate"
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

    /**
     * @associates <{Student}>
     * This is a collection with the actual students of the course
     */
    @ManyToMany(mappedBy = "studyingCourses")
    private Set<Student> courseStudents;

    /**
     * This is the actual Teacher of the course
     */
    @ManyToOne
    private Teacher teacher;

    /**
     * @associates <{Post}>
     * This is a collection with the actual post of the course
     */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Collection<Post> coursePosts;

    /**
     * This is the actual Subject of the course
     */
    @ManyToOne
    private Subject subjects;

    //Constructors
    public Course() {
        super();
    }

    public Course(Integer courseId, String name, Date startDate, Boolean isVisible, Boolean isPrivate) {
        this.courseId = courseId;
        this.name = name;
        this.startDate = startDate;
        this.isVisible = isVisible;
        this.isPrivate = isPrivate;
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
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.courseId);
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.startDate);
        hash = 83 * hash + Objects.hashCode(this.isVisible);
        hash = 83 * hash + Objects.hashCode(this.isPrivate);
        hash = 83 * hash + Objects.hashCode(this.courseStudents);
        hash = 83 * hash + Objects.hashCode(this.teacher);
        hash = 83 * hash + Objects.hashCode(this.coursePosts);
        hash = 83 * hash + Objects.hashCode(this.subjects);
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
        final Course other = (Course) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.courseId, other.courseId)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.isVisible, other.isVisible)) {
            return false;
        }
        if (!Objects.equals(this.isPrivate, other.isPrivate)) {
            return false;
        }
        if (!Objects.equals(this.courseStudents, other.courseStudents)) {
            return false;
        }
        if (!Objects.equals(this.teacher, other.teacher)) {
            return false;
        }
        if (!Objects.equals(this.coursePosts, other.coursePosts)) {
            return false;
        }
        if (!Objects.equals(this.subjects, other.subjects)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course{" + "courseId=" + courseId + ", name=" + name + ", startDate=" + startDate + ", isVisible=" + isVisible + ", isPrivate=" + isPrivate + ", courseStudents=" + courseStudents + ", teacher=" + teacher + ", coursePosts=" + coursePosts + ", subject=" + subjects + '}';
    }

}
