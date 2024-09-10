package com.example.futurbe.repositorys;


import com.example.futurbe.entitys.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {
    Page<Reclamation> findByStatus(ReclamationStatus status, Pageable pageable);
    Page<Reclamation> findByType(ReclamationType type, Pageable pageable);
    @Query("SELECT COUNT(r) FROM Reclamation r WHERE r.status = :status")
    Long countByStatus(ReclamationStatus status);
    Page<Reclamation> findByUser(User user, Pageable pageable);
    long countByUserAndStatus(User user, ReclamationStatus status);

    @Query("SELECT COUNT(r) FROM Reclamation r WHERE r.user = :user")
    long countByUser(@Param("user") User user);

    Page<Reclamation> findByUserAndStatus(User user,ReclamationStatus status, Pageable pageable);
    Page<Reclamation> findByUserAndType(User user,ReclamationType type, Pageable pageable);

    Page<Reclamation> findByStatusNot(ReclamationStatus status, Pageable pageable);

}
