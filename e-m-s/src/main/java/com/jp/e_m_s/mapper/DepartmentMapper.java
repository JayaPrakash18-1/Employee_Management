package com.jp.e_m_s.mapper;

import com.jp.e_m_s.DTO.DepartmentRequestDTO;
import com.jp.e_m_s.DTO.DepartmentResponseDTO;
import com.jp.e_m_s.Entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    // RequestDTO -> Entity
    public Department toEntity(DepartmentRequestDTO requestDTO) {

        Department department = new Department();

        department.setDepartmentName(requestDTO.getDepartmentName());
        department.setDepartmentCode(requestDTO.getDepartmentCode());
        department.setLocation(requestDTO.getLocation());

        return department;
    }

    // Entity -> ResponseDTO
    public  DepartmentResponseDTO toResponse(Department department) {

        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();

        responseDTO.setDepartmentId(department.getDepartmentId());
        responseDTO.setDepartmentName(department.getDepartmentName());
        responseDTO.setDepartmentCode(department.getDepartmentCode());
        responseDTO.setLocation(department.getLocation());

        return responseDTO;
    }

    // Update Existing Entity
    public void updateEntity(Department department,
                             DepartmentRequestDTO requestDTO) {

        department.setDepartmentName(requestDTO.getDepartmentName());
        department.setDepartmentCode(requestDTO.getDepartmentCode());
        department.setLocation(requestDTO.getLocation());
    }
}