package com.megadel.client.controller;

import com.megadel.client.exception.AppException;
import com.megadel.client.security.JwtTokenProvider;
import com.megadel.models.Role;
import com.megadel.models.User;
import com.megadel.models.projectenum.RoleName;
import com.megadel.repository.RoleRepository;
import com.megadel.repository.UserRepository;
import com.megadel.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final
    AuthenticationManager authenticationManager;

    final
    UserRepository userRepository;

    final
    RoleRepository roleRepository;

    final
    PasswordEncoder passwordEncoder;

    final
    JwtTokenProvider tokenProvider;

    final
    VerificationTokenService verificationTokenService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider, VerificationTokenService verificationTokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.verificationTokenService = verificationTokenService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User> user = null;
        User tempUser = new User();
        User tempUser1 = new User();
        try {
            user = userRepository.findByEmail(loginRequest.getUsernameOrEmail());
        } catch (Exception e) {
            user = null;
        }

        Optional<User> user1 = null;
        try {
            user1 = userRepository.findByUsername(loginRequest.getUsernameOrEmail());
        } catch (Exception e) {
            user1 = null;
        }

        tempUser.setIsActive(user.get().getIsActive());
        tempUser1.setIsActive(user.get().getIsActive());
        if(!tempUser.getIsActive() || !tempUser1.getIsActive()) {
            return new ResponseEntity(new ApiResponse(false,
                    "Sorry! You haven't verified your account..."),
                    HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        user.setIsActive(false);

        User result = userRepository.save(user);

        verificationTokenService.createVerification(result.getEmail());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true,
                "A verification email has been sent to: " + result.getEmail()));
    }

    @PostMapping("/resend-token")
    public ResponseEntity<?> resendToken( @Valid @RequestBody User userEmail) {
        Optional<User> user = userRepository.findByEmail(userEmail.getEmail());
        User tempUser;
        try {
            tempUser = user.get();
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse(false,
                    "Sorry! No account found with this email..."),
                    HttpStatus.BAD_REQUEST);
        }

        tempUser.setIsActive(user.get().getIsActive());

        if(tempUser.getIsActive()) {
            return new ResponseEntity(new ApiResponse(false,
                    "Sorry! Your account is already verified..."),
                    HttpStatus.BAD_REQUEST);
        }

        verificationTokenService.createVerification(userEmail.getEmail());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(tempUser.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true,
                "Token resent! Please check your mail..."));
    }

    @RequestMapping(value="/verify-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String verifyAccount( @RequestParam("token")String token) {
        return verificationTokenService.verifyAccount(token).getBody();
    }
}