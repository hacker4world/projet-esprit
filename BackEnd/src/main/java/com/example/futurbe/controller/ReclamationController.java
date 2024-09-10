package com.example.futurbe.controller;

import com.example.futurbe.dto.Documents.GetAllDocumentPagedResponse;
import com.example.futurbe.dto.ReclamationDTOs.*;
import com.example.futurbe.dto.classRoomDTOs.GetAllClassRoomPagedResponse;
import com.example.futurbe.entitys.Reclamation;
import com.example.futurbe.entitys.ReclamationStatus;
import com.example.futurbe.entitys.ReclamationType;
import com.example.futurbe.services.EmailService;
import com.example.futurbe.services.iservices.IReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/reclamations")
public class ReclamationController {
    @Autowired
    IReclamationService reclamationService;
    @Autowired
    private EmailService emailService;
    @GetMapping("/filter/status")
    public GetAllReclamationPagedResponse filterStatus(@RequestParam ReclamationStatus status, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return reclamationService.filterByStatus(status,pageNumber-1, pageSize);
    }
    @GetMapping("/filter/type")
    public GetAllReclamationPagedResponse filterStatus(@RequestParam ReclamationType type, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return reclamationService.filterByType(type,pageNumber-1, pageSize);
    }

    @GetMapping
    public GetAllReclamationPagedResponse  getAll(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return reclamationService.getAllReclamations(pageNumber-1, pageSize);
    }
    @GetMapping("/{idClaim}")
    public GetReclamationByIdResponse getReclamationById(@PathVariable Long idClaim) {
        return reclamationService.getReclamationById(idClaim);
    }

    @GetMapping("/user/{idUser}")
    public GetAllReclamationPagedResponse getReclamationsByUser(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize,@PathVariable Long idUser) {
        return reclamationService.getReclamationsByUser(pageNumber-1, pageSize,idUser);
    }
    @PostMapping("/user/{idUser}")
    public void saveReclamation(@RequestBody ReclamationSave claim, @PathVariable Long idUser) {
         reclamationService.saveReclamation(claim, idUser);
    }
    @PutMapping("/status")
    public void updateReclamationStatus(@RequestBody ReclamationUpdateStatusDTO body) {
         reclamationService.updateReclamationStatus(body);
    }
    @PutMapping
    public void updateReclamation(@RequestBody ReclamationUpdate claim) {
        reclamationService.updateReclamation(claim);
    }
    @GetMapping("/statistics")
    public Map<String, Long> getClaimStatistics() {
        return reclamationService.getClaimStatistics();
    }

    @GetMapping("/statistics/user/{idUser}")
    public Map<String, Long> getClaimStatisticsByUser(@PathVariable Long idUser) {
        return reclamationService.getReclamationStatisticsByUser(idUser);
    }


    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        emailService.sendEmail(to, subject, text);
        return "Email sent successfully!";
    }

    @GetMapping("/filter/status/{idUser}")
    public GetAllReclamationPagedResponse filterByUserAndStatus(@PathVariable Long idUser,@RequestParam ReclamationStatus status, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return reclamationService.filterByUserAndStatus(idUser,status,pageNumber-1, pageSize);
    }
    @GetMapping("/filter/type/{idUser}")
    public GetAllReclamationPagedResponse filterByUserAndStatus(@PathVariable Long idUser,@RequestParam ReclamationType type, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return reclamationService.filterByUserAndType(idUser,type,pageNumber-1, pageSize);
    }

    @GetMapping("/archived")
    @ResponseBody
    public GetAllReclamationPagedResponse  getAllArchived(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return reclamationService.retrieveAllReclamationArchived(pageNumber-1, pageSize);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reclamationService.deleteReclamationById(id);

    }


}
