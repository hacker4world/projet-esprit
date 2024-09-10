package com.example.futurbe.services.iservices;


import com.example.futurbe.dto.Documents.*;
import com.example.futurbe.dto.ReclamationDTOs.GetAllReclamationPagedResponse;
import com.example.futurbe.dto.classRoomDTOs.GetAllClassRoomPagedResponse;
import com.example.futurbe.entitys.*;

import java.util.List;
import java.util.Map;

public interface IDocumentService {
    GetAllDocumentPagedResponse retrieveAllDocuments(int pageNumber, int pageSize);
    GetAllDocumentPagedResponse getDocumentByUser( int pageNumber, int pageSize,Long idUser);
    Document getDocumentById(Long idDocument);
    void saveDocument(DocumentSaveDTO document, Long idUser);
    void updateDocumentStatus(DocumentUpdateDTO body);
    GetAllDocumentPagedResponse filterByStatus(DocumentStatus status, int pageNumber, int pageSize);
    GetAllDocumentPagedResponse filterByType(DocumentType type, int pageNumber, int pageSize);


    GetAllDocumentPagedResponse filterByUserAndStatus(Long idUser,DocumentStatus  status, int pageNumber, int pageSize);
    GetAllDocumentPagedResponse filterByUserAndType(Long idUser,DocumentType type, int pageNumber, int pageSize);

    Map<String, Long> getDocumentStatistics();
    Map<String, Long> getDocumentStatisticsByUser(Long idUser);
    GetAllDocumentPagedResponse retrieveAllDocumentsArchived(int pageNumber, int pageSize);

    void deleteDocumentById(Long idDocument);

}
