package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "work_schedule")
public class WorkSchedule extends BaseEntity {

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "doctor_id")
    private Doctor doctor;

    @Column(name = "work_date")
    private LocalDate workDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    protected WorkSchedule() {}
    protected WorkSchedule(Doctor doctor, LocalTime startTime, LocalTime endTime, Status status) {
        this.doctor = doctor;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
}
