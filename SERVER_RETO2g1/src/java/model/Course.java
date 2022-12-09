package model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collection;
import java.util.Objects;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer courseId;
    private String name;
    private Timestamp startDate;
    private Boolean isVisible;
    private Boolean isPrivate;

    /**
     * @associates <{model.Student}>
     */
    private Collection courseStudents;
    private Teacher teacher;

    /**
     * @associates <{model.Post}>
     */
    private Collection coursePosts;
    private Subject subject;

    public Course() {
        super();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Collection getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(Collection courseStudents) {
        this.courseStudents = courseStudents;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Collection getCoursePosts() {
        return coursePosts;
    }

    public void setCoursePosts(Collection coursePosts) {
        this.coursePosts = coursePosts;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

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
        hash = 83 * hash + Objects.hashCode(this.subject);
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
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course{" + "courseId=" + courseId + ", name=" + name + ", startDate=" + startDate + ", isVisible=" + isVisible + ", isPrivate=" + isPrivate + ", courseStudents=" + courseStudents + ", teacher=" + teacher + ", coursePosts=" + coursePosts + ", subject=" + subject + '}';
    }

}
