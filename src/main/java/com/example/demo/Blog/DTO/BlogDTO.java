package com.example.demo.Blog.DTO;

import com.example.demo.Blog.Entity.BlogPost;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Blob;

@Getter
@AllArgsConstructor
public class BlogDTO {

    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;

    public static BlogPost toBlogPost(BlogDTO blogDTO)
    {
        return BlogPost.builder().content(blogDTO.getContent().getBytes())
                .name(blogDTO.getTitle())
                .build();
    }
}
