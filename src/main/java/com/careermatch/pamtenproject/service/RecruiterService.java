package com.careermatch.pamtenproject.service;

import com.careermatch.pamtenproject.model.Recruiter;
import com.careermatch.pamtenproject.model.User;
import com.careermatch.pamtenproject.repository.RecruiterRepository;
import com.careermatch.pamtenproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final UserRepository userRepository;

    public Recruiter createOrUpdateRecruiterProfile(Integer userId, Recruiter recruiterData) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Recruiter> existing = recruiterRepository.findByUserUserId(userId);
        Recruiter recruiter;
        if (existing.isPresent()) {
            recruiter = existing.get();
            // Update fields
            recruiter.setCompanyName(recruiterData.getCompanyName());
            recruiter.setContactNumber(recruiterData.getContactNumber());
            recruiter.setPosition(recruiterData.getPosition());
            recruiter.setLinkedin(recruiterData.getLinkedin());
        } else {
            recruiter = Recruiter.builder()
                    .user(user)
                    .companyName(recruiterData.getCompanyName())
                    .contactNumber(recruiterData.getContactNumber())
                    .position(recruiterData.getPosition())
                    .linkedin(recruiterData.getLinkedin())
                    .build();
        }
        return recruiterRepository.save(recruiter);
    }

    public Recruiter getRecruiterProfile(Integer userId) {
        return recruiterRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Recruiter profile not found"));
    }
}