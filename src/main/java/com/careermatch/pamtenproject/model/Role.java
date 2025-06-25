package com.careermatch.pamtenproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_id")
    private Integer roleId;

    @Column(name = "Role_name", nullable = false, unique = true)
    private String roleName;
}