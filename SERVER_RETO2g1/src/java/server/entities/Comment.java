package server.entities;

import java.io.Serializable;
import java.util.Date;

import java.util.Objects;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * JPA for the entity {@link Comment} This class contains this attributes
 * {@code ID}, {@code commentedStudent}, {@code commentedPost},
 * {@code dateComment}, {@code commentText}
 *
 * @author Henrique Yeguo
 */
@NamedQueries(
        @NamedQuery(
                name = "getCommentsByPostID",
                query = "FROM Comment c WHERE c.post.postId = :idPost")
)
@Entity
@Table(name = "student_post_comment", schema = "reto2_g1c_sussybaka")
@XmlRootElement
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "comment_id")
    private Integer commentID;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Post post;

    /**
     * {@link Timestamp} field saves the time when the {@code Comment} was
     * created
     *
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @NotNull
    private Date dateComment;

    /**
     * String field containing the text of the comment
     *
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
     * Gets the creation date of the {@link Comment}
     *
     * @return {@link Timestamp} with the date when the comment was created
     */
    public Date dateComment() {
        return dateComment;
    }

    /**
     * Sets the date when the comment was created
     *
     * @param dateComment Passes the timestamp with the creation date
     */
    public void setDate(Date dateComment) {
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
        hash = 79 * hash + Objects.hashCode(this.commentID);
        hash = 79 * hash + Objects.hashCode(this.student);
        hash = 79 * hash + Objects.hashCode(this.post);
        hash = 79 * hash + Objects.hashCode(this.dateComment);
        hash = 79 * hash + Objects.hashCode(this.commentText);
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
        if (!Objects.equals(this.commentID, other.commentID)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.post, other.post)) {
            return false;
        }
        if (!Objects.equals(this.dateComment, other.dateComment)) {
            return false;
        }
        return true;
    }

    /**
     * Gets a concatenated string with the values of all atributes
     *
     * @return Concatenated string with all attributes
     */
    @Override
    public String toString() {
        return "Comment{" + "commentID=" + commentID + ", student=" + student + ", post=" + post + ", dateComment=" + dateComment + ", commentText=" + commentText + '}';
    }

}
