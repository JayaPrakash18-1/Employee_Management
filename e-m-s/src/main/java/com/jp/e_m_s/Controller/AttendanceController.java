package com.jp.e_m_s.Controller;

import com.jp.e_m_s.DTO.AttendanceRequestDTO;
import com.jp.e_m_s.DTO.AttendanceResponseDTO;
import com.jp.e_m_s.Service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // Check In
    @PostMapping("/check-in")
    public AttendanceResponseDTO checkIn(
            @Valid @RequestBody AttendanceRequestDTO request) {

        return attendanceService.checkIn(request);
    }

    // Check Out
    @PutMapping("/check-out/{id}")
    public AttendanceResponseDTO checkOut(
            @PathVariable Long id) {

        return attendanceService.checkOut(id);
    }

    // Get All Attendance
    @GetMapping
    public List<AttendanceResponseDTO> getAll() {

        return attendanceService.getAll();
    }

    // Get Attendance By ID
    @GetMapping("/{id}")
    public AttendanceResponseDTO getById(
            @PathVariable Long id) {

        return attendanceService.getById(id);
    }

    // Get Attendance By Employee
    @GetMapping("/employee/{employeeId}")
    public List<AttendanceResponseDTO> getByEmployee(
            @PathVariable Long employeeId) {

        return attendanceService.getByEmployee(employeeId);
    }

    // Get Attendance By Date
    @GetMapping("/date/{date}")
    public List<AttendanceResponseDTO> getByDate(
            @PathVariable LocalDate date) {

        return attendanceService.getByDate(date);
    }

    // Delete Attendance
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        attendanceService.delete(id);

        return "Attendance deleted successfully";
    }
}