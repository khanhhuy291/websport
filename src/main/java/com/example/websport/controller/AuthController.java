package com.example.websport.controller;

import com.example.websport.model.User;
import com.example.websport.repository.UserRepository;
import com.example.websport.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Tìm user
            User user = userRepository.findByUsername(loginRequest.username())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

            // Kiểm tra mật khẩu trực tiếp
            if (!loginRequest.password().equals(user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("Tên tài khoản hoặc mật khẩu không đúng"));
            }

            // Tạo token
            String token = jwtService.generateToken(user);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", user.getUsername());
            response.put("role", user.getRole());
            response.put("fullName", user.getFullName());
            response.put("userId", user.getId());

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Tên tài khoản hoặc mật khẩu không đúng"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Đã xảy ra lỗi trong quá trình đăng nhập"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            if (userRepository.existsByUsername(registerRequest.username())) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Tên tài khoản này đã được sử dụng"));
            }

            User user = new User();
            user.setUsername(registerRequest.username());
            // Lưu mật khẩu dạng plain text
            user.setPassword(registerRequest.password());
            user.setFullName(registerRequest.fullName());
            user.setPhone(registerRequest.phone());
            user.setRole("USER");

            userRepository.save(user);

            // Tự động đăng nhập sau khi đăng ký
            String token = jwtService.generateToken(user);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", user.getUsername());
            response.put("message", "Đăng ký thành công");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Đã xảy ra lỗi trong quá trình đăng ký"));
        }
    }
}

record LoginRequest(String username, String password) {
}

record RegisterRequest(String username, String password, String fullName, String phone) {
}

record ErrorResponse(String message) {
}