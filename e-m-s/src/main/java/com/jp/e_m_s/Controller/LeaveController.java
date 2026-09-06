package com.jp.e_m_s.Controller;

import com.jp.e_m_s.DTO.LeaveRequestDTO;
import com.jp.e_m_s.DTO.LeaveResponseDTO;
import com.jp.e_m_s.Service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
/*this is demo project
yes
yes
yes*/
@RestController
@RequestMapping("/leaves")
public class LeaveController {
    public final LeaveService leaveService;
    LeaveController(LeaveService leaveService){
        this.leaveService=leaveService;

    }
    @PostMapping
    public LeaveResponseDTO Apply_Leave(@Valid @RequestBody LeaveRequestDTO request){
        return  leaveService.applyLeave(request);

    }

    @GetMapping
    public List<LeaveResponseDTO> getAllleaves() {

        return leaveService.getAll();
    }
    @GetMapping("/employee/{employeeId}")
    public List<LeaveResponseDTO> getLeavesByEmployee(
            @PathVariable Long employeeId) {

        return leaveService.getLeavesByEmployee(employeeId);
    }
    @GetMapping("/{id}")
    public LeaveResponseDTO getById(@PathVariable long id){
       return leaveService.getleaveById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLeave(@PathVariable long id){
        return leaveService.deleteLeave_ID(id);
    }
    @PutMapping("/{id}/approve")
    public LeaveResponseDTO approveLeave(@PathVariable Long id) {

        return leaveService.approveLeave(id);
    }
    @PutMapping("/{id}/reject")
    public LeaveResponseDTO rejectLeave(@PathVariable Long id) {

        return leaveService.rejectLeave(id);
    }




}
