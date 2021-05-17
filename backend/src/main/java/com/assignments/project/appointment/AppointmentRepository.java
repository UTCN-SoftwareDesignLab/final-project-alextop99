package com.assignments.project.appointment;

import com.assignments.project.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByDateTimeBetweenAndUserIdOrderByDateTimeAsc(LocalDateTime startDate, LocalDateTime endDate, Long userId);
    List<Appointment> findAllByCarId(Long carId);
    List<Appointment> findAllByCarIdAndDateTimeNotOrderByDateTimeDesc(Long carId, LocalDateTime date);
}
