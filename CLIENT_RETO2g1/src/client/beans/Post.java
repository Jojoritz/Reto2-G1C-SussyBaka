package client.beans;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 *
 * @author Henrique Yeguo
 */
@XmlRootElement
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID field for post entity
     */
    private Integer postId;

    /**
     * Post title
     */
    private String title;

    /**
     * Content field, contains the data in the post
     */
    private String content;

    /**
     * Publication date field, contains when the post date was created
     */
    private Date publicationDate;

    /**
     * Link {@link Course} of the post
     */
    private Course course;

    /**
     * Image field contains the relative path to the image
     */
    private String image;

    /**
     * Video field contains the relative path to the video
     */
    private String video;

    /**
     * List containing all the {@link Comments} a {@link Post} has
     *
     * @associates <{model.Comment}>
     */
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
        return hash;
    }

    /**
     * This implementation will compare between two ID of the entity
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
        if (!Objects.equals(this.postId, other.postId)) {
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
