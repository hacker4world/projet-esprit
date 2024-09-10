package com.example.futurbe.dto.commentDTOs;

import com.example.futurbe.entitys.Reclamation;
import com.example.futurbe.entitys.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String content;
    private User user;
    private Date createdAt;
    private Reclamation reclamation;
    private List<String> attachments;
}
