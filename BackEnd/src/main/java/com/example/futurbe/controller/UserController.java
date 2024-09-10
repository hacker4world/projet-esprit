package com.example.futurbe.controller;

import com.example.futurbe.dto.userDTO.ResumeDTO;
import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.Resume;
import com.example.futurbe.entitys.User;
import com.example.futurbe.repositorys.UserRepository;
import com.example.futurbe.services.iservices.IUserService;
import com.google.cloud.documentai.v1.Document;
import com.google.cloud.documentai.v1.DocumentProcessorServiceClient;
import com.google.cloud.documentai.v1.ProcessRequest;
import com.google.cloud.documentai.v1.RawDocument;
import com.google.protobuf.ByteString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Objects;
import java.util.List;

@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Value("${file.upload-dir}")
    Path fileStorageLocation;
    @Autowired
    IUserService userService;

//    @Autowired
//    private DocumentProcessorServiceClient documentProcessorServiceClient;
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO);
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        userDTO.setCreatedDate(new Date());
        return userService.saveUser(userDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT','PROFESSOR','ENTERPRISE')")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userRepository.findById(id).get();
        userDTO.setId(id);
        if(userDTO.getFirstName()==null){
            userDTO.setUsername(user.getFirstName());
        }if(userDTO.getPassword()==null){
            userDTO.setPassword(user.getPassword());
        }if(userDTO.getLastName()==null){
            userDTO.setLastName(user.getLastName());
        }if(userDTO.getEmail()==null){
        userDTO.setEmail(user.getEmail());
        }if(userDTO.getRole()==null){
            userDTO.setRole(user.getRole());
        }if(userDTO.getUserOption()==null){
            userDTO.setUserOption(user.getUserOption());
        }
        userDTO.setProfilePicURI(user.getProfilePicURI());
        userDTO.setResumeURI(user.getResumeURI());
        userDTO.setUsername(user.getUsername());
        userDTO.setUpdatedDate(new Date());
        UserDTO updatedUser = userService.saveUser(userDTO);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/profilePic")
    @PreAuthorize("hasAnyRole('STUDENT','PROFESSOR')")
    public ResponseEntity<UserDTO> updateProfilePicture(@PathVariable Long id,
                                                        @RequestParam("profilePicFile") MultipartFile profilePicFile) throws IOException {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }
        userDTO.setUpdatedDate(new Date());
        String profilePicURI = uploadFile(profilePicFile);
        userDTO.setProfilePicURI(profilePicURI);
        userDTO= userService.saveUser(userDTO);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/{id}/resume")
    @PreAuthorize("hasAnyRole('STUDENT','PROFESSOR')")
    public ResponseEntity<UserDTO> updateResume(@PathVariable Long id,
                                                @RequestParam("resume") MultipartFile resumeFile) throws IOException {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO == null) {
            return ResponseEntity.notFound().build(); // Handle missing user
        }

        // Process resume with Google Cloud Document AI
//        Document document = processResume(resumeFile);
//
//        // Extract relevant data from Document
//        String education = extractField(document, "education");
//        String experience = extractField(document, "experience");
//        String skills = extractField(document, "skills");
//        String language = extractField(document, "language");

        // Update the Resume entity
//        ResumeDTO resume = userDTO.getResume();
//        if (resume == null) {
//            resume = new ResumeDTO();
//            resume.setUserDTO(userDTO);
//        }
//
//        resume.setEducation(education);
//        resume.setExperience(experience);
//        resume.setSkills(skills);
//        resume.setLanguage(language);
//        resume.setEmail(userDTO.getEmail());
//        resume.setDateOfBirth(new Date()); // Assuming you extract date of birth as well

//        userDTO.setResume(resume);
        userDTO.setUpdatedDate(new Date());
        String resumeFileURI = uploadFile(resumeFile);
        userDTO.setResumeURI(resumeFileURI);
        userService.saveUser(userDTO);

        return ResponseEntity.ok(userDTO);
    }

//    private Document processResume(MultipartFile file) throws IOException {
//        ByteString content = ByteString.readFrom(file.getInputStream());
//        RawDocument rawDocument = RawDocument.newBuilder()
//                .setContent(content)
//                .setMimeType("application/pdf")
//                .build();
//
//        ProcessRequest request = ProcessRequest.newBuilder()
//                .setName("projects/YOUR_PROJECT_ID/locations/YOUR_LOCATION/processors/YOUR_PROCESSOR_ID")
//                .setRawDocument(rawDocument)
//                .build();
//
//        return documentProcessorServiceClient.processDocument(request).getDocument();
//    }

    private String extractField(Document document, String fieldName) {
        // Implement logic to extract field value from the Document
        // This is a placeholder implementation, you will need to adjust it based on how the fields are structured in the Document
        return document.getText();
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String uploadDir = fileStorageLocation.toString();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path uploadPath = Paths.get(uploadDir);

        try {
            // Create the upload directory if it doesn't exist
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                System.out.println("Upload directory created: " + uploadPath.toAbsolutePath());
            }

            // Resolve the file path
            Path filePath = uploadPath.resolve(fileName);
            System.out.println("File path resolved: " + filePath.toAbsolutePath());

            // Copy the file to the upload directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved successfully: " + filePath.toAbsolutePath());

        } catch (IOException ex) {
            System.err.println("Error saving file: " + ex.getMessage());
            throw ex;
        }

        return fileName;
    }

}
