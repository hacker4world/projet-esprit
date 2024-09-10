package com.example.futurbe.dto.ReclamationDTOs;

import com.example.futurbe.dto.Documents.UserResponseDTO;
import com.example.futurbe.dto.commentDTOs.getCommentReclamationResponse;
import com.example.futurbe.entitys.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GetReclamationByIdResponse {

    private Long id;
    private String subject;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    private ReclamationStatus status;
    private ReclamationType type;
    private UserResponseDTO user;
    private List<getCommentReclamationResponse> comments;

    public GetReclamationByIdResponse(Reclamation claim) {
        this.id = claim.getId();
        this.subject = claim.getSubject();
        this.description = claim.getDescription();
        this.createdAt = claim.getCreatedAt();
        this.updatedAt = claim.getUpdatedAt();

        this.status = claim.getStatus();
        this.type = claim.getType();
        UserResponseDTO userDTO = new UserResponseDTO(claim.getUser().getFirstName(), claim.getUser().getLastName(),claim.getUser().getEmail(),claim.getUser().getId());
        user = userDTO;

        this.comments = claim.getComments().stream()
                .map(getCommentReclamationResponse::new)
                .collect(Collectors.toList());
    }
}
