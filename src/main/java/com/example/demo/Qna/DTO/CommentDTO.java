package com.example.demo.Qna.DTO;

import com.example.demo.Qna.Entity.Comment;
import com.example.demo.Qna.Entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class CommentDTO {
    @Setter
    @Getter
    @Builder
    public static class Request {
        private Long id;
        private String writer;
        private String password;
        private String content;
        private Long parentPostId;
        private Long parentCommentId;
    }

    public static class Response {
    }

}
