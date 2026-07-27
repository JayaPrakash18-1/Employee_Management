package com.jp.e_m_s.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @NotBlank
    @Column(unique = true)
    private String departmentName;

    @NotBlank
    @Column(unique = true)
    private String departmentCode;

    @NotBlank
    private String location;

}