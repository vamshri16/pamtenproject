package com.careermatch.pamtenproject.repository;

import com.careermatch.pamtenproject.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {
    Optional<Recruiter> findByUserUserId(Integer userId);
}