package model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collection;

public class Post implements Serializable {
    private Integer post_id;
    private String content;
    private Timestamp publication_date;
    private Course course;

    private String image;
    private String video;

    /**
     * @associates <{model.Comment}>
     */
    private Collection postComments;


    public Post() {
        super();
    }
}
