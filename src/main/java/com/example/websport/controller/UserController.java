package com.example.websport.controller;

import com.example.websport.dto.LoginRequest;
import com.example.websport.dto.RegisterRequest;
import com.example.websport.model.User;
import com.example.websport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import java.util.Map;
import java.util.Collections;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getUsers(
            @RequestParam(required = false) Integer id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (id != null) {
            try {
                User user = userService.getUserById(id);
                return new PageImpl<>(
                        Collections.singletonList(user),
                        pageable,
                        1);
            } catch (RuntimeException e) {
                return new PageImpl<>(
                        Collections.emptyList(),
                        pageable,
                        0);
            }
        }

        return userService.getAllUsers(pageable);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        boolean success = userService.register(request.getUsername(), request.getPassword());
        return success ? "Đăng ký thành công" : "Tài khoản đã tồn tại";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Map<String, String> response = userService.loginWithRole(request.getUsername(), request.getPassword());
        if (response != null) {
            response.put("username", request.getUsername()); // Thêm username
            response.put("userId", String.valueOf(userService.getUserIdByUsername(request.getUsername())));
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai tài khoản hoặc mật khẩu");
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
