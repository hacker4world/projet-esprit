package com.example.futurbe.services;

import com.example.futurbe.dto.ReclamationDTOs.*;
import com.example.futurbe.entitys.*;
import com.example.futurbe.mapper.ReclamationMapper;
import com.example.futurbe.mapper.ReclamationSaveMapper;
import com.example.futurbe.repositorys.CommentRepository;
import com.example.futurbe.repositorys.ReclamationRepository;
import com.example.futurbe.repositorys.UserRepository;
import com.example.futurbe.services.iservices.IReclamationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ReclamationService implements IReclamationService {
    @Autowired
    ReclamationRepository claimRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReclamationMapper reclamationMapper;
    @Autowired
    ReclamationSaveMapper reclamationSaveMapper;
    @Autowired
     CommentRepository commentRepository;
    @Autowired
    EmailService emailService;

    @Override
    public GetAllReclamationPagedResponse getAllReclamations(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Reclamation> claimPage = claimRepository.findByStatusNot(ReclamationStatus.Archived,pageRequest);

        List<getAllReclamationResponse> reclamations = claimPage.getContent().stream()
                .map(getAllReclamationResponse::new)
                .collect(Collectors.toList());

        int totalPages = claimPage.getTotalPages();
        return new GetAllReclamationPagedResponse(totalPages, pageNumber, pageSize, reclamations);
    }

    @Override
    public GetAllReclamationPagedResponse getReclamationsByUser(int pageNumber, int pageSize, Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new NullPointerException("User not found"));
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Reclamation> claimPage = claimRepository.findByUser(user,pageRequest);
        List<getAllReclamationResponse> reclamations = claimPage.getContent().stream()
                .map(getAllReclamationResponse::new)
                .collect(Collectors.toList());

        int totalPages = claimPage.getTotalPages();
        return new GetAllReclamationPagedResponse(totalPages, pageNumber, pageSize, reclamations);
    }

    @Override
    public GetReclamationByIdResponse getReclamationById(Long idClaim) {
        Reclamation claim = claimRepository.findById(idClaim)
                .orElseThrow(() -> new RuntimeException("Classroom not found"));
        return new GetReclamationByIdResponse(claim);
    }
    public void saveReclamation(ReclamationSave claim, Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + idUser));
        try {
            Reclamation reclamation = reclamationSaveMapper.convertToEntity(claim);
            Reclamation newClaim = new Reclamation();

            newClaim.setUser(user);
            newClaim.setStatus(ReclamationStatus.Pending);
            newClaim.setType(reclamation.getType());
            newClaim.setDescription(reclamation.getDescription());
            newClaim.setCreatedAt(new Date());
            newClaim.setSubject(reclamation.getSubject());
            newClaim.setUpdatedAt(new Date());


            Comment comment = Comment.builder()
                    .content(reclamation.getDescription())
                    .createdAt(new Date())
                    .user(user)
                    .reclamation(newClaim)
                    .build();

            // Ajouter le commentaire à la réclamation
            newClaim.setComments(List.of(comment));

            reclamation = claimRepository.save(newClaim);

            //Email send admin
            String subject = "New Reclamation Created";
            String text = "A new reclamation has been created by user: " + user.getFirstName() + " " + user.getLastName();
            emailService.sendEmail("zhaniimen6@gmail.com",subject, text);

        } catch (Exception e) {
            throw new RuntimeException("Error saving reclamation", e);
        }
    }

    @Override
    public void updateReclamationStatus(ReclamationUpdateStatusDTO body) {
        Optional<Reclamation> reclamationOptional = claimRepository.findById(body.getClaimId());
        if (reclamationOptional.isPresent()) {
            Reclamation reclamation = reclamationOptional.get();
            reclamation.setStatus(body.getStatus());
            reclamation = claimRepository.save(reclamation);
            // send mail
            String userEmail = reclamation.getUser().getEmail();
            String subject = "Recalamtion Status Updated";
            String description = "Your reclamation status has been updated to: " + body.getStatus();
            //Email send user
            emailService.sendEmail(userEmail, subject, description);
        } else {

        }
    }
    @Transactional
    @Override
    public void updateReclamation(ReclamationUpdate claim) {
        Optional<Reclamation> reclamationOptional = claimRepository.findById(claim.getClaimId());
        Optional<User> userOptional = userRepository.findById(claim.getUserId());

        if (reclamationOptional.isPresent() && userOptional.isPresent()) {

            Reclamation reclamation = reclamationOptional.get();
            User user = userOptional.get();
            if(reclamation.getStatus().equals(ReclamationStatus.Pending)){
                reclamation.setStatus(ReclamationStatus.Approved);
            }
            reclamation.setUpdatedAt(new Date());
            Comment comment = new Comment();
            comment.setContent(claim.getComment());
            comment.setUser(user);
            comment.setCreatedAt(new Date());
            comment.setReclamation(reclamation);

            reclamation.getComments().add(comment);

            //commentRepository.save(comment);
            claimRepository.save(reclamation);
        }

    }

    @Override
    public GetAllReclamationPagedResponse filterByStatus(ReclamationStatus  status, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Reclamation> classroomPage = claimRepository.findByStatus(status, pageRequest);

        List<getAllReclamationResponse> claims = classroomPage.getContent().stream()
                .map(getAllReclamationResponse::new)
                .collect(Collectors.toList());

        int totalPages = classroomPage.getTotalPages();

        return new GetAllReclamationPagedResponse(totalPages, pageNumber, pageSize, claims);
    }

    @Override
    public GetAllReclamationPagedResponse filterByType(ReclamationType type, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Reclamation> claimPage = claimRepository.findByType(type, pageRequest);

        List<getAllReclamationResponse> claims = claimPage.getContent().stream()
                .map(getAllReclamationResponse::new)
                .collect(Collectors.toList());

        int totalPages = claimPage.getTotalPages();

        return new GetAllReclamationPagedResponse(totalPages, pageNumber, pageSize, claims);
    }
    @Override
    public Map<String, Long> getClaimStatistics() {
        Long totalClaims = claimRepository.count();
        Long pendingClaims = claimRepository.countByStatus(ReclamationStatus.Pending);
        Long approvedClaims = claimRepository.countByStatus(ReclamationStatus.Approved);
        Long rejectedClaims = claimRepository.countByStatus(ReclamationStatus.Rejected);
        Long completedClaims = claimRepository.countByStatus(ReclamationStatus.Completed);

        Map<String, Long> stats = new HashMap<>();
        stats.put("totalClaims", totalClaims);
        stats.put("pendingClaims", pendingClaims);
        stats.put("approvedClaims", approvedClaims);
        stats.put("rejectedClaims", rejectedClaims);
        stats.put("completedClaims", completedClaims);

        return stats;
    }

    @Override
    public Map<String, Long> getReclamationStatisticsByUser(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new NullPointerException("User not found"));

        Long totalClaims = claimRepository.countByUser(user);
        Long pendingClaims = claimRepository.countByUserAndStatus(user,ReclamationStatus.Pending);
        Long approvedClaims = claimRepository.countByUserAndStatus(user,ReclamationStatus.Approved);
        Long rejectedClaims = claimRepository.countByUserAndStatus(user,ReclamationStatus.Rejected);
        Long completedClaims = claimRepository.countByUserAndStatus(user,ReclamationStatus.Completed);

        Map<String, Long> stats = new HashMap<>();
        stats.put("totalClaims", totalClaims);
        stats.put("pendingClaims", pendingClaims);
        stats.put("approvedClaims", approvedClaims);
        stats.put("rejectedClaims", rejectedClaims);
        stats.put("completedClaims", completedClaims);

        return stats;
    }

    @Override
    public GetAllReclamationPagedResponse filterByUserAndStatus(Long idUser, ReclamationStatus status, int pageNumber, int pageSize) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new NullPointerException("User not found"));

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Reclamation> classroomPage = claimRepository.findByUserAndStatus(user,status, pageRequest);

        List<getAllReclamationResponse> claims = classroomPage.getContent().stream()
                .map(getAllReclamationResponse::new)
                .collect(Collectors.toList());

        int totalPages = classroomPage.getTotalPages();

        return new GetAllReclamationPagedResponse(totalPages, pageNumber, pageSize, claims);
    }

    @Override
    public GetAllReclamationPagedResponse filterByUserAndType(Long idUser, ReclamationType type, int pageNumber, int pageSize) {

        User user = userRepository.findById(idUser).orElseThrow(() -> new NullPointerException("User not found"));

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Reclamation> claimPage = claimRepository.findByUserAndType(user,type, pageRequest);

        List<getAllReclamationResponse> claims = claimPage.getContent().stream()
                .map(getAllReclamationResponse::new)
                .collect(Collectors.toList());

        int totalPages = claimPage.getTotalPages();

        return new GetAllReclamationPagedResponse(totalPages, pageNumber, pageSize, claims);
    }

    @Override
    public GetAllReclamationPagedResponse retrieveAllReclamationArchived(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Reclamation> claimPage = claimRepository.findByStatus(ReclamationStatus.Archived,pageRequest);

        List<getAllReclamationResponse> reclamations = claimPage.getContent().stream()
                .map(getAllReclamationResponse::new)
                .collect(Collectors.toList());

        int totalPages = claimPage.getTotalPages();
        return new GetAllReclamationPagedResponse(totalPages, pageNumber, pageSize, reclamations);
    }

    @Override
    public void deleteReclamationById(Long idClaim) {
        Reclamation claim = claimRepository.findById(idClaim).orElseThrow(() -> new NullPointerException("Document not found"));
        claimRepository.delete(claim);
    }
}
