package com.jp.e_m_s.mapper;

import com.jp.e_m_s.DTO.AttendanceResponseDTO;
import com.jp.e_m_s.Entity.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public AttendanceResponseDTO toResponse(Attendance attendance) {

        AttendanceResponseDTO response = new AttendanceResponseDTO();

        response.setAttendanceId(attendance.getAttendanceId());

        response.setEmployeeId(
                attendance.getEmployee().getEmployeeId()
        );

        response.setEmployeeName(
                attendance.getEmployee().getFirstName()
                        + " "
                        + attendance.getEmployee().getLastName()
        );

        response.setAttendanceDate(
                attendance.getAttendanceDate()
        );

        response.setCheckInTime(
                attendance.getCheckInTime()
        );

        response.setCheckOutTime(
                attendance.getCheckOutTime()
        );

        response.setStatus(
                attendance.getStatus()
        );

        return response;
    }
}