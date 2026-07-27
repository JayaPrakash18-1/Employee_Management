package com.jp.e_m_s.Repository;

import com.jp.e_m_s.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}