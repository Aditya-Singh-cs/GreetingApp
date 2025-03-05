package com.example.GreetingApp.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
public class AuthUserDTO {
    public AuthUserDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "First name must start with an uppercase letter.")
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Last name must start with an uppercase letter.")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Pattern(regexp = "^(?=.[A-Z])(?=.\\d)(?=.[@#$%^&()\\-+=])[A-Za-z\\d@#$%^&*()\\-+=]{8,}$",
            message = "Password must contain at least 1 uppercase letter, 1 special character, 1 number, and be at least 8 characters long.")
    private String password;
}