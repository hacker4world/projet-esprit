package com.example.futurbe.dto;

import com.example.futurbe.dto.userDTO.UserDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationBookDTO {
    private long id;
    private String reason;
    private boolean isApproved;
    private boolean isReturned;
    private boolean requestReturn;
    private LocalDate startDate;

    private LocalDate endDate;

    private long userId;
    private long bookId;
    private BookDTO book;
    private UserDTO user;
}
