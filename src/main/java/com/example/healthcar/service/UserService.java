package com.example.healthcar.service;

import com.example.healthcar.dto.UserResponse;
import com.example.healthcar.dto.UserCreateRequest;
import com.example.healthcar.entity.User;
import com.example.healthcar.repository.UserRepository;
import com.example.healthcar.dto.UserUpdateRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

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

  public UserResponse getCurrentUser(Authentication authentication) {

    Long userId = Long.valueOf(authentication.getName());

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("ユーザーが存在しません"));

    return new UserResponse(
        user.getId(),
        user.getName(),
        user.getEmail());
  }

  public UserResponse updateUser(
      Long userId,
      UserUpdateRequest request) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

    // メールアドレスを変更する場合のみ重複チェック
    if (!user.getEmail().equals(request.getEmail())
        && userRepository.existsByEmail(request.getEmail())) {

      throw new IllegalArgumentException("Email already exists");
    }

    user.setName(request.getName());
    user.setEmail(request.getEmail());

    // パスワードはハッシュ化して保存
    user.setPassword(passwordEncoder.encode(request.getPassword()));

    User savedUser = userRepository.save(user);

    return new UserResponse(
        savedUser.getId(),
        savedUser.getName(),
        savedUser.getEmail());
  }
}