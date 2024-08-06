package com.example.demo.Qna.Repository;

import com.example.demo.Qna.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
