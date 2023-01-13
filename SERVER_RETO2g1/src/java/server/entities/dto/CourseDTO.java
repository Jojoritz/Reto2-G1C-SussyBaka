/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.entities.dto;

import java.util.Date;

/**
 *
 * @author Joritz
 */
public class CourseDTO {
    public Integer courseId;
    public String courseName;
    public Date startDate;
    public boolean isVisible;
    public boolean isPrivate;
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
    
}
