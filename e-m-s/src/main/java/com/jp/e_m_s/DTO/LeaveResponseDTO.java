package com.jp.e_m_s.DTO;

import com.jp.e_m_s.Entity.Leave_Status;
import com.jp.e_m_s.Entity.LeaveType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LeaveResponseDTO {

    private Long leaveId;

    private Long employeeId;

    private String employeeName;

    private LeaveType leaveType;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;

    private Leave_Status status;

    private LocalDate appliedDate;
}