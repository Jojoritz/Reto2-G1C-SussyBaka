package model;

import java.io.Serializable;

import java.sql.Timestamp;

public class Comment implements Serializable {

    private Student commentedStudent;
    private Post commentedPost;
    private Timestamp date;
    private String commentText;

    public Comment() {
        super();
    }
}
