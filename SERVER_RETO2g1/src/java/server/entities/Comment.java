package server.entities;

import java.io.Serializable;
import java.util.Date;

import java.util.Objects;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@NamedQueries({
    @NamedQuery(
            name = "getCommentsByPostID",
            query = "SELECT new server.entities.dto.CommentDTO(c.commentID, s.fullName, c.dateComment, c.commentText, s.email, s.id, p.postId) "
            + "FROM Comment c JOIN c.student s JOIN c.post p Where c.post.postId = :postId")
    ,
     @NamedQuery(
            name = "getCommentsByID",
            query = "SELECT new server.entities.Comment(c.commentID, c.dateComment, c.commentText) "
            + "FROM Comment c Where c.commentID = :commentId")
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
    @ManyToOne
    private Student student;

    /**
     * Relation object to {@link Post}
     */
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
    @Column(columnDefinition = "TEXT")
    private String commentText;

    /**
     * {@link Comment} class empty constructor
     */
    public Comment() {
        super();
    }

    /**
     * Class constructor without both {@link Post} and {@link Student} relational objects
     *
     * @param commentID The id of the comment
     * @param dateComment Date of creation of the comment
     * @param commentText Text of the comment
     */
    public Comment(Integer commentID, Date dateComment, String commentText) {
        this.commentID = commentID;
        this.dateComment = dateComment;
        this.commentText = commentText;
    }

    /**
     * Class constructor without {@link Post} relational object
     *
     * @param commentID The id of the comment
     * @param student The relational object to student
     * @param dateComment Date of creation of the comment
     * @param commentText Text of the comment
     */
    public Comment(Integer commentID, Student student, Date dateComment, String commentText) {
        this.commentID = commentID;
        this.student = student;
        this.dateComment = dateComment;
        this.commentText = commentText;
    }

    /**
     * Class constructor with all attributes and relational objects
     *
     * @param commentID The id of the comment
     * @param student The relational object to student
     * @param dateComment Date of creation of the comment
     * @param commentText Text of the comment
     * @param post The relational object to the Post
     */
    public Comment(Integer commentID, Student student, Date dateComment, String commentText, Post post) {
        this.commentID = commentID;
        this.student = student;
        this.dateComment = dateComment;
        this.commentText = commentText;
        this.post = post;
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
