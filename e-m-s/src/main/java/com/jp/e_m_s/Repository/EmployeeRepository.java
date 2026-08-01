package com.jp.e_m_s.Repository;

import com.jp.e_m_s.Entity.Employee;
import com.jp.e_m_s.Entity.EmployeeStatus;
import com.jp.e_m_s.Entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>,
        JpaSpecificationExecutor<Employee> {

    // Search by Name
    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );
    List<Employee> findByDepartmentDepartmentId(Long departmentId);
    // Search by Email
    Optional<Employee> findByEmailIgnoreCase(String email);
    List<Employee> findByStatus(EmployeeStatus status);
    List<Employee> findByGender(Gender gender);
    List<Employee> findBySalaryBetween(BigDecimal minSalary,
                                       BigDecimal maxSalary);
    List<Employee> findByJoiningDateBetween(LocalDate startDate,
                                            LocalDate endDate);
    List<Employee> findBySalaryGreaterThanEqual(BigDecimal salary);
    List<Employee> findBySalaryLessThanEqual(BigDecimal salary);
}