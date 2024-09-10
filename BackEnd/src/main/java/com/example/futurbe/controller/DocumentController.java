package com.example.futurbe.controller;

import com.example.futurbe.dto.Documents.*;
import com.example.futurbe.dto.ReclamationDTOs.GetAllReclamationPagedResponse;
import com.example.futurbe.entitys.*;
import com.example.futurbe.services.iservices.IDocumentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    IDocumentService documentService;
    @GetMapping("/filter/status")
    public GetAllDocumentPagedResponse filterStatus(@RequestParam DocumentStatus status, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return documentService.filterByStatus(status,pageNumber-1, pageSize);
    }
    @GetMapping("/filter/type")
    public GetAllDocumentPagedResponse filterStatus(@RequestParam DocumentType type, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return documentService.filterByType(type,pageNumber-1, pageSize);
    }
    @GetMapping
    @ResponseBody
    public GetAllDocumentPagedResponse  getAll(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return documentService.retrieveAllDocuments(pageNumber-1, pageSize);
    }

    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable Long id) {
        Document document = documentService.getDocumentById(id);
        return document;
    }

    @DeleteMapping("/{id}")
    public void deleteDocumentById(@PathVariable Long id) {
     documentService.deleteDocumentById(id);

    }

    @GetMapping("/user/{userId}")
    public GetAllDocumentPagedResponse getDocumentsByUser(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize,@PathVariable Long userId) {
        return documentService.getDocumentByUser(pageNumber-1, pageSize,userId);
    }

    @PostMapping("/user/{userId}")
    public void createDocument(@RequestBody DocumentSaveDTO document, @PathVariable Long userId) {
      documentService.saveDocument(document, userId);

    }

    @PutMapping("/updateStatus")
    public void updateDocumentStatus(@RequestBody DocumentUpdateDTO body) {
      documentService.updateDocumentStatus(body);

    }

    @GetMapping("/filter/status/{idUser}")
    public GetAllDocumentPagedResponse filterByUserAndStatus(@PathVariable Long idUser,@RequestParam DocumentStatus status, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return documentService.filterByUserAndStatus(idUser,status,pageNumber-1, pageSize);
    }
    @GetMapping("/filter/type/{idUser}")
    public GetAllDocumentPagedResponse filterByUserAndStatus(@PathVariable Long idUser,@RequestParam DocumentType type, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return documentService.filterByUserAndType(idUser,type,pageNumber-1, pageSize);
    }

    @GetMapping("/statistics")
    public Map<String, Long> getClaimStatistics() {
        return documentService.getDocumentStatistics();
    }

    @GetMapping("/statistics/user/{idUser}")
    public Map<String, Long> getClaimStatisticsByUser(@PathVariable Long idUser) {
        return documentService.getDocumentStatisticsByUser(idUser);
    }

    @GetMapping("/archived")
    @ResponseBody
    public GetAllDocumentPagedResponse  getAllDocumentArchived(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return documentService.retrieveAllDocumentsArchived(pageNumber-1, pageSize);
    }
}
