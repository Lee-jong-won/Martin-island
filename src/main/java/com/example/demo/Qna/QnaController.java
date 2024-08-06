package com.example.demo.Qna;

import com.example.demo.Blog.Entity.BlogPost;
import com.example.demo.Qna.DTO.CommentDTO;
import com.example.demo.Qna.DTO.PostDTO;
import com.example.demo.Qna.Entity.Comment;
import com.example.demo.Qna.Entity.Post;
import com.example.demo.Qna.Service.CommentService;
import com.example.demo.Qna.Service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@AllArgsConstructor
@Controller
public class QnaController {

    public final PostService postService;
    public final CommentService commentService;

    @PostMapping("/qna/create-post")
    public ResponseEntity<String> createPost(@RequestBody PostDTO.request postDTO)
    {
        Post post = postService.save(postDTO);
        Long newId = post.getId();
        String redirectUrl = "/qna/post" + newId;

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", redirectUrl)
                .body("Redirecting to " + redirectUrl);
    }

    @PostMapping("/qna/post/{id}/create-comment")
    public ResponseEntity<String> createComment(@RequestBody CommentDTO.Request request)
    {
        commentService.saveComment(request);
        String redirectUrl = "/qna/post" + request.getParentPostId();

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", redirectUrl)
                .body("Redirecting to " + redirectUrl);
    }

    @GetMapping("/qna/post/{id}")
    public ResponseEntity<>


}
