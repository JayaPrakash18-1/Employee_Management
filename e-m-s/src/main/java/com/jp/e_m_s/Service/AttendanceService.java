package com.jp.e_m_s.Service;

import com.jp.e_m_s.DTO.AttendanceRequestDTO;
import com.jp.e_m_s.DTO.AttendanceResponseDTO;
import com.jp.e_m_s.Entity.Attendance;
import com.jp.e_m_s.Entity.Employee;
import com.jp.e_m_s.Entity.AttendanceStatus;
import com.jp.e_m_s.Entity.Role;
import com.jp.e_m_s.Repository.AttendanceRepository;
import com.jp.e_m_s.Repository.EmployeeRepository;
import com.jp.e_m_s.Security.SecurityUtil;
import com.jp.e_m_s.mapper.AttendanceMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            EmployeeRepository employeeRepository,
            AttendanceMapper attendanceMapper) {

        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.attendanceMapper = attendanceMapper;
    }

    // Check In
    public AttendanceResponseDTO checkIn(
            AttendanceRequestDTO request) {
        String email = SecurityUtil.getCurrentUserEmail();
        Employee employee = employeeRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        if (attendanceRepository
                .findByEmployeeEmployeeIdAndAttendanceDate(
                        employee.getEmployeeId(),
                        request.getAttendanceDate())
                .isPresent()) {

            throw new IllegalStateException(
                    "Attendance already exists for this date");
        }

        Attendance attendance = new Attendance();

        attendance.setEmployee(employee);
        attendance.setAttendanceDate(
                request.getAttendanceDate()
        );

        attendance.setCheckInTime(LocalTime.now());

        attendance.setStatus(AttendanceStatus.PRESENT);

        Attendance savedAttendance =
                attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(savedAttendance);
    }

    // Check Out
    public AttendanceResponseDTO checkOut(Long id) {

        Attendance attendance = attendanceRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attendance not found"));

        if (attendance.getCheckOutTime() != null) {

            throw new IllegalStateException(
                    "Employee has already checked out");
        }

        attendance.setCheckOutTime(LocalTime.now());

        Attendance updatedAttendance =
                attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(updatedAttendance);
    }

    // Get All
    public List<AttendanceResponseDTO> getAll() {

        String email = SecurityUtil.getCurrentUserEmail();

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Logged-in employee not found"));

        List<Attendance> attendanceList;

        if (employee.getRole() == Role.ADMIN) {
            // Admin sees all attendance
            attendanceList = attendanceRepository.findAll();
        } else {
            // Employee sees only their own attendance
            attendanceList = attendanceRepository
                    .findByEmployeeEmployeeId(
                            employee.getEmployeeId()
                    );
        }

        return attendanceList.stream()
                .map(attendanceMapper::toResponse)
                .toList();
    }

    // Get By ID
    public AttendanceResponseDTO getById(Long id) {

        Attendance attendance =
                attendanceRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Attendance not found"));

        return attendanceMapper.toResponse(attendance);
    }

    // Employee-wise Attendance
    public List<AttendanceResponseDTO> getByEmployee(
            Long employeeId) {

        return attendanceRepository
                .findByEmployeeEmployeeId(employeeId)
                .stream()
                .map(attendanceMapper::toResponse)
                .toList();
    }

    // Daily Attendance
    public List<AttendanceResponseDTO> getByDate(
            java.time.LocalDate date) {

        return attendanceRepository
                .findByAttendanceDate(date)
                .stream()
                .map(attendanceMapper::toResponse)
                .toList();
    }

    // Delete
    public void delete(Long id) {

        Attendance attendance =
                attendanceRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Attendance not found"));

        attendanceRepository.delete(attendance);
    }
}