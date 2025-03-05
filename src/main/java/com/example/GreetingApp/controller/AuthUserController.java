package com.example.GreetingApp.controller;

import com.example.GreetingApp.dto.AuthUserDTO;
import com.example.GreetingApp.dto.LoginDTO;
import com.example.GreetingApp.service.AuthenticationService;
import com.example.GreetingApp.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private EmailService emailService;  // Inject EmailService

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthUserDTO authUserDTO) {
        String response = authenticationService.registerUser(authUserDTO);
        String fullName = authUserDTO.getFirstName() + " " + authUserDTO.getLastName();

        // Define the attachment path (inside the project)
        String attachmentPath = "src/main/resources/static/Welcome.pdf";
        File attachmentFile = new File(attachmentPath);

        if (attachmentFile.exists()) {
            try {
                emailService.sendEmailWithAttachment(
                        authUserDTO.getEmail(),
                        "Welcome to Our App!",
                        "Hello " + fullName + ",\n\nYour registration was successful! Please find the attached document.\n\nBest Regards,\nYour App Team",
                        attachmentPath
                );
                return ResponseEntity.ok("User registered successfully! Email with attachment sent.");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Error sending email: " + e.getMessage());
            }
        } else {
            // Send simple email if no attachment exists
            emailService.sendSimpleEmail(
                    authUserDTO.getEmail(),
                    "Welcome to Our App!",
                    "Hello " + fullName + ",\n\nThank you for registering!\n\nBest Regards,\nYour App Team"
            );

            return ResponseEntity.ok("User registered successfully! Simple email sent.");
        }

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        String token = authenticationService.loginUser(loginDTO);
        return ResponseEntity.ok().body("{\"message\": \"Login successful!\", \"token\": \"" + token + "\"}");
    }
}
