/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.entities.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import server.entities.Course;
import server.entities.Subject;
import server.entities.Teacher;

/**
 *
 * @author Joritz
 */
public class CourseDTO {

    public Integer courseId;
    public String courseName;
    public Date startDate;
    public Boolean isVisible;
    public Boolean isPrivate;
    public String teachName;
    public String subName;

    public CourseDTO(Integer courseId, String courseName, Date startDate, boolean isVisible, boolean isPrivate, String teachName, String subName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.startDate = startDate;
        this.isVisible = isVisible;
        this.isPrivate = isPrivate;
        this.teachName = teachName;
        this.subName = subName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
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

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.courseId);
        hash = 47 * hash + Objects.hashCode(this.courseName);
        hash = 47 * hash + Objects.hashCode(this.startDate);
        hash = 47 * hash + Objects.hashCode(this.isVisible);
        hash = 47 * hash + Objects.hashCode(this.isPrivate);
        hash = 47 * hash + Objects.hashCode(this.teachName);
        hash = 47 * hash + Objects.hashCode(this.subName);
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
        final CourseDTO other = (CourseDTO) obj;
        if (!Objects.equals(this.courseName, other.courseName)) {
            return false;
        }
        if (!Objects.equals(this.teachName, other.teachName)) {
            return false;
        }
        if (!Objects.equals(this.subName, other.subName)) {
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
        return true;
    }

    @Override
    public String toString() {
        return "CourseDTO{" + "courseId=" + courseId + ", courseName=" + courseName + ", startDate=" + startDate + ", isVisible=" + isVisible + ", isPrivate=" + isPrivate + ", teachName=" + teachName + ", subName=" + subName + '}';
    }

    public static Course convertDTOCourse(CourseDTO dto) {
            Teacher teacher = new Teacher();
            teacher.setFullName(dto.getTeachName());
            Subject subject = new Subject();
            subject.setName(dto.getSubName());
            Course course = new Course(dto.getCourseId(), dto.getCourseName(), dto.getStartDate(),
                    dto.getIsVisible(), dto.getIsPrivate(), teacher, subject);
        return course;
    }

}
