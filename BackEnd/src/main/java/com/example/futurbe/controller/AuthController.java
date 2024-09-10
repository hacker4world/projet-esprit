package com.example.futurbe.controller;

import com.example.futurbe.dto.userDTO.*;
import com.example.futurbe.entitys.Role;
import com.example.futurbe.entitys.User;
import com.example.futurbe.jwt.JwtUtils;
import com.example.futurbe.repositorys.UserRepository;
import com.example.futurbe.services.EmailService;
import com.example.futurbe.services.iservices.IUserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Random;


@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {
    @Autowired
    private EmailService emailService;
    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;
// credential
    //username =testtest06-2024
    //Password ="string"


    //usename = mb06-2024
    //Password =4<Pea4
    final
    AuthenticationManager authenticationManager;

    final
    UserRepository userRepository;

    final
    IUserService userService;
    final
    JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, IUserService userService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Attempt authentication
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            // Handle failed login attempt (increment attempts)
            User user = userRepository.findByEmail(loginRequest.getEmail()); // Find user by username
            if (user != null) {
                user.updateLoginAttempts(false); // Increment login attempts
                userRepository.save(user); // Save updated user with incremented attempts
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User userDetails = (User) authentication.getPrincipal();

        // Check account lock status before generating JWT
        if (!userDetails.isAccountNonLocked()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Account locked due to multiple failed login attempts");
        }

        // Successful login, generate JWT and response
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(
                        userDetails.getId(),
                        userDetails.getFirstName(),
                        userDetails.getLastName(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getRole(),
                        userDetails.getResumeURI(),
                        userDetails.getResume() != null ? userDetails.getResume().getId() : 0,
                        userDetails.getProfilePicURI() != null ? userDetails.getProfilePicURI() : "",
                        userDetails.getProfilePicURI() != null ? userDetails.getProfilePicURI() : ""));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
        }
        signUpRequest.setRole(Role.STUDENT);
        signUpRequest.setCreatedDate(new Date());
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        signUpRequest.setPassword(encodedPassword);
        log.info(signUpRequest);
        userService.saveUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }


    @PostMapping("/forget/{userName}")
    public ResponseEntity<String> forgetPassWord(@Valid @PathVariable String userName) {
        // Attempt authentication
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        String allChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\"\\|,.<>/?";
        // Generate random 10-character password
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(allChars.length());
            password.append(allChars.charAt(index));
        }

        // Send email with the new password
        emailService.sendEmail(user.getEmail(), "Forget Password", "Your new password is: " + password.toString());

        // Reset login attempts and update password
        user.setLoginAttempts(0);
        user.setPassword(passwordEncoder.encode(password.toString()));
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully!");
    }

    @PostMapping("/qrcode/{userName}")
    public ResponseEntity<String> receiveQrCode(@Valid @PathVariable String userName, @RequestBody QrCodeRequest qrCodeRequest) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        try {
            // Decode the base64 encoded string to byte array
            byte[] decodedBytes = Base64.getDecoder().decode(qrCodeRequest.getData());

            // Send email with the QR code as an attachment
            emailService.sendEmailWithAttachment(user.getEmail(), "QR Code", "Please find the attached QR code for verification.", decodedBytes, "qrcode.png");
            return ResponseEntity.ok("QR code sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send QR code via email.");
        }
    }
}