package com.example.futurbe.dto.ReclamationDTOs;

import com.example.futurbe.entitys.ReclamationType;
import com.example.futurbe.entitys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamationSave {
    private String subject;
    private String description;
    private ReclamationType type;
//    private String attachements;
}
