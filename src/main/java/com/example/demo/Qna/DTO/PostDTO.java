package com.example.demo.Qna.DTO;

import com.example.demo.Qna.Entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PostDTO {

    @Getter
    @Setter
    @Builder
    public static class request {
        private Long id;
        private String title;
        private String password;
        private String writer;
        private String content;

        public static Post toPost(PostDTO.request request)
        {
            return Post.builder().writer(request.writer)
                    .title(request.title)
                    .password(request.password)
                    .content(request.content).build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class response {
        private String title;
        private String writer;
        private String content;

        public static PostDTO.response toResponseDTO(Post post)
        {
            return PostDTO.response.builder()
                    .title(post.getTitle())
                    .writer(post.getWriter())
                    .content(post.getContent())
                    .build();
        }

    }

}
