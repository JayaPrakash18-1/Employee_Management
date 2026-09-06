package com.jp.e_m_s.Repository;

import com.jp.e_m_s.Entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findByEmployeeEmployeeId(Long employeeId);

}