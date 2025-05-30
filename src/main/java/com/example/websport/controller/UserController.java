package com.example.websport.controller;

import com.example.websport.model.User;
import com.example.websport.repository.UserRepository;
import com.example.websport.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    // Đường dẫn thư mục lưu trữ ảnh. Cần khớp với MvcConfig!
    private static final String UPLOAD_DIR = "/Users/khanhhuy/IdeaProjects/websport/uploaded_images/"; // <--- Đảm bảo
                                                                                                       // khớp

    // Lấy danh sách tất cả user (có phân trang)
    @GetMapping
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String role) {
        Pageable pageable = PageRequest.of(page, size);
        if (role != null && !role.isEmpty()) {
            return userRepository.findByRole(role, pageable);
        }
        return userRepository.findAll(pageable);
    }

    // Lấy thông tin user theo id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get()); // Trả về User nếu tìm thấy
        } else {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy
        }
    }

    // Tạo mới user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Cập nhật user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Xóa user
    @DeleteMapping("/{id}") // Đã sửa cú pháp @DeleteMapping
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    // Endpoint để upload ảnh đại diện cho user
    @PostMapping("/{id}/uploadAvatar")
    public ResponseEntity<?> uploadUserAvatar(@PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            // Lấy user theo ID
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            User user = userOptional.get();

            // Tạo thư mục upload nếu chưa tồn tại
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Tạo tên file duy nhất
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
            Path filePath = uploadPath.resolve(uniqueFileName);

            // Lưu file vào hệ thống
            Files.copy(file.getInputStream(), filePath);

            // Cập nhật imageUrl trong database
            String relativeImageUrl = uniqueFileName; // Hoặc "/images/" + uniqueFileName nếu cấu hình phục vụ tĩnh dùng
                                                      // prefix
            user.setImageUrl(relativeImageUrl);
            userRepository.save(user); // Lưu user đã cập nhật

            return ResponseEntity.ok("Avatar uploaded successfully. Image URL: " + relativeImageUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not upload the file: " + file.getOriginalFilename() + "!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}