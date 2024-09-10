package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ClassroomRepository extends JpaRepository<ClassRoom, Long> {
    Page<ClassRoom> findByGroupNameContainingIgnoreCase(String groupName, Pageable pageable);
}
