package com.example.demo.Qna.Service;

import com.example.demo.Qna.DTO.PostDTO;
import com.example.demo.Qna.Entity.Post;
import com.example.demo.Qna.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    public Post save(PostDTO.request postDTO)
    {
        Post post = PostDTO.request.toPost(postDTO);
        return postRepository.save(post);
    }

    public void update(Long id, PostDTO.request postDTO)
    {
        Post post = postRepository.findById(id).orElseThrow(() ->
        new IllegalArgumentException("해당 게시물이 존재하지 않습니다. id=" + id));
        post.updatePost(postDTO.getTitle(), postDTO.getContent());
    }

    public void delete(Long id)
    {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        postRepository.delete(post);
    }

    public Post findById(Long id)
    {
        Post post = postRepository.findById(id).get();
        return post;
    }
}
