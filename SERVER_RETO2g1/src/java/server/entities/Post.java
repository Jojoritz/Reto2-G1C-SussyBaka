package server.entities;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * JPA for the entity {@link Post} This class contains this attributes
 * {@code ID}, {@code content}, {@code publicationDate}, {@code course},
 * {@code postComments}, {@code image} and {@code video}
 *
 *
 * @author Henrique Yeguo
 */
@NamedQueries({
    @NamedQuery(
            name = "findByDate",
            query = "FROM Post p WHERE DATE(p.publicationDate) = DATE(:date)")
    ,
    @NamedQuery(
            name = "findByDateRange",
            query = "FROM Post p Where DATE(p.publicationDate) BETWEEN DATE(:startDate) AND DATE(:endDate)")
    ,
    @NamedQuery(
            name = "findByTitle",
            query = "FROM Post p WHERE p.title = :title")
})
@Entity
@Table(name = "post", schema = "reto2_g1c_sussybaka")
@XmlRootElement
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID field for post entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Integer postId;

    @NotNull
    private String title;

    /**
     * Content field, contains the data in the post
     */
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * Publication date field, contains when the post date was created
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @NotNull
    private Date publicationDate;

    /**
     * Link {@link Course} of the post
     */
    @ManyToOne
    @NotNull
    private Course course;

    /**
     * Image field contains the relative path to the image
     */
    @Column(name = "image_path")
    @Null
    private String image;

    /**
     * Video field contains the relative path to the video
     */
    @Column(name = "video_path")
    @Null
    private String video;

    /**
     * List containing all the {@link Comments} a {@link Post} has
     *
     * @associates <{model.Comment}>
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> postComments;

    /**
     * {@link Post} class empty constructor
     */
    public Post() {
        super();
    }

    /**
     * Gets the post ID
     *
     * @return Returns the ID of the post
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * Sets the post ID
     *
     * @param postId Passes the ID value of the post
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    /**
     * Gets the title of the post
     *
     * @return Returns the title of the post
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title for the post
     *
     * @param title String value with the title for the post
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content text
     *
     * @return content Content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content text
     *
     * @param content Content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the publication date
     *
     * @return publicationDate Publication Date
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * Sets the publication date
     *
     * @param publicationDate PublicationDate
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Gets the course this post is posted
     *
     * @return course Course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets the course this post is posted
     *
     * @param course Course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Gets the image path
     *
     * @return image Image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image path
     *
     * @param image Image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the video path
     *
     * @return video Video
     */
    public String getVideo() {
        return video;
    }

    /**
     * Sets the video path
     *
     * @param video Video
     */
    public void setVideo(String video) {
        this.video = video;
    }

    /**
     * Gets the comments that the post has
     *
     * @return postComments
     */
    @XmlTransient
    public Set<Comment> getPostComments() {
        return postComments;
    }

    /**
     * Sets the comments on the post
     *
     * @param postComments PostComments
     */
    public void setPostComments(Set<Comment> postComments) {
        this.postComments = postComments;
    }

    /**
     * Implementation of the method hashCode, this will return a {@code int}
     * with the hashCode of this class
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.postId);
        hash = 97 * hash + Objects.hashCode(this.content);
        hash = 97 * hash + Objects.hashCode(this.publicationDate);
        hash = 97 * hash + Objects.hashCode(this.course);
        hash = 97 * hash + Objects.hashCode(this.image);
        hash = 97 * hash + Objects.hashCode(this.video);
        hash = 97 * hash + Objects.hashCode(this.postComments);
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
        final Post other = (Post) obj;
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.video, other.video)) {
            return false;
        }
        if (!Objects.equals(this.postId, other.postId)) {
            return false;
        }
        if (!Objects.equals(this.publicationDate, other.publicationDate)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.postComments, other.postComments)) {
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
        return "Post{" + "postId=" + postId + ", content=" + content + ", publicationDate=" + publicationDate + ", course=" + course + ", image=" + image + ", video=" + video + ", postComments=" + postComments + '}';
    }

}
