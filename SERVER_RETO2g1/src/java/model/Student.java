package model;

import java.util.Collection;

public class Student extends User{
    private static final long serialVersionUID = 1L;

    /**
     * @associates <{model.Course}>
     */
    private Collection studyingCourses;

    public Student() {
        super();
    }
}
