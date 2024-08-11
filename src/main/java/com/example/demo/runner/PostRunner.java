package com.example.demo.runner;

import com.example.demo.Qna.DTO.CommentDTO;
import com.example.demo.Qna.DTO.PostDTO;
import com.example.demo.Qna.Entity.Comment;
import com.example.demo.Qna.Entity.Post;
import com.example.demo.Qna.Service.CommentService;
import com.example.demo.Qna.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostRunner implements ApplicationRunner {

    private final CommentService commentService;
    private final PostService postService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 30; i++)
        {
            PostDTO.request postRequest = PostDTO.request.builder()
                    .title("testTitle" + i)
                    .writer("testWriter" + i)
                    .content("testContent" + i)
                    .password("testPassword")
                    .build();
            Post newPost = postService.save(postRequest);

            CommentDTO.Request commentRequest = CommentDTO.Request.builder()
                    .parentPostId(newPost.getId())
                    .writer("testWriter" + i)
                    .password("testPassword" + i)
                    .content("testContent" + i)
                    .build();

            Comment newComment = commentService.saveComment(commentRequest);
        }
    }

}
