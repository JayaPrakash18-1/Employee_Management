package com.jp.e_m_s.mapper;

import com.jp.e_m_s.DTO.LeaveResponseDTO;
import com.jp.e_m_s.Entity.Leave;
import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {

    public LeaveResponseDTO toResponse(Leave leave) {

        LeaveResponseDTO response = new LeaveResponseDTO();

        response.setLeaveId(leave.getLeave_id());

        response.setEmployeeId(
                leave.getEmployee().getEmployeeId()
        );

        response.setEmployeeName(
                leave.getEmployee().getFirstName()
                        + " "
                        + leave.getEmployee().getLastName()
        );

        response.setLeaveType(leave.getLeaveType());

        response.setStartDate(leave.getStartdate());

        response.setEndDate(leave.getEnddate());

        response.setReason(leave.getReason());

        response.setStatus(leave.getLeaveStatus());

        response.setAppliedDate(leave.getApplieddate());

        return response;
    }
}