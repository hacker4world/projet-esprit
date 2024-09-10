package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.SubjectTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectTestRepository extends JpaRepository<SubjectTest, Long> {
    Page<SubjectTest> findBySubjectId(Long subjectId, Pageable pageable);
    @Query("SELECT st FROM SubjectTest st WHERE st.subject.classroom.id = :classroomId")
    List<SubjectTest> findAllByClassroomId(@Param("classroomId") Long classroomId);
}
