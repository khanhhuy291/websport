package com.example.websport.service;

import com.example.websport.model.Court;
import com.example.websport.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourtService {
    @Autowired
    private CourtRepository courtRepository;

    public Page<Court> getAll(Pageable pageable) {
        return courtRepository.findAll(pageable);
    }

    public Court getById(Integer id) {
        return courtRepository.findById(id).orElse(null);
    }

    public Court create(Court court) {
        return courtRepository.save(court);
    }

    public Court update(Integer id, Court court) {
        // Lấy sân hiện có từ database
        Court existingCourt = courtRepository.findById(id).orElse(null);

        // Kiểm tra xem sân có tồn tại không
        if (existingCourt == null) {
            // Trả về null hoặc ném ngoại lệ tùy theo logic xử lý lỗi bạn muốn
            return null;
        }

        // Cập nhật các thuộc tính chỉ khi chúng không null trong đối tượng court nhận
        // được
        if (court.getNameCourt() != null) {
            existingCourt.setNameCourt(court.getNameCourt());
        }
        if (court.getTypeCourt() != null) {
            existingCourt.setTypeCourt(court.getTypeCourt());
        }
        if (court.getPrice() != null) {
            existingCourt.setPrice(court.getPrice());
        }
        if (court.getImageUrl() != null) {
            existingCourt.setImageUrl(court.getImageUrl());
        }

        // Lưu sân đã được cập nhật
        return courtRepository.save(existingCourt);
    }

    public void delete(Integer id) {
        courtRepository.deleteById(id);
    }
}