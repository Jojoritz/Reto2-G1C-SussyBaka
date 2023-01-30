package client.beans;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 *
 * @author Henrique Yeguo
 */
@XmlRootElement(name = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID field for post entity
     */
    private Integer postId;

    /**
     * Post title
     */
    private final SimpleStringProperty title;
    /**
     * Content field, contains the data in the post
     */
    private String content;

    /**
     * Publication date field, contains when the post date was created
     */
    private final SimpleObjectProperty<Date> publicationDate;

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
    private final SimpleStringProperty video;

    /**
     * List containing all the {@link Comments} a {@link Post} has
     *
     * @associates <{model.Comment}>
     */
    private Set<Comment> postComments;

    public Post() {
        this.title = new SimpleStringProperty();
        this.publicationDate = new SimpleObjectProperty();
        this.video = new SimpleStringProperty();
    }

    /**
     * {@link Post} Constructor
     *
     * @param title
     * @param publicationDate
     * @param video
     */
    public Post(String title, Date publicationDate, String video) {
        this.title = new SimpleStringProperty(title);
        this.publicationDate = new SimpleObjectProperty(publicationDate);
        this.video = new SimpleStringProperty(video);
    }

    public Post(Integer postId, String title, String content, Date publicationDate, String image, String video, Course course) {
        this.title = new SimpleStringProperty(title);
        this.publicationDate = new SimpleObjectProperty(publicationDate);
        this.video = new SimpleStringProperty(video);
        this.postId = postId;
        this.content = content;
        this.image = image;
        this.course = course;
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
    @XmlElement(name = "title")
    public String getTitle() {
        return this.title.get();
    }

    /**
     * Sets the title for the post
     *
     * @param title String value with the title for the post
     */
    public void setTitle(String title) {
        this.title.set(title);
    }

    /**
     * Gets the content text
     *
     * @return content Content
     */
    @XmlElement(name = "content")
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
    @XmlElement(name = "publicationDate")
    public Date getPublicationDate() {
        return this.publicationDate.get();
    }

    /**
     * Sets the publication date
     *
     * @param publicationDate PublicationDate
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate.set(publicationDate);
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
    @XmlElement(name = "image")
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
    @XmlElement(name = "video")
    public String getVideo() {
        return this.video.get();
    }

    /**
     * Sets the video path
     *
     * @param video Video
     */
    public void setVideo(String video) {
        this.video.set(video);
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
        return Objects.equals(this.postId, other.postId);
    }

    /**
     * This implementation returns a concatenated String of all attributes
     *
     * @return Concatenated string with all the attributes
     */
    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", title=" + title + ", content=" + content + ", publicationDate=" + publicationDate + ", course=" + course + ", image=" + image + ", video=" + video + ", postComments=" + postComments + '}';
    }
}
