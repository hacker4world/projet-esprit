package com.example.futurbe.services.iservices;

import com.example.futurbe.dto.userDTO.ResumeDTO;

import java.util.List;

public interface IResumeService {
    List<ResumeDTO> getAllResumes();
    ResumeDTO getResumeById(Long id);
    ResumeDTO saveResume(Long id,ResumeDTO resumeDTO);
    void deleteResume(Long id);
}
