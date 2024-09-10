package com.example.futurbe.services;
import com.example.futurbe.dto.Documents.*;
import com.example.futurbe.entitys.*;
import com.example.futurbe.mapper.Documents.DocumentMapper;
import com.example.futurbe.mapper.Documents.DocumentResponseGetAll;
import com.example.futurbe.mapper.Documents.DocumentSaveMapper;
import com.example.futurbe.repositorys.DocumentRepository;
import com.example.futurbe.repositorys.UserRepository;
import com.example.futurbe.services.iservices.IDocumentService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.text.DocumentException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class DocumentService implements IDocumentService {
     DocumentRepository documentRepository;
    UserRepository userRepository;
     EmailService emailService;
    DocumentSaveMapper documentSaveMapper;
     DocumentMapper documentMapper;
    DocumentResponseGetAll documentResponseGetAll;

    @Override
    public GetAllDocumentPagedResponse retrieveAllDocuments(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Document> documentPage = documentRepository.findByStatusNot(DocumentStatus.Archived,pageRequest);


        List<getAllDocumentResponse> documents = documentPage.getContent().stream()
                .map(getAllDocumentResponse::new)
                .collect(Collectors.toList());

        int totalPages = documentPage.getTotalPages();
        return new GetAllDocumentPagedResponse(totalPages, pageNumber, pageSize, documents);

//        List<Document> documents = documentRepository.findAll();
//        return documents.stream().map(documentResponseGetAll::convertToDto).collect(Collectors.toList());

    }

    @Override
    public GetAllDocumentPagedResponse getDocumentByUser( int pageNumber, int pageSize,Long idUser) {

        User user = userRepository.findById(idUser).orElseThrow(() -> new NullPointerException("User not found"));
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Document> documentPage = documentRepository.findByUser(user,pageRequest);
        List<getAllDocumentResponse> documents = documentPage.getContent().stream()
                .map(getAllDocumentResponse::new)
                .collect(Collectors.toList());

        int totalPages = documentPage.getTotalPages();
        return new GetAllDocumentPagedResponse(totalPages, pageNumber, pageSize, documents);
    }

    @Override
    public Document getDocumentById(Long idDocument) {
        return documentRepository.findById(idDocument).orElseThrow(() -> new NullPointerException("Document not found"));

    }

    @Override
    public void saveDocument(DocumentSaveDTO document, Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new NullPointerException("User not found"));
        Document documentMapper = documentSaveMapper.convertToEntity(document);
        Document newDoc = new Document();
        newDoc.setCreatedAt(new Date());
        newDoc.setUser(user);
        newDoc.setStatus(DocumentStatus.Pending);
        newDoc.setType(documentMapper.getType());
        newDoc.setUpdatedAt(new Date());
        //Email send admin
        String subject = "New request for created document alert !";
        String text = "A new document has been created by user: " + user.getFirstName() + " " + user.getLastName();
        emailService.sendEmail("zhaniimen6@gmail.com",subject, text);
         documentRepository.save(newDoc);
    }
    private String generateContentBasedOnType(DocumentType type) {
        switch (type) {
            case diploma:
                return "Diploma Content";
            case transcript:
                return "Transcript Content";
            case studentCard:
                return "Student Card Content";
            case attendanceCertificate:
                return "Attendance Certificate Content";
            default:
                return "Unknown Document Type";
        }
    }
    @Override
    public void updateDocumentStatus(DocumentUpdateDTO body) {
        Document document = documentRepository.findById(body.getDocumentId()).orElseThrow(() -> new NullPointerException("Document not found"));
        document.setUpdatedAt(new Date());
        document.setStatus(body.getStatus());
        if (body.getStatus() == DocumentStatus.Rejected) {
            document.setComment(body.getComment());
        }
        String userEmail = document.getUser().getEmail();
        String subject = "Document Status Updated";
        String description = "Your document status has been updated to: " + body.getStatus();
        if (body.getStatus() == DocumentStatus.Rejected) {
            description += "\nReason: " + body.getComment();
        }
if(body.getStatus() == DocumentStatus.Completed) {
    if (document.getStatus() == DocumentStatus.Completed) {
        if(document.getType()==DocumentType.studentCard) {
            this.generateStudentCard(document);
        }else if(document.getType()==DocumentType.attendanceCertificate) {
            this.generateAttendanceCertificate(document);
        }else if(document.getType()==DocumentType.transcript) {
            this.generateTranscript(document);
        } else  {
            this.generateDiploma(document);
        }


    }}
        documentRepository.save(document);
        //Email send user
        emailService.sendEmail(userEmail, subject, description);

    }
    @Override
    public GetAllDocumentPagedResponse filterByStatus(DocumentStatus status, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Document> documentPage = documentRepository.findByStatus(status, pageRequest);

        List<getAllDocumentResponse> claims = documentPage.getContent().stream()
                .map(getAllDocumentResponse::new)
                .collect(Collectors.toList());

        int totalPages = documentPage.getTotalPages();

        return new GetAllDocumentPagedResponse(totalPages, pageNumber, pageSize, claims);
    }

    @Override
    public GetAllDocumentPagedResponse filterByType(DocumentType type, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Document> claimPage = documentRepository.findByType(type, pageRequest);

        List<getAllDocumentResponse> documents = claimPage.getContent().stream()
                .map(getAllDocumentResponse::new)
                .collect(Collectors.toList());

        int totalPages = claimPage.getTotalPages();

        return new GetAllDocumentPagedResponse(totalPages, pageNumber, pageSize, documents);
    }

    @Override
    public GetAllDocumentPagedResponse filterByUserAndStatus(Long idUser, DocumentStatus status, int pageNumber, int pageSize) {

        User user = userRepository.findById(idUser).orElseThrow(() -> new NullPointerException("User not found"));
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Document> documentPage = documentRepository.findByUserAndStatus(user,status, pageRequest);

        List<getAllDocumentResponse> claims = documentPage.getContent().stream()
                .map(getAllDocumentResponse::new)
                .collect(Collectors.toList());

        int totalPages = documentPage.getTotalPages();

        return new GetAllDocumentPagedResponse(totalPages, pageNumber, pageSize, claims);
    }

    @Override
    public GetAllDocumentPagedResponse filterByUserAndType(Long idUser, DocumentType type, int pageNumber, int pageSize) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new NullPointerException("User not found"));

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Document> claimPage = documentRepository.findByUserAndType(user,type, pageRequest);

        List<getAllDocumentResponse> documents = claimPage.getContent().stream()
                .map(getAllDocumentResponse::new)
                .collect(Collectors.toList());

        int totalPages = claimPage.getTotalPages();

        return new GetAllDocumentPagedResponse(totalPages, pageNumber, pageSize, documents);
    }

    @Override
    public Map<String, Long> getDocumentStatistics() {
        Long totalDocuments = documentRepository.count();
        Long pendingDocuments = documentRepository.countByStatus(DocumentStatus.Pending);
        Long approvedDocuments = documentRepository.countByStatus(DocumentStatus.Approved);
        Long rejectedDocuments = documentRepository.countByStatus(DocumentStatus.Rejected);
        Long completedDocuments = documentRepository.countByStatus(DocumentStatus.Completed);

        Map<String, Long> stats = new HashMap<>();
        stats.put("totalDocuments", totalDocuments);
        stats.put("pendingDocuments", pendingDocuments);
        stats.put("approvedDocuments", approvedDocuments);
        stats.put("rejectedDocuments", rejectedDocuments);
        stats.put("completedDocuments", completedDocuments);

        return stats;
    }

    @Override
    public Map<String, Long> getDocumentStatisticsByUser(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new NullPointerException("User not found"));

        Long totalDocuments = documentRepository.countByUser(user);
        Long pendingDocuments = documentRepository.countByUserAndStatus(user,DocumentStatus.Pending);
        Long approvedDocuments = documentRepository.countByUserAndStatus(user,DocumentStatus.Approved);
        Long rejectedDocuments = documentRepository.countByUserAndStatus(user,DocumentStatus.Rejected);
        Long completedDocuments = documentRepository.countByUserAndStatus(user,DocumentStatus.Completed);

        Map<String, Long> stats = new HashMap<>();
        stats.put("totalDocuments", totalDocuments);
        stats.put("pendingDocuments", pendingDocuments);
        stats.put("approvedDocuments", approvedDocuments);
        stats.put("rejectedDocuments", rejectedDocuments);
        stats.put("completedDocuments", completedDocuments);

        return stats;
    }

    @Override
    public GetAllDocumentPagedResponse retrieveAllDocumentsArchived(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Document> documentPage = documentRepository.findByStatus(DocumentStatus.Archived,pageRequest);


        List<getAllDocumentResponse> documents = documentPage.getContent().stream()
                .map(getAllDocumentResponse::new)
                .collect(Collectors.toList());

        int totalPages = documentPage.getTotalPages();
        return new GetAllDocumentPagedResponse(totalPages, pageNumber, pageSize, documents);
    }

    @Override
    public void deleteDocumentById(Long idDocument) {
        Document document = documentRepository.findById(idDocument).orElseThrow(() -> new NullPointerException("Document not found"));
        documentRepository.delete(document);
    }

    public ByteArrayInputStream generateAttendanceCertificate(Document document) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(out))) {
            com.itextpdf.layout.Document pdfDocument = new com.itextpdf.layout.Document(pdfDoc);
            User user = document.getUser();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Paragraph title = new Paragraph("Attestation de Présence")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20)
                    .setBold();

            Paragraph details = new Paragraph()
                    .add("Nous soussignés, Ecole Supérieure Privée d'Ingénierie et de Technologies, certifions que :\n\n")
                    .add("Nom de l'étudiant : " + user.getFirstName() + " " + user.getLastName() + "\n")
                    .add("Numéro d'étudiant : " + user.getUsername() + "\n")
                    .add("A été présent(e) à : BI" + "\n")
                    .add("Date : " + formatter.format(document.getUpdatedAt()) + "\n\n")
                    .add("Signature :\n\n")
                    .add("_______________________\n")
                    .add("Professeur / Responsable administratif\n")
                    .add("Date de délivrance : " + formatter.format(document.getCreatedAt()) + "\n");

            pdfDocument.add(title);
            pdfDocument.add(details);
            pdfDocument.close();

            // Save PDF to a temporary file
            File tempFile = File.createTempFile("AttendanceCertificate", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(out.toByteArray());
            }

            // Send email with the attachment
            emailService.sendEmailWithAttachmentd("imen.zhani@esprit.tn", "Your Document", "Please find the attached document.", tempFile.getAbsolutePath());

            // Delete the temporary file after sending the email
            tempFile.delete();


        }  catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    public ByteArrayInputStream generateDiploma(Document document) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(out))) {
            com.itextpdf.layout.Document pdfDocument = new com.itextpdf.layout.Document(pdfDoc);
            User user = document.getUser();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Paragraph title = new Paragraph("Diplôme")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(30)
                    .setBold();

            Paragraph details = new Paragraph()
                    .add("Nous soussignés, Ecole Supérieure Privée d'Ingénierie et de Technologies, certifions que :\n\n")
                    .add("Nom de l'étudiant : " + user.getFirstName() + " " + user.getLastName() + "\n")
                    .add("Numéro d'étudiant : " + user.getUsername() + "\n")
                    .add("A complété avec succès le programme de : BI" + "\n")
                    .add("Date : " + formatter.format(document.getUpdatedAt()) + "\n\n")
                    .add("Signature :\n\n")
                    .add("_______________________\n")
                    .add("Directeur de l'Institution\n")
                    .add("Date de délivrance : " + formatter.format(document.getCreatedAt()) + "\n");


            pdfDocument.add(title);
            pdfDocument.add(details);
            pdfDocument.close();

            // Save PDF to a temporary file
            File tempFile = File.createTempFile("Diploma", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(out.toByteArray());
            }

            // Send email with the attachment
            emailService.sendEmailWithAttachmentd("imen.zhani@esprit.tn", "Your Document", "Please find the attached document.", tempFile.getAbsolutePath());

            // Delete the temporary file after sending the email
            tempFile.delete();


        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    public ByteArrayInputStream generateStudentCard(Document document) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(out))) {
            com.itextpdf.layout.Document pdfDocument = new com.itextpdf.layout.Document(pdfDoc);
            User user = document.getUser();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Paragraph title = new Paragraph("Carte d'Étudiant")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20)
                    .setBold();

            Paragraph details = new Paragraph()
                    .add("Institution : Ecole Supérieure Privée d'Ingénierie et de Technologies,\n\n")
                    .add("Nom de l'étudiant : " + user.getFirstName() + " " + user.getLastName() + "\n")
                    .add("Numéro d'étudiant : " + user.getUsername() + "\n")
                    .add("Programme : BI" + "\n")
                    .add("Date d'émission : " + formatter.format(document.getCreatedAt()) + "\n\n");


            pdfDocument.add(title);
            pdfDocument.add(details);
            pdfDocument.close();

            // Save PDF to a temporary file
            File tempFile = File.createTempFile("StudentCard", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(out.toByteArray());
            }

            // Send email with the attachment
            emailService.sendEmailWithAttachmentd("imen.zhani@esprit.tn", "Your Document", "Please find the attached document.", tempFile.getAbsolutePath());

            // Delete the temporary file after sending the email
            tempFile.delete();


        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    public ByteArrayInputStream generateTranscript(Document document) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(out))) {
            com.itextpdf.layout.Document pdfDocument = new com.itextpdf.layout.Document(pdfDoc);
            User user = document.getUser();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Paragraph title = new Paragraph("Relevé de Notes")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20)
                    .setBold();

            Paragraph studentDetails = new Paragraph()
                    .add("Nom de l'étudiant : " + user.getFirstName() + " " + user.getLastName() + "\n")
                    .add("Numéro d'étudiant : " + user.getUsername() + "\n")
                    .add("Date de génération : " + formatter.format(document.getCreatedAt()) + "\n\n");

            Paragraph transcriptDetails = new Paragraph("Détails des cours et des notes :\n");


            pdfDocument.add(title);

            pdfDocument.add(studentDetails);
            pdfDocument.add(transcriptDetails);

            pdfDocument.close();

            // Save PDF to a temporary file
            File tempFile = File.createTempFile("Transcript", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(out.toByteArray());
            }

            // Send email with the attachment
            emailService.sendEmailWithAttachmentd("imen.zhani@esprit.tn", "Your Document", "Please find the attached document.", tempFile.getAbsolutePath());

            // Delete the temporary file after sending the email
            tempFile.delete();


        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}