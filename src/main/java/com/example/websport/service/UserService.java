package com.example.websport.service;

import com.example.websport.model.User;
import com.example.websport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public boolean register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false; // Tên đã tồn tại
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // TODO: nên mã hoá password
        user.setRole("USER");
        userRepository.save(user);
        return true;
    }

    public Map<String, String> loginWithRole(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            Map<String, String> result = new HashMap<>();
            result.put("message", "Đăng nhập thành công");
            result.put("role", user.get().getRole()); // ADMIN hoặc USER
            return result;
        }
        return null;
    }

    public Long getUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElse(null);
    }

    public boolean login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(u -> u.getPassword().equals(password)).orElse(false);
    }

    public User updateUser(Integer id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        if (user.getRole() != null) {
            existingUser.setRole(user.getRole());
        }
        return userRepository.save(existingUser);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
