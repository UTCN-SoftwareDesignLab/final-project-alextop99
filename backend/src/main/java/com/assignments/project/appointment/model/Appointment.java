package com.assignments.project.appointment.model;

import com.assignments.project.car.model.Car;
import com.assignments.project.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 120)
    private String details;

    @Column(nullable = false)
    private Boolean finished;
}
