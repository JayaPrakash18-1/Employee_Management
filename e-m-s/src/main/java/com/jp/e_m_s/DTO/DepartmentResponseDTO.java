package com.jp.e_m_s.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DepartmentResponseDTO {

    private Long departmentId;
    private String departmentName;
    private String departmentCode;
    private String location;
}