package com.example.websport.repository;

import com.example.websport.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findById(Integer id);

    void deleteById(Integer id);

    Boolean existsByUsername(String username);

    Page<User> findByRole(String role, Pageable pageable);
}