package com.example.demo.Qna.Service;

import com.example.demo.Qna.DTO.PostDTO;
import com.example.demo.Qna.Entity.Post;
import com.example.demo.Qna.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    public Post save(PostDTO.request postDTO)
    {
        postDTO.setPassword(passwordEncoder.encode(postDTO.getPassword()));
        Post post = PostDTO.request.toPost(postDTO);
        return postRepository.save(post);
    }
    public void update(PostDTO.request postDTO)
    {
        Post post = findPostById(postDTO.getId());
        if(passwordEncoder.matches(postDTO.getPassword(), post.getPassword())) {
            postDTO.setPassword(passwordEncoder.encode(postDTO.getPassword()));
            post.updatePost(postDTO);
            postRepository.save(post);
        }
        else {
            throw new IllegalArgumentException("암호가 틀렸습니다.");
        }
    }
    public void delete(Long id, String password)
    {
        Post post = findPostById(id);
        if(passwordEncoder.matches(password, post.getPassword())) {
            postRepository.delete(post);
        }
        else {
            throw new IllegalArgumentException("암호가 틀렸습니다.");
        }
    }

    public Post findPostById(Long id)
    {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        return post;
    }

}
