package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.Offre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OffreRepository extends JpaRepository<Offre, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Offre o WHERE o.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(o) > 0 FROM Offre o WHERE o.user.id = :userId")
    boolean existsByUserId(@Param("userId") Long userId);

}
