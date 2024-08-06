package com.example.demo;

import com.example.demo.Blog.Repository.BlogRepository;
import com.example.demo.Blog.Service.BlogService;
import com.example.demo.Blog.DTO.BlogDTO;
import com.example.demo.Blog.Entity.BlogPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class BlogServiceTest {
    @Mock
    private BlogRepository blogRepository;
    @InjectMocks
    private BlogService blogService;

    @BeforeEach
    public void setUp() throws IOException{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBlogPostTest() throws IOException{
        //given
        String title = "게시글1";
        String mdfile = "# 제목 1\\n\\n이것은 \\\"Markdown\\\" 파일입니다.\\n\\n\\\\ 특별한 문자를 포함할 수 있습니다.";
        byte[] content = mdfile.getBytes();

        BlogDTO blogDTO = new BlogDTO(title, mdfile);
        BlogPost blogEntity = BlogDTO.toBlogPost(blogDTO);
        when(blogRepository.save(any(BlogPost.class))).thenReturn(blogEntity);

        BlogPost blogPost = blogService.saveBlogPost(blogDTO);


        assertArrayEquals(content, blogPost.getContent());
    }

}
