package com.jp.e_m_s.Repository;

import com.jp.e_m_s.Entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByEmployeeEmployeeIdAndAttendanceDate(
            Long employeeId,
            LocalDate attendanceDate
    );

    List<Attendance> findByEmployeeEmployeeId(Long employeeId);

    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);
}