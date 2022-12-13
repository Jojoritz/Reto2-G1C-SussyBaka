package entities;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 * JPA for the entity {@link Comment} This class contains this attributes
 * {@code ID}, {@code commentedStudent}, {@code commentedPost},
 * {@code dateComment}, {@code commentText}
 *
 *
 * @author Henrique Yeguo
 */
@Entity
@Table(name = "comment", schema = "reto2_g1c_sussybaka")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Embedded ID field for the comment entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Relationship Many-{@link Comment} -- One-{@link Student}
     */
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student commentedStudent;

    /**
     * Relationship Many-{@link Comment} -- One-{@link Post}
     */
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post commentedPost;

    /**
     * {@link Timestamp} field saves the time when the {@code Comment} was
     * created
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @NotNull
    private Timestamp dateComment;

    /**
     * String field containing the text of the comment
     */
    @NotNull
    private String commentText;

    /**
     * {@link Comment} class empty constructor
     */
    public Comment() {
        super();
    }

    /**
     * Gets the comment compose ID
     *
     * @return Compose ID of the comment
     */
    public Integer getCommentId() {
        return id;
    }

    /**
     * Sets the comment compose ID
     *
     * @param id Passes ID for the comment
     */
    public void setCommentId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the {@link Student} that created the comment
     *
     * @return The student owner of the comment
     */
    public Student getCommentedStudent() {
        return commentedStudent;
    }

    /**
     * Sets the {@link Student} who owns the {@link Comment}
     *
     * @param commentedStudent
     */
    public void setCommentedStudent(Student commentedStudent) {
        this.commentedStudent = commentedStudent;
    }

    /**
     * Gets the {@link Post} where the {@link Comment} was created on
     *
     * @return The post where the comment is posted
     */
    public Post getCommentedPost() {
        return commentedPost;
    }

    /**
     * Sets the {@link Post}
     *
     * @param commentedPost Passes the {@code Post} where the {@link Comment}
     * was created on
     */
    public void setCommentedPost(Post commentedPost) {
        this.commentedPost = commentedPost;
    }

    /**
     * Gets the creation date of the {@link Comment}
     *
     * @return {@link Timestamp} with the date when the comment was created
     */
    public Timestamp dateComment() {
        return dateComment;
    }

    /**
     * Sets the date when the comment was created
     *
     * @param dateComment Passes the timestamp with the creation date
     */
    public void setDate(Timestamp dateComment) {
        this.dateComment = dateComment;
    }

    /**
     * Gets the comment text
     *
     * @return String with the text of the comment
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * Sets the comment test
     *
     * @param commentText Passes the text of the comment
     */
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    /**
     * Implementation of the method hashCode, this will return a {@code int}
     * with the hashCode of this class
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.commentedStudent);
        hash = 13 * hash + Objects.hashCode(this.commentedPost);
        hash = 13 * hash + Objects.hashCode(this.dateComment);
        hash = 13 * hash + Objects.hashCode(this.commentText);
        return hash;
    }

    /**
     * This implementation will compare between two objects all the attributes,
     * if all of them is {@code True} it will return that, if not returns
     * {@code False}
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
        final Comment other = (Comment) obj;
        if (!Objects.equals(this.commentText, other.commentText)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.commentedStudent, other.commentedStudent)) {
            return false;
        }
        if (!Objects.equals(this.commentedPost, other.commentedPost)) {
            return false;
        }
        if (!Objects.equals(this.dateComment, other.dateComment)) {
            return false;
        }
        return true;
    }

    /**
     * This implementation returns a concatenated String of all attributes
     *
     * @return Concatenated string with all the attributes
     */
    @Override
    public String toString() {
        return "Comment{" + "Id=" + id + ", commentedStudent=" + commentedStudent + ", commentedPost=" + commentedPost + ", dateComment=" + dateComment + ", commentText=" + commentText + '}';
    }

}
