package client.beans;

import java.io.Serializable;
import java.util.Date;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 *
 *
 * @author Henrique Yeguo
 */
@XmlRootElement
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    private final SimpleIntegerProperty commentID;
    private final SimpleObjectProperty<Student> student;
    private final SimpleObjectProperty<Post> post;
    private final SimpleObjectProperty<Date> dateComment;
    private final SimpleStringProperty commentText;

    public Comment() {
        this.commentID = new SimpleIntegerProperty();
        this.student = new SimpleObjectProperty();
        this.post = new SimpleObjectProperty();
        this.dateComment = new SimpleObjectProperty();
        this.commentText = new SimpleStringProperty();
    }

    public Comment(Integer commentID, Student student, Post post, Date dateComment, String commentText) {
        this.commentID = new SimpleIntegerProperty(commentID);
        this.student = new SimpleObjectProperty(student);
        this.post = new SimpleObjectProperty(post);
        this.dateComment = new SimpleObjectProperty(dateComment);
        this.commentText = new SimpleStringProperty(commentText);
    }

    /**
     * Gets the comment Id
     *
     * @return Returns the Id of the comment
     */
    public Integer getCommentID() {
        return this.commentID.get();
    }

    /**
     * Sets the comment ID
     *
     * @param commentID The ID of the comment
     */
    public void setCommentID(Integer commentID) {
        this.commentID.set(commentID);
    }

    /**
     * Gets the student who wrote the comment
     *
     * @return Returns the student object
     */
    public Student getStudent() {
        return this.student.get();
    }

    /**
     * Sets the student who wrote the comment
     *
     * @param student Passes the student who wrote the comment
     */
    public void setStudent(Student student) {
        this.student.set(student);
    }

    /**
     * Gets the post of the comment
     *
     * @return Returns the post where the comment is posted
     */
    public Post getPost() {
        return this.post.get();
    }

    /**
     * Sets the post where the comment is posted
     *
     * @param post Sets the post where the comment is posted
     */
    public void setPost(Post post) {
        this.post.set(post);
    }

    /**
     * Gets the creation date of the {@link Comment}
     *
     * @return {@link Timestamp} with the date when the comment was created
     */
    public Date getDateComment() {
        return this.dateComment.get();
    }

    /**
     * Sets the date when the comment was created
     *
     * @param dateComment Passes the timestamp with the creation date
     */
    public void setDateComment(Date dateComment) {
        this.dateComment.set(dateComment);
    }

    /**
     * Gets the comment text
     *
     * @return String with the text of the comment
     */
    public String getCommentText() {
        return this.commentText.get();
    }

    /**
     * Sets the comment test
     *
     * @param commentText Passes the text of the comment
     */
    public void setCommentText(String commentText) {
        this.commentText.set(commentText);
    }

    /**
     * Implementation of the method hashCode, this will return a {@code int}
     * with the hashCode of this class
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hash(commentID);
        return hash;
    }

    /**
     * Because this bean has an unique ID we can use it to compare to objects
     *
     * @param obj Object
     * @return boolean True or False if object is equal
     */
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
        Comment other = (Comment) obj;
        return Objects.equals(commentID, other.commentID);
    }

}
