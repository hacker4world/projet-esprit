package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.TestEvalution;
import com.example.futurbe.entitys.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestEvaluationRepository extends JpaRepository<TestEvalution, Long> {
    Page<TestEvalution> findBySubjectTestId(Long subjectTestId, Pageable pageable);
    List<TestEvalution> findByUser(User user);
}
