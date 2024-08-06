package com.example.demo.Qna.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String writer;
    private String content;
    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post parentPost;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parentComment; //부모 댓글
    @OneToMany(mappedBy = "parentComment", orphanRemoval = true)
    private List<Comment> childrenComment = new ArrayList<>(); //자식 댓글들(대댓글)

    public void update(String content){
        this.content = content;
    }
    public void remove()
    {
        this.parentPost = null;
    }
}
