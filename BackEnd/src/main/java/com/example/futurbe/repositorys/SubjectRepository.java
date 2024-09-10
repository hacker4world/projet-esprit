package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Page<Subject> findByClassroomId(Long classroomId, Pageable pageable);
}
