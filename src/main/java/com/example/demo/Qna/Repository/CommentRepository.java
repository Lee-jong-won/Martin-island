package com.example.demo.Qna.Repository;

import com.example.demo.Qna.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
