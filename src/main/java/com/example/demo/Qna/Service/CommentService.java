package com.example.demo.Qna.Service;

import com.example.demo.Qna.DTO.CommentDTO;
import com.example.demo.Qna.DTO.PostDTO;
import com.example.demo.Qna.Entity.Comment;
import com.example.demo.Qna.Entity.Post;
import com.example.demo.Qna.Repository.CommentRepository;
import com.example.demo.Qna.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    public Comment saveComment(CommentDTO.Request request)
    {
        Post post = postRepository.findById(request.getParentPostId()).orElseThrow();
        Comment comment = null;

        if(request.getParentCommentId() == null) // 부모 댓글이 존재하지 않는 경우
        {
            comment = commentRepository.save(Comment.builder()
                    .writer(request.getWriter())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .content(request.getContent())
                    .parentPost(post)
                    .build());

        }
        else // 부모 댓글이 존재하는 경우
        {
            Comment commentParent = commentRepository.findById(request.getParentCommentId()).orElseThrow();

            comment = commentRepository.save(Comment.builder()
                    .writer(request.getWriter())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .content(request.getContent())
                    .parentPost(post)
                    .parentComment(commentParent)
                    .build());
        }

        return comment;
    }

    public void removeCommentFromPost(CommentDTO.Request request)
    {
        Post post = postRepository.findById(request.getParentPostId()).orElseThrow(()->new IllegalArgumentException("Post Not Found"));
        Comment comment = findCommentById(request.getId());

        if(passwordEncoder.matches(request.getPassword(), comment.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다!");
        }

        if(comment.getParentComment() == null) // 부모 댓글이 존재하지 않는 경우
        {
            post.removeComment(comment);
            postRepository.save(post);
        }
        else // 부모 댓글이 존재하는 경우
        {
            Comment parentComment = comment.getParentComment();
            parentComment.removeChildren(comment);
            commentRepository.save(parentComment);
        }
    }

    public void updateComment(CommentDTO.Request commentDTO)
    {
        Comment comment = commentRepository.findById(commentDTO.getId()).get();
        if(passwordEncoder.matches(commentDTO.getPassword(), comment.getPassword())) {
            comment.updateComment(commentDTO.getContent());
            commentRepository.save(comment);
        }
        else {
            throw new IllegalArgumentException("암호가 틀렸습니다.");
        }
    }

    public Comment findCommentById(Long id)
    {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
        return comment;
    }

}
