package com.example.demo.Blog.Controller;

import com.example.demo.Blog.Service.BlogService;
import com.example.demo.Blog.DTO.BlogDTO;
import com.example.demo.Blog.Entity.BlogPost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/blog/{id}")
    public ResponseEntity<byte[]> getMarkDownFile(@PathVariable Long id)
    {
        byte[] mdcode = blogService.findBlogPost(id);

        if(mdcode == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Blog post not found".getBytes());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_MARKDOWN); // MD 파일의 MIME 타입 설정
        headers.setContentLength(mdcode.length);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(mdcode);
    }


    @PostMapping("/blog/write")
    public ResponseEntity<String> createBlogPost(@RequestBody BlogDTO blogDTO)
    {
        //md 파일인지 아닌지 유효성 검증하기!

        //md 파일 저장!
        try{
            BlogPost newPost = blogService.saveBlogPost(blogDTO);
            Long newId = newPost.getId();
            String redirectUrl = "/blog/" + newId;

            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", redirectUrl)
                    .body("Redirecting to " + redirectUrl);
        }
        catch(IOException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }

    }
}



