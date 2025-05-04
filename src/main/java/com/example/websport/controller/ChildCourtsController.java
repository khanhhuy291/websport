package com.example.websport.controller;

import com.example.websport.model.ChildCourts;
import com.example.websport.repository.ChildCourtsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/childcourts")
@CrossOrigin(origins = "http://localhost:3000")

public class ChildCourtsController {
    @Autowired
    private ChildCourtsRepository childCourtsRepository;

    @GetMapping
    public ResponseEntity<Page<ChildCourts>> getChildCourts(
            @RequestParam(required = false) Long courtsId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        if (courtsId != null) {
            Page<ChildCourts> childCourts = childCourtsRepository.findByCourtsId(courtsId, pageable);
            return ResponseEntity.ok(childCourts);
        }
        return ResponseEntity.ok(childCourtsRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<ChildCourts> createChildCourt(@RequestBody ChildCourts childCourt) {
        ChildCourts savedChildCourt = childCourtsRepository.save(childCourt);
        return ResponseEntity.ok(savedChildCourt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChildCourts> updateChildCourt(@PathVariable Long id,
            @RequestBody ChildCourts updatedChildCourt) {
        return childCourtsRepository.findById(id)
                .map(existingChildCourt -> {
                    // Only update fields that are provided in the request
                    if (updatedChildCourt.getName() != null) {
                        existingChildCourt.setName(updatedChildCourt.getName());
                    }

                    if (updatedChildCourt.getCourts_id() != null) {
                        existingChildCourt.setCourts_id(updatedChildCourt.getCourts_id());
                    }

                    if (updatedChildCourt.getPrice_per_hour() != null) {
                        existingChildCourt.setPrice_per_hour(updatedChildCourt.getPrice_per_hour());
                    }

                    if (updatedChildCourt.getStatus() != null) {
                        existingChildCourt.setStatus(updatedChildCourt.getStatus());
                    }

                    if (updatedChildCourt.getType() != null) {
                        existingChildCourt.setType(updatedChildCourt.getType());
                    }

                    return ResponseEntity.ok(childCourtsRepository.save(existingChildCourt));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChildCourt(@PathVariable Long id) {
        childCourtsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
