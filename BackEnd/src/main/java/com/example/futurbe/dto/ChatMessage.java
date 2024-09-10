package com.example.futurbe.dto;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;
    private String recipient;

    // Getters and Setters
}