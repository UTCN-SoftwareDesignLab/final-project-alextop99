package com.assignments.project.appointment;

import com.assignments.project.appointment.dto.AppointmentDTO;
import com.assignments.project.appointment.mapper.AppointmentMapper;
import com.assignments.project.appointment.model.Appointment;
import com.assignments.project.user.UserRepository;
import com.assignments.project.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final AppointmentMapper appointmentMapper;

    public List<AppointmentDTO> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper ::appointmentToAppointmentDTO)
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> findAllByDateAndMechanicId(LocalDate date, Long mechanicId) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atStartOfDay().plusHours(24);

        return appointmentRepository.findAllByDateTimeBetweenAndUserIdOrderByDateTimeAsc(startOfDay, endOfDay, mechanicId).stream()
                .map(appointmentMapper ::appointmentToAppointmentDTO)
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> findAllByCarId(Long carId, LocalDateTime date) {
        return appointmentRepository.findAllByCarIdAndDateTimeNotOrderByDateTimeDesc(carId, date).stream()
                .map(appointmentMapper ::appointmentToAppointmentDTO)
                .collect(Collectors.toList());
    }

    public void deleteAllByCarId(long carId) {
        List<Appointment> appointments = appointmentRepository.findAllByCarId(carId);

        for(Appointment appointment: appointments) {
            appointmentRepository.deleteById(appointment.getId());
        }
    }

    public void update(AppointmentDTO appointmentDTO) {
        save(appointmentDTO);
    }

    public void save(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.appointmentDTOToAppointment(appointmentDTO);
        User user = userRepository.findById(appointment.getUser().getId()).get();

        appointment.setUser(user);

        appointmentRepository.save(appointment);
    }

    public void deleteByID(long id) {
        appointmentRepository.deleteById(id);
    }

    public AppointmentDTO findById(Long id) {
        Optional<AppointmentDTO> result = appointmentRepository.findById(id).map(appointmentMapper :: appointmentToAppointmentDTO);
        return result.orElse(null);
    }
}
