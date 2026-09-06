package com.jp.e_m_s.Service;

import com.jp.e_m_s.DTO.LeaveRequestDTO;
import com.jp.e_m_s.DTO.LeaveResponseDTO;
import com.jp.e_m_s.Entity.Employee;
import com.jp.e_m_s.Entity.Leave;
import com.jp.e_m_s.Entity.Leave_Status;
import com.jp.e_m_s.Entity.Role;
import com.jp.e_m_s.Exception.EmployeeNotFoundException;
import com.jp.e_m_s.Exception.LeaveNotFoundException;
import com.jp.e_m_s.Repository.EmployeeRepository;
import com.jp.e_m_s.Repository.LeaveRepository;
import com.jp.e_m_s.Security.SecurityUtil;
import com.jp.e_m_s.mapper.LeaveMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveMapper leaveMapper;

    public LeaveService(
            LeaveRepository leaveRepository,
            EmployeeRepository employeeRepository,
            LeaveMapper leaveMapper) {

        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
        this.leaveMapper = leaveMapper;
    }
    public LeaveResponseDTO applyLeave(LeaveRequestDTO leave){

        if (leave.getStartDate().isAfter(leave.getEndDate())) {
            throw new IllegalArgumentException(
                    "Start date cannot be after end date"
            );
        }
        String email = SecurityUtil.getCurrentUserEmail();

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));
        Leave newLeave=new Leave();
        newLeave.setLeaveType(leave.getLeaveType());
        newLeave.setLeaveStatus(Leave_Status.PENDING);
        newLeave.setApplieddate(LocalDate.now());
        newLeave.setStartdate(leave.getStartDate());
        newLeave.setEnddate(leave.getEndDate());
        newLeave.setReason(String.valueOf(leave.getReason()));
        newLeave.setEmployee(employee);

        Leave savedleave=leaveRepository.save(newLeave);

        return leaveMapper.toResponse(savedleave);





    }
    public List<LeaveResponseDTO> getAll() {

        String email = SecurityUtil.getCurrentUserEmail();

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Logged-in employee not found"));

        List<Leave> leaves;

        if (employee.getRole() == Role.ADMIN) {
            // Admin sees all leaves
            leaves = leaveRepository.findAll();
        } else {
            // Employee sees only their own leaves
            leaves = leaveRepository
                    .findByEmployeeEmployeeId(employee.getEmployeeId());
        }

        return leaves.stream()
                .map(leaveMapper::toResponse)
                .toList();
    }
    public LeaveResponseDTO getleaveById(long id){
        Leave leave=leaveRepository.findById(id).orElseThrow(()->new RuntimeException("Not found"));
        return leaveMapper.toResponse(leave);
    }
    public ResponseEntity<String> deleteLeave_ID(long id){
        Leave leave=leaveRepository.findById(id).orElseThrow(()->new LeaveNotFoundException("Leave with "+id+" Not exists"));
        leaveRepository.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
    public List<LeaveResponseDTO> getLeavesByEmployee(Long employeeId) {

        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException(
                    "Employee not found with ID: " + employeeId
            );
        }

        return leaveRepository.findByEmployeeEmployeeId(employeeId)
                .stream()
                .map(leaveMapper::toResponse)
                .toList();
    }
    public LeaveResponseDTO approveLeave(Long id) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new LeaveNotFoundException(
                                "Leave not found with ID: " + id
                        ));

        if (leave.getLeaveStatus() != Leave_Status.PENDING) {
            throw new IllegalStateException(
                    "Only pending leaves can be approved"
            );
        }

        leave.setLeaveStatus(Leave_Status.APPROVED);

        Leave updatedLeave = leaveRepository.save(leave);

        return leaveMapper.toResponse(updatedLeave);
    }
    public LeaveResponseDTO rejectLeave(Long id) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new LeaveNotFoundException(
                                "Leave not found with ID: " + id
                        ));

        if (leave.getLeaveStatus() != Leave_Status.PENDING) {
            throw new IllegalStateException(
                    "Only pending leaves can be rejected"
            );
        }

        leave.setLeaveStatus(Leave_Status.REJECTED);

        Leave updatedLeave = leaveRepository.save(leave);

        return leaveMapper.toResponse(updatedLeave);
    }

}