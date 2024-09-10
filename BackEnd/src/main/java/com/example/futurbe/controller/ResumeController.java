package com.example.futurbe.controller;


import com.example.futurbe.dto.userDTO.ResumeDTO;
import com.example.futurbe.services.iservices.IResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/resumes")
public class ResumeController {
    @Autowired
    IResumeService resumeService;

    @GetMapping
    public List<ResumeDTO> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeDTO> getResumeById(@PathVariable Long id) {
        ResumeDTO resumeDTO = resumeService.getResumeById(id);
        if (resumeDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resumeDTO);
    }
    @PostMapping("/{idUser}")
    public ResumeDTO createResume(@PathVariable Long idUser, @RequestBody ResumeDTO resumeDTO) {
        return resumeService.saveResume(idUser, resumeDTO);
    }

    @PutMapping("/{id}/{idUser}")
    public ResponseEntity<ResumeDTO> updateResume(@PathVariable Long id,@PathVariable Long idUser, @RequestBody ResumeDTO resumeDTO) {
        resumeDTO.setId(id);
        ResumeDTO updatedResume = resumeService.saveResume(idUser,resumeDTO);
        if (updatedResume == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedResume);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.noContent().build();
    }
}
