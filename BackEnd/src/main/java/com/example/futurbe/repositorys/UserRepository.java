package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.Role;
import com.example.futurbe.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByClassroomId(Long classroomId, Pageable pageable);

    boolean existsByFirstName(String firstName);
    boolean existsByEmail(String email);


    User findByFirstName(String firstName);
    User findByEmail(String email);

    User findByUsername(String userName);
    List<User> findByRoleAndClassroomIsNull(Role role);
}