package com.example.futurbe.dto.ReclamationDTOs;

import com.example.futurbe.entitys.Comment;
import com.example.futurbe.entitys.ReclamationStatus;
import com.example.futurbe.entitys.ReclamationType;
import com.example.futurbe.entitys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamationDTO {
    private Long id;
    private String subject;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private ReclamationStatus status;
    private User user;
    private Number rate;
    private ReclamationType type;
    private List<Comment> comments;
}
