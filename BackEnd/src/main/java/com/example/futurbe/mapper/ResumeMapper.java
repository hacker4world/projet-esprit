package com.example.futurbe.mapper;

import com.example.futurbe.dto.userDTO.ResumeDTO;
import com.example.futurbe.entitys.Resume;
import org.springframework.stereotype.Component;

@Component
public class ResumeMapper {

    public ResumeDTO convertToDto(Resume resume) {
        ResumeDTO resumeDTO = new ResumeDTO();
        resumeDTO.setId(resume.getId());
        resumeDTO.setEmail(resume.getEmail());
        resumeDTO.setDateOfBirth(resume.getDateOfBirth());
        resumeDTO.setEducation(resume.getEducation());
        resumeDTO.setExperience(resume.getExperiance());
        resumeDTO.setSkils(resume.getSkils());
        resumeDTO.setLangue(resume.getLangue());

        return resumeDTO;
    }

    public Resume convertToEntity(ResumeDTO resumeDTO) {
        Resume resume = new Resume();
        resume.setId(resumeDTO.getId());
        resume.setEmail(resumeDTO.getEmail());
        resume.setDateOfBirth(resumeDTO.getDateOfBirth());
        resume.setEducation(resumeDTO.getEducation());
        resume.setExperiance(resumeDTO.getExperience());
        resume.setSkils(resumeDTO.getSkils());
        resume.setLangue(resumeDTO.getLangue());

        return resume;
    }
}
