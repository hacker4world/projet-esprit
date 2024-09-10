package com.example.futurbe.dto.commentDTOs;

import com.example.futurbe.dto.Documents.UserResponseDTO;
import com.example.futurbe.entitys.Comment;
import com.example.futurbe.entitys.Reclamation;
import com.example.futurbe.entitys.ReclamationStatus;
import com.example.futurbe.entitys.ReclamationType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class getCommentReclamationResponse {
    private Long id;
    private String content;
    private Date createdAt;
    private UserResponseDTO user;



    public getCommentReclamationResponse(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
         this.createdAt = comment.getCreatedAt();
        UserResponseDTO userDTO = new UserResponseDTO(comment.getUser().getFirstName(), comment.getUser().getLastName(),comment.getUser().getEmail(),comment.getUser().getId());
        user = userDTO;
    }
}
