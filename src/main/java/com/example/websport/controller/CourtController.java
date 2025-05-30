package com.example.websport.controller;

import com.example.websport.model.Court;
import com.example.websport.service.CourtService;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/courts")
@CrossOrigin(origins = "*")
public class CourtController {

    @Autowired
    private CourtService courtService;

    // Đường dẫn thư mục lưu trữ ảnh. Cần thay đổi!
    private static final String UPLOAD_DIR = "/Users/khanhhuy/IdeaProjects/websport/uploaded_images/"; // <--- Đã thay
                                                                                                       // đổi

    // Lấy danh sách có phân trang
    @GetMapping
    public Page<Court> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courtService.getAll(pageable);
    }

    // Lấy theo id
    @GetMapping("/{id}")
    public Court getById(@PathVariable Integer id) {
        return courtService.getById(id);
    }

    // Tạo mới
    @PostMapping
    public Court create(@RequestBody Court court) {
        return courtService.create(court);
    }

    // Sửa
    @PutMapping("/{id}")
    public Court update(@PathVariable Integer id, @RequestBody Court court) {
        return courtService.update(id, court);
    }

    // Xoá
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        courtService.delete(id);
    }

    // Endpoint để upload ảnh cho sân
    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<?> uploadCourtImage(@PathVariable Integer id,
            @RequestParam("file") MultipartFile file) {
        try {
            // Lấy sân theo ID
            Court court = courtService.getById(id);
            if (court == null) {
                return ResponseEntity.notFound().build();
            }

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
            // Lưu ý: Lưu đường dẫn tương đối để dễ dàng phục vụ file tĩnh
            String relativeImageUrl = uniqueFileName; // Hoặc "/images/" + uniqueFileName nếu cấu hình phục vụ tĩnh dùng
                                                      // prefix
            court.setImageUrl(relativeImageUrl);
            courtService.update(id, court); // Cần có phương thức update trong CourtService

            return ResponseEntity.ok("File uploaded successfully. Image URL: " + relativeImageUrl);

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