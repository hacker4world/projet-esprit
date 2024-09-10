package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Resume findByUserId(Long userId);
}