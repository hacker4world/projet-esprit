package com.example.futurbe.services;
import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.Document;
import com.example.futurbe.entitys.User;
import com.example.futurbe.mapper.UserMapper;
import com.example.futurbe.repositorys.DocumentRepository;
import com.example.futurbe.repositorys.OffreRepository;
import com.example.futurbe.repositorys.UserRepository;
import com.example.futurbe.services.iservices.IUserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    OffreRepository offreRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    @Autowired
    @Lazy
    WebSecurityConfig encoder;
    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::convertToDto).orElse(null);
    }

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        User user = userMapper.convertToEntity(userDTO);

        // Encode the password
        user.setDocuments(null);
        user = userRepository.save(user);
        return userMapper.convertToDto(user);
    }
    public void deleteUser(Long id) {

        if(offreRepository.existsByUserId(id)){
            offreRepository.deleteByUserId(id);
        }
//        List<Document> documents = documentRepository.findByUserId(id);
//        if (!documents.isEmpty()) {
//            documentRepository.deleteAll(documents); // Delete all associated documents
//        }
        log.info(id);
        userRepository.deleteById(id);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository.findByUsername(username);
    }
}
