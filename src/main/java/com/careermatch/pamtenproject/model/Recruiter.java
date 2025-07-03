package com.careermatch.pamtenproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Recruiters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruiter_id")
    private Integer recruiterId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    // Add your custom fields here, for example:
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "position")
    private String position;

    @Column(name = "linkedin")
    private String linkedin;
}