package com.assignments.project.appointment.mapper;

import com.assignments.project.appointment.dto.AppointmentDTO;
import com.assignments.project.appointment.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(
            target = "user.role",
            ignore = true
    )
    Appointment appointmentFromDTO(AppointmentDTO appointmentDTO);

    @Mapping(
            target = "user.role",
            ignore = true
    )
    AppointmentDTO appointmentToDTO(Appointment appointment);
}