package com.example.websport.repository;

import com.example.websport.model.ChildCourts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface ChildCourtsRepository extends JpaRepository<ChildCourts, Long> {
    List<ChildCourts> findByCourtsId(Long courtsId);

    Page<ChildCourts> findByCourtsId(Long courtsId, Pageable pageable);
}