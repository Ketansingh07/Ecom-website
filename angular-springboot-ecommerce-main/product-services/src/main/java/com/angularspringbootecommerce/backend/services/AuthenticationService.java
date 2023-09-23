package com.angularspringbootecommerce.backend.services;

import com.angularspringbootecommerce.backend.dtos.ModLoginDto;
import com.angularspringbootecommerce.backend.models.Mod;
import com.angularspringbootecommerce.backend.models.ModRole;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;
import com.angularspringbootecommerce.backend.repository.ModRepository;
import com.angularspringbootecommerce.backend.repository.ModRoleRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private ModRepository userRepository;

    @Autowired
    private ModRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public Mod register(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        // Check if the UserRole with authority "USER" exists
        Optional<ModRole> userRoleOptional = userRoleRepository.findByAuthority("MOD");
        
        if (userRoleOptional.isPresent()) {
            ModRole userRole = userRoleOptional.get();
            Set<ModRole> authorities = new HashSet<>();
            authorities.add(userRole);

            return userRepository.save(new Mod(email, encodedPassword, authorities));
        } else {
            // Handle the case where the UserRole is not found (e.g., throw an exception or return null)
            // For example:
            throw new RuntimeException("UserRole with authority 'MOD' not found");
        }
    }


    public ModLoginDto login(String email, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            String token = tokenService.generateJwt(auth);

            Mod user = userRepository.findByEmail(email).orElse(null);
            if (user != null) {
                return new ModLoginDto(user.getId(), user, token);
            } else {
                return new ModLoginDto(null, null, "");
            }

        } catch (AuthenticationException e) {
            return new ModLoginDto(null, null, "");
        }
    }
}
