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
public interface DocumentRepository extends JpaRepository<Document,Long> {

    Page<Document> findByUser(User user, Pageable pageable);
    Page<Document> findByStatus(DocumentStatus status, Pageable pageable);
    Page<Document> findByType(DocumentType type, Pageable pageable);

    Page<Document> findByUserAndStatus(User user,DocumentStatus status, Pageable pageable);
    Page<Document> findByUserAndType(User user,DocumentType type, Pageable pageable);

    @Query("SELECT COUNT(d) FROM Document d WHERE d.user = :user")
    long countByUser(@Param("user") User user);

    @Query("SELECT COUNT(d) FROM Document d WHERE d.status = :status")
    Long countByStatus(DocumentStatus status);

    long countByUserAndStatus(User user, DocumentStatus status);
    Page<Document> findByStatusNot(DocumentStatus status,Pageable pageable);

}
