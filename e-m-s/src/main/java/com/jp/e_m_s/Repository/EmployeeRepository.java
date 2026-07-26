package com.jp.e_m_s.Repository;

import com.jp.e_m_s.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}