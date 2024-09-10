package com.example.futurbe.services.iservices;


import com.example.futurbe.dto.Documents.DocumentUpdateDTO;
import com.example.futurbe.dto.Documents.GetAllDocumentPagedResponse;
import com.example.futurbe.dto.ReclamationDTOs.*;
import com.example.futurbe.dto.classRoomDTOs.GetAllClassRoomPagedResponse;
import com.example.futurbe.entitys.Document;
import com.example.futurbe.entitys.Reclamation;
import com.example.futurbe.entitys.ReclamationStatus;
import com.example.futurbe.entitys.ReclamationType;

import java.util.List;
import java.util.Map;

public interface IReclamationService {
    GetAllReclamationPagedResponse getAllReclamations(int pageNumber, int pageSize);
    GetAllReclamationPagedResponse getReclamationsByUser(int pageNumber, int pageSize,Long idUser);
    GetReclamationByIdResponse getReclamationById(Long idClaim);
    void saveReclamation(ReclamationSave claim, Long idUser);
    void updateReclamationStatus(ReclamationUpdateStatusDTO body);

    void updateReclamation(ReclamationUpdate claim);
    GetAllReclamationPagedResponse filterByStatus(ReclamationStatus  status, int pageNumber, int pageSize);
    GetAllReclamationPagedResponse filterByType(ReclamationType type, int pageNumber, int pageSize);
    Map<String, Long> getClaimStatistics();
    Map<String, Long> getReclamationStatisticsByUser(Long idUser);

    GetAllReclamationPagedResponse filterByUserAndStatus(Long idUser,ReclamationStatus  status, int pageNumber, int pageSize);
    GetAllReclamationPagedResponse filterByUserAndType(Long idUser,ReclamationType type, int pageNumber, int pageSize);

    GetAllReclamationPagedResponse retrieveAllReclamationArchived(int pageNumber, int pageSize);

    void deleteReclamationById(Long idClaim);


}
