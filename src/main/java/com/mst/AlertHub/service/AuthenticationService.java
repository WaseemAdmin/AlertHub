package com.mst.AlertHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mst.AlertHub.repository.UserRepository;
import com.mst.AlertHub.beans.LoginUsers;
import com.mst.AlertHub.beans.User;
import com.mst.AlertHub.services.AuthService;
import com.mst.AlertHub.services.JWTService;
import com.mst.AlertHub.services.JwtAuthenticationResponse;
import com.mst.AlertHub.services.SignInRequest;
import com.mst.AlertHub.services.SignUpRequest;

@Service
public class AuthenticationService implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        User user = User.builder().name(request.getName()).phoneNumber(request.getPhoneNumber())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);

        var jwt = jwtService.generateToken(user);

//        LoginUsers loginEvent = new LoginUsers(user.getId(),user.getEmail(),null,jwt);

        kafkaProducerService.sendLoginEvent(jwt);

        return JwtAuthenticationResponse.builder().token(jwt).build();

    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findUserByEmail(request.getEmail());
        String jwt = jwtService.generateToken(user);
//		  LoginUsers loginEvent = new LoginUsers(user.getId(),user.getEmail(),null,jwt);
        kafkaProducerService.sendLoginEvent(jwt);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}
