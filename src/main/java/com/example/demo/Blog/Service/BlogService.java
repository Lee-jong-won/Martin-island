package com.example.demo.Blog.Service;

import com.example.demo.Blog.Repository.BlogRepository;
import com.example.demo.Blog.DTO.BlogDTO;
import com.example.demo.Blog.Entity.BlogPost;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public BlogPost saveBlogPost(BlogDTO blogDTO) throws IOException
    {
        BlogPost blogPost = BlogDTO.toBlogPost(blogDTO);
        return blogRepository.save(blogPost);
    }

    public byte[] findBlogPost(Long id)
    {
        Optional<BlogPost> optionalBlogPost = blogRepository.findById(id);
        if(optionalBlogPost.isPresent() == true)
        {
            BlogPost blogPost = optionalBlogPost.get();
            return blogPost.getContent();
        }
        else
            return null;
    }











}
