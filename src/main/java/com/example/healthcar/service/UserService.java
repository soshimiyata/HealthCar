package com.example.healthcar.service;

import com.example.healthcar.dto.UserResponse;
import com.example.healthcar.dto.UserCreateRequest;
import com.example.healthcar.entity.User;
import com.example.healthcar.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserResponse createUser(UserCreateRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("メールアドレスは既に登録されています");
    }

    User user = new User();

    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPassword(
        passwordEncoder.encode(request.getPassword()));

    User savedUser = userRepository.save(user);

    return new UserResponse(
        savedUser.getId(),
        savedUser.getName(),
        savedUser.getEmail());
  }
}