package com.example.demo.Qna.Entity;
import com.example.demo.Qna.DTO.PostDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String writer;
    private String title;
    private String password;
    private String content;

    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();
    public void addComment(Comment comment)
    {
        comments.add(comment);
        comment.assignToPost(this);
    }
    public void removeComment(Comment comment)
    {
        comments.remove(comment);
        comment.removeFromPost();
    }
    public void updatePost(PostDTO.request request)
    {
        if(request.getWriter() != null)
            this.writer = request.getWriter();
        if(request.getTitle() != null)
            this.title = request.getTitle();
        if(request.getContent() != null)
            this.content = request.getContent();
        if(request.getPassword() != null)
            this.password = request.getPassword();
    }


}
