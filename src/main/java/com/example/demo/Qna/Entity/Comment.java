package com.example.demo.Qna.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> childrenComment = new ArrayList<>(); //자식 댓글들(대댓글)

    public void update(String content){
        this.content = content;
    }

    // 양방향 관계 설정을 위한 메서드 (패키지 프라이빗으로 설정)
    public void assignToPost(Post post) {
        this.parentPost = post;
    }

    // 양방향 관계 해제를 위한 메서드 (패키지 프라이빗으로 설정)
    public void removeFromPost() {
        this.parentPost = null;
    }

    public void removeChildren(Comment comment) {
        childrenComment.remove(comment);
        comment.parentComment = null;
    }

    public void updateComment(String content)
    {
        if(content != null)
            this.content = content;
    }

}
