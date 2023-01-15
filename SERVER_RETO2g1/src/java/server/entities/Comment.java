package server.entities;

import java.io.Serializable;
import java.util.Date;

import java.util.Objects;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * JPA for the entity {@link Comment} This class contains this attributes
 * {@code ID}, {@code commentedStudent}, {@code commentedPost},
 * {@code dateComment}, {@code commentText}
 *
 * @author Henrique Yeguo
 */
@NamedQueries({
    @NamedQuery(
            name = "getCommentsByPostID",
            query = "SELECT c FROM Comment c JOIN c.student s JOIN c.post p Where c.post.postId = :postId")
})
@Entity
@Table(name = "student_post_comment", schema = "reto2_g1c_sussybaka")
@XmlRootElement
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID of the comment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Integer commentID;

    /**
     * Relational object to {@link Student}
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    /**
     * Relation object to {@link Post}
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
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
    @Column(columnDefinition = "TEXT")
    private String commentText;

    /**
     * {@link Comment} class empty constructor
     */
    public Comment() {
        super();
    }

    /**
     * Gets the comment Id
     *
     * @return Returns the Id of the comment
     */
    public Integer getCommentID() {
        return commentID;
    }

    /**
     * Sets the comment ID
     *
     * @param commentID The ID of the comment
     */
    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    /**
     * Gets the student who wrote the comment
     *
     * @return Returns the student object
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Sets the student who wrote the comment
     *
     * @param student Passes the student who wrote the comment
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Gets the post of the comment
     *
     * @return Returns the post where the comment is posted
     */
    @XmlTransient
    public Post getPost() {
        return post;
    }

    /**
     * Sets the post where the comment is posted
     *
     * @param post Sets the post where the comment is posted
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * Gets the creation date of the {@link Comment}
     *
     * @return {@link Timestamp} with the date when the comment was created
     */
    public Date getDateComment() {
        return dateComment;
    }

    /**
     * Sets the date when the comment was created
     *
     * @param dateComment Passes the timestamp with the creation date
     */
    public void setDateComment(Date dateComment) {
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
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.commentID);
        hash = 59 * hash + Objects.hashCode(this.student);
        hash = 59 * hash + Objects.hashCode(this.post);
        hash = 59 * hash + Objects.hashCode(this.dateComment);
        hash = 59 * hash + Objects.hashCode(this.commentText);
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
     * Gets a concatenated string with the values of all attributes
     *
     * @return Concatenated string with all attributes
     */
    @Override
    public String toString() {
        return "Comment{" + "commentID=" + commentID + ", student=" + student + ", post=" + post + ", dateComment=" + dateComment + ", commentText=" + commentText + '}';
    }

}
