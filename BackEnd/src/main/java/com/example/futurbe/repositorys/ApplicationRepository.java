package com.example.futurbe.repositorys;

import com.example.futurbe.entitys.Application;
import com.example.futurbe.entitys.Offre;
import com.example.futurbe.entitys.TypeOffre;
import com.example.futurbe.entitys.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    public Application findApplicationByOffer_TypeOffre(TypeOffre typeOffre) ;
    public List<Application> findApplicationByUser(User user);
    public List<Application> findApplicationByUserAndOffer(User user, Offre offer);
    public List<Application> findApplicationByOffer(Offre offer);

    @Modifying
    @Transactional
    @Query("UPDATE Application a SET a.is_viewed = true WHERE a.user.id = :userId AND a.offer.id = :offerId")
    public void updateIsViewed(Long userId, Long offerId);
    @Modifying
    @Transactional
    @Query("delete Application a  WHERE  a.offer.id = :offerId")
    public void deleteApplicationByOfferID( Long offerId);
}
