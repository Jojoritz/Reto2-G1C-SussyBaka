package model;

import java.io.Serializable;

import java.util.Collection;

public class Subject implements Serializable {
    private Integer subject_id;
    private String name;
    private String type;
    private String century;

    private String level;

    /**
     * @associates <{model.Course}>
     */
    private Collection courseWithSubject;

    public Subject() {
        super();
    }
}
