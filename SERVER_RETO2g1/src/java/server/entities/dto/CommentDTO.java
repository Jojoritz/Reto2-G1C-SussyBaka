/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.entities.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import server.entities.Comment;
import server.entities.Post;
import server.entities.Student;

/**
 *
 * @author Henri
 */
public class CommentDTO {

    private Integer commentID;
    private String studentName;
    private Date dateComment;
    private String commentText;
    private String email;
    private Integer studentId;
    private Integer postId;

    public CommentDTO() {
    }

    public CommentDTO(Integer commentID, String studentName, Date dateComment, String commentTest, String email, Integer studentId, Integer postId) {
        this.commentID = commentID;
        this.studentName = studentName;
        this.dateComment = dateComment;
        this.commentText = commentTest;
        this.email = email;
        this.studentId = studentId;
        this.postId = postId;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentTest) {
        this.commentText = commentTest;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.commentID);
        hash = 97 * hash + Objects.hashCode(this.studentName);
        hash = 97 * hash + Objects.hashCode(this.dateComment);
        hash = 97 * hash + Objects.hashCode(this.commentText);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.studentId);
        hash = 97 * hash + Objects.hashCode(this.postId);
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
        final CommentDTO other = (CommentDTO) obj;
        if (!Objects.equals(this.studentName, other.studentName)) {
            return false;
        }
        if (!Objects.equals(this.commentText, other.commentText)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.commentID, other.commentID)) {
            return false;
        }
        if (!Objects.equals(this.dateComment, other.dateComment)) {
            return false;
        }
        if (!Objects.equals(this.studentId, other.studentId)) {
            return false;
        }
        if (!Objects.equals(this.postId, other.postId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommentDTO{" + "commentID=" + commentID + ", studentName=" + studentName + ", dateComment=" + dateComment + ", commentText=" + commentText + ", email=" + email + ", studentId=" + studentId + ", postId=" + postId + '}';
    }

    public static List<Comment> convertDTOsComment(List<CommentDTO> dtos) {
        List<Comment> comments = new ArrayList<>();
        dtos.forEach((dto) -> {
            Student student = new Student();
            student.setFullName(dto.getStudentName());
            student.setEmail(dto.getEmail());
            student.setId(dto.getStudentId());
            Post post = new Post();
            post.setPostId(dto.getPostId());
            Comment comment = new Comment(dto.getCommentID(), student, dto.getDateComment(), dto.getCommentText(), post);
            comments.add(comment);
        });
        return comments;
    }
}
