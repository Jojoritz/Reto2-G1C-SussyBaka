package model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collection;

public class Course implements Serializable {
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
}
