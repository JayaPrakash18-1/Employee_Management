package com.jp.e_m_s.DTO;

import com.jp.e_m_s.Entity.AttendanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AttendanceRequestDTO {

    @NotNull
    private LocalDate attendanceDate;

    private AttendanceStatus status;
}