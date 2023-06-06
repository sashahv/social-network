package com.olekhv.socialnetwork.service;

import com.olekhv.socialnetwork.config.JwtService;
import com.olekhv.socialnetwork.dto.AuthenticationResponse;
import com.olekhv.socialnetwork.dto.AuthorizationRequest;
import com.olekhv.socialnetwork.dto.RegisterRequest;
import com.olekhv.socialnetwork.exception.UserAlreadyExistsException;
import com.olekhv.socialnetwork.model.user.User;
import com.olekhv.socialnetwork.model.userCredential.UserCredential;
import com.olekhv.socialnetwork.repository.UserCredentialRepository;
import com.olekhv.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserCredentialRepository userCredentialRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        String requestEmail = request.getEmail();
        if(userCredentialRepository.findByEmail(requestEmail).isPresent()){
            throw new UserAlreadyExistsException("User with email " + requestEmail + " already exists");
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .birthDate(request.getBirthDate())
                .build();

        userRepository.save(user);

        UserCredential userCredential = UserCredential.builder()
                .user(user)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userCredentialRepository.save(userCredential);

        String token = jwtService.generateToken(userCredential);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authorize(AuthorizationRequest request){
        String requestEmail = request.getEmail();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestEmail,
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        UserCredential userCredential = userCredentialRepository.findByEmail(requestEmail).orElseThrow(
                () -> new UsernameNotFoundException("User " + requestEmail + " not found")
        );

        String token = jwtService.generateToken(userCredential);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
