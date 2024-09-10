package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
