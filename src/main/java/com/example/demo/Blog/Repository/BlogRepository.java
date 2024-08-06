package com.example.demo.Blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Blog.Entity.BlogPost;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<BlogPost, Long> {

    Optional<BlogPost> findByName(String name);

}
