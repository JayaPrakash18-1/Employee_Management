package com.jp.e_m_s.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    private String address;

    @NotBlank
    private String designation;

    private LocalDate joiningDate;

    @Min(15000)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;




    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


     @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}