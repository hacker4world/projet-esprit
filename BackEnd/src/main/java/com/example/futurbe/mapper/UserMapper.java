package com.example.futurbe.mapper;

import com.example.futurbe.dto.userDTO.ResumeDTO;
import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.User;
import com.example.futurbe.entitys.Resume;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserMapper {

   ResumeMapper resumeMapper;

    public UserDTO convertToDto(User user) {
        ResumeDTO resumeDTO = user.getResume() != null ? resumeMapper.convertToDto(user.getResume()) : null;

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUserOption(user.getUserOption());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setLoginAttempts(user.getLoginAttempts());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setUpdatedDate(user.getUpdatedDate());
        userDTO.setProfilePicURI(user.getProfilePicURI());
        userDTO.setResumeURI(user.getResumeURI());
        userDTO.setRole(user.getRole());
        userDTO.setResume(resumeDTO);

        return userDTO;
    }

    public User convertToEntity(UserDTO userDTO) {
        Resume resume = userDTO.getResume() != null ? resumeMapper.convertToEntity(userDTO.getResume()) : null;

        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setUserOption(userDTO.getUserOption());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setCreatedDate(userDTO.getCreatedDate());
        user.setUpdatedDate(userDTO.getUpdatedDate());
        user.setProfilePicURI(userDTO.getProfilePicURI());
        user.setResumeURI(userDTO.getResumeURI());
        user.setRole(userDTO.getRole());
        user.setResume(resume);

        return user;
    }
}
