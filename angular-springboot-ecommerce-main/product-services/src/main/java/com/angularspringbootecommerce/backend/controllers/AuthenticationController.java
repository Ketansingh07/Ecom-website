package com.angularspringbootecommerce.backend.controllers;

import com.angularspringbootecommerce.backend.dtos.ModDto;
import com.angularspringbootecommerce.backend.dtos.ModLoginDto;
import com.angularspringbootecommerce.backend.exceptions.AppException;
import com.angularspringbootecommerce.backend.models.Mod;
import com.angularspringbootecommerce.backend.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("mod/register")
    public ResponseEntity<?> register(@Valid @RequestBody ModDto mod) {
        try {
            if (mod.getEmail() == null || mod.getEmail().isEmpty() || mod.getPassword() == null || mod.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body("All fields are required.");
            }

            Mod registeredUser = authenticationService.register(mod.getEmail(), mod.getPassword());

            if (registeredUser != null) {
                return ResponseEntity.ok(registeredUser);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Registering");
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            // Return an informative error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Registering: " + e.getMessage());
        }
    }



    @PostMapping("mod/login")
    public ModLoginDto login(@Valid @RequestBody ModDto mod) {
        ModLoginDto modLoginDto = authenticationService.login(mod.getEmail(), mod.getPassword());

        if (modLoginDto.getUser() == null) {
            throw new AppException("Invalid credentials.", HttpStatus.NOT_FOUND);
        }

        return modLoginDto;
    }
}
