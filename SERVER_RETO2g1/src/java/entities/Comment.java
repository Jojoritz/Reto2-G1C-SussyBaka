package entities;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.Objects;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    private Student commentedStudent;
    private Post commentedPost;
    private Timestamp date;
    private String commentText;
    

    public Comment() {
        super();
    }

    public Student getCommentedStudent() {
        return commentedStudent;
    }

    public void setCommentedStudent(Student commentedStudent) {
        this.commentedStudent = commentedStudent;
    }

    public Post getCommentedPost() {
        return commentedPost;
    }

    public void setCommentedPost(Post commentedPost) {
        this.commentedPost = commentedPost;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.commentedStudent);
        hash = 89 * hash + Objects.hashCode(this.commentedPost);
        hash = 89 * hash + Objects.hashCode(this.date);
        hash = 89 * hash + Objects.hashCode(this.commentText);
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
        final Comment other = (Comment) obj;
        if (!Objects.equals(this.commentText, other.commentText)) {
            return false;
        }
        if (!Objects.equals(this.commentedStudent, other.commentedStudent)) {
            return false;
        }
        if (!Objects.equals(this.commentedPost, other.commentedPost)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Comment{" + "commentedStudent=" + commentedStudent + ", commentedPost=" + commentedPost + ", date=" + date + ", commentText=" + commentText + '}';
    }

}
