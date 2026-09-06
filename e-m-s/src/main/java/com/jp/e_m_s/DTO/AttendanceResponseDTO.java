package com.jp.e_m_s.DTO;

import com.jp.e_m_s.Entity.AttendanceStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AttendanceResponseDTO {

    private Long attendanceId;

    private Long employeeId;

    private String employeeName;

    private LocalDate attendanceDate;

    private LocalTime checkInTime;

    private LocalTime checkOutTime;

    private AttendanceStatus status;
}