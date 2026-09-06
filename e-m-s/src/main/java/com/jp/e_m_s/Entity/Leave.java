package com.jp.e_m_s.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Leave {
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long Leave_id;
    @ManyToOne
    @JoinColumn(name="employee_id",nullable = false)
    private Employee employee;
    @Enumerated(EnumType.STRING)
    @NotNull
    private LeaveType leaveType;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Leave_Status leaveStatus;
    @NotNull
    private LocalDate startdate;
    @NotNull
    private LocalDate enddate;
    @NotNull
    private LocalDate applieddate;
    @Size(max = 500)
    private String reason;



}
