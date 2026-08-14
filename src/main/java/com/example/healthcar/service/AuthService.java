package com.example.healthcar.service;

import com.example.healthcar.dto.auth.LoginRequest;
import com.example.healthcar.dto.auth.LoginResponse;
import com.example.healthcar.entity.User;
import com.example.healthcar.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthService(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      JwtService jwtService) {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public LoginResponse login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new IllegalArgumentException(
            "メールアドレスまたはパスワードが不正です"));

    if (!passwordEncoder.matches(
        request.getPassword(),
        user.getPassword())) {

      throw new IllegalArgumentException(
          "メールアドレスまたはパスワードが不正です");
    }

    String accessToken = jwtService.generateToken(user.getId());

    return new LoginResponse(
        accessToken,
        "Bearer",
        jwtService.getExpirationSeconds());
  }
}