package com.jp.e_m_s.DTO;

import com.jp.e_m_s.Entity.BloodGroup;
import com.jp.e_m_s.Entity.EmployeeStatus;
import com.jp.e_m_s.Entity.Gender;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeResponseDTO {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Gender gender;

    private LocalDate dateOfBirth;

    private BloodGroup bloodGroup;

    private String address;

    private String designation;

    private LocalDate joiningDate;

    private BigDecimal salary;

    private EmployeeStatus status;
    private String departmentName;
    private Long departmentId;
}