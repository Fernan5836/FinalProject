package com.project.library.service;

import com.project.library.model.User;
import com.project.library.repository.UserRepository;
import com.project.library.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()){
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())){
                return jwtTokenProvider.createToken(user.getUsername());
            }
        }
        throw new RuntimeException("Usuario o contraseña invalido");
    }
}
