package com.jp.e_m_s.specification;

import com.jp.e_m_s.Entity.Employee;
import com.jp.e_m_s.Entity.EmployeeStatus;
import com.jp.e_m_s.Entity.Gender;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeSpecification {

    public static Specification<Employee> hasStatus(EmployeeStatus status) {

        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }

    public static Specification<Employee> hasDepartment(Long departmentId) {

        return (root, query, cb) ->
                cb.equal(
                        root.get("department")
                                .get("departmentId"),
                        departmentId
                );
    }

    public static Specification<Employee> hasGender(Gender gender) {

        return (root, query, cb) ->
                cb.equal(root.get("gender"), gender);
    }

    public static Specification<Employee> salaryBetween(
            BigDecimal minSalary,
            BigDecimal maxSalary) {

        return (root, query, cb) ->
                cb.between(
                        root.get("salary"),
                        minSalary,
                        maxSalary
                );
    }

    public static Specification<Employee> joiningDateBetween(
            LocalDate startDate,
            LocalDate endDate) {

        return (root, query, cb) ->
                cb.between(
                        root.get("joiningDate"),
                        startDate,
                        endDate
                );
    }

}