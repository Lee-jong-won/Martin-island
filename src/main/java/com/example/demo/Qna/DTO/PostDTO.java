package com.example.demo.Qna.DTO;

import com.example.demo.Qna.Entity.Post;
import lombok.Getter;

public class PostDTO {

    @Getter
    public static class request {
        private String title;
        private String writer;
        private String content;

        public static Post toPost(PostDTO.request request)
        {
            return Post.builder().writer(request.writer)
                    .title(request.title)
                    .content(request.content).build();
        }
    }

}
