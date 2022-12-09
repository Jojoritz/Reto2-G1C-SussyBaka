package model;

import java.util.Collection;

public class Teacher extends User {
    private static final long serialVersionUID = 1L;

    /**
     * @associates <{model.Course}>
     */
    private Collection teachingCourses;


    public Teacher() {
        super();
    }
}
