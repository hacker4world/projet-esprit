package com.example.futurbe.services;

import com.example.futurbe.dto.userDTO.ResumeDTO;
import com.example.futurbe.entitys.Resume;
import com.example.futurbe.entitys.User;
import com.example.futurbe.mapper.ResumeMapper;
import com.example.futurbe.repositorys.ResumeRepository;
import com.example.futurbe.repositorys.UserRepository;
import com.example.futurbe.services.iservices.IResumeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ResumeService implements IResumeService {
    @Autowired
   ResumeRepository resumeRepository;
    @Autowired
    ResumeMapper resumeMapper;
    @Autowired
    private UserRepository userRepository;

    public List<ResumeDTO> getAllResumes() {
        return resumeRepository.findAll()
                .stream()
                .map(resumeMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public ResumeDTO getResumeById(Long id) {
        Resume resume = resumeRepository.findByUserId(id);
        return resumeMapper.convertToDto(resume);
    }

    public ResumeDTO saveResume(Long id,ResumeDTO resumeDTO) {
        User user = userRepository.findById(id).get();
        Resume resume = resumeMapper.convertToEntity(resumeDTO);
        resume.setUser(user);
        resume = resumeRepository.save(resume);
        return resumeMapper.convertToDto(resume);
    }

    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}
