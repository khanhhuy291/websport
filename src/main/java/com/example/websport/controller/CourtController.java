package com.example.websport.controller;

import com.example.websport.model.Court;
import com.example.websport.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/courts")
@CrossOrigin(origins = "http://localhost:3000")
public class CourtController {

    @Autowired
    private CourtRepository courtRepository;

    @GetMapping
    public ResponseEntity<?> getCourts(
            @RequestParam(required = false) Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (id != null) {
            return courtRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Court> courts = courtRepository.findAll(pageable);
        return ResponseEntity.ok(courts);
    }

    @PostMapping
    public ResponseEntity<Court> createCourt(@RequestBody Court court) {
        Court saved = courtRepository.save(court);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Court> updateCourt(@PathVariable Long id, @RequestBody Court updatedCourt) {
        return courtRepository.findById(id)
                .map(court -> {
                    // Only update fields that are provided in the request
                    if (updatedCourt.getName() != null) {
                        court.setName(updatedCourt.getName());
                    }

                    if (updatedCourt.getAddress() != null) {
                        court.setAddress(updatedCourt.getAddress());
                    }

                    if (updatedCourt.getStatus() != null) {
                        court.setStatus(updatedCourt.getStatus());
                    }

                    if (updatedCourt.getImageUrl() != null) {
                        court.setImageUrl(updatedCourt.getImageUrl());
                    }

                    return ResponseEntity.ok(courtRepository.save(court));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourt(@PathVariable Long id) {
        if (courtRepository.existsById(id)) {
            courtRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
