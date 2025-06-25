package com.careermatch.pamtenproject.repository;
import com.careermatch.pamtenproject.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email); // ✅ This line fixes the error

    Optional<User> findByEmail(String email); // (Useful later for login)
}