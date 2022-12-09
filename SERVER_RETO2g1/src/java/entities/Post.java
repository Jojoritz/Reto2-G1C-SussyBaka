package entities;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collection;
import java.util.Objects;

public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer postId;
    private String content;
    private Timestamp publicationDate;
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Collection getPostComments() {
        return postComments;
    }

    public void setPostComments(Collection postComments) {
        this.postComments = postComments;
    }

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

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", content=" + content + ", publicationDate=" + publicationDate + ", course=" + course + ", image=" + image + ", video=" + video + ", postComments=" + postComments + '}';
    }

}
