package com.example.demo.Qna.Service;

import com.example.demo.Qna.DTO.CommentDTO;
import com.example.demo.Qna.Entity.Comment;
import com.example.demo.Qna.Entity.Post;
import com.example.demo.Qna.Repository.CommentRepository;
import com.example.demo.Qna.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Long saveComment(CommentDTO.Request request)
    {
        Post post = postRepository.findById(request.getParentPostId()).orElseThrow();
        Long commentId = null;

        if(request.getParentCommentId() == null) // 부모 댓글이 존재하지 않는 경우
        {
            commentRepository.save(Comment.builder()
                    .writer(request.getWriter())
                    .password(request.getPassword())
                    .content(request.getContent())
                    .parentPost(post)
                    .build());

        }
        else // 부모 댓글이 존재하는 경우
        {
            Comment commentParent = commentRepository.findById(request.getParentPostId()).orElseThrow();

            commentRepository.save(Comment.builder()
                    .writer(request.getWriter())
                    .password(request.getPassword())
                    .content(request.getContent())
                    .parentPost(post)
                    .parentComment(commentParent)
                    .build());
        }

        return request.getParentPostId();
    }


}
