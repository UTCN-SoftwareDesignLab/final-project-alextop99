package com.assignments.project.appointment.dto;

import com.assignments.project.car.dto.CarDTO;
import com.assignments.project.user.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;
    private CarDTO car;
    private UserDTO user;
    private String details;
    private Boolean finished;
}