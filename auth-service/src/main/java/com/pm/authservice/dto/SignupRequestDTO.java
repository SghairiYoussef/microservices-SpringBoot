package com.pm.authservice.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequestDTO {
    @NotBlank(message = "Email Field is required")
    @Email(message = "Your Email should be in the correct format")
    private String email;

    @NotBlank(message = "The name field should be required")
    private String name;

    @NotBlank
    @Size(min = 8, message = "Your password should be at least 8 characters long")
    private String password;

    private String confirmPassword;

    @NotBlank
    private String role;

    @AssertTrue(message = "Passwords must match")
    public boolean isPasswordMatching() {
        System.out.println("Validating password match..."); // Or use logger
        return password != null && password.equals(confirmPassword);
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
