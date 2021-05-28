package com.assignments.project.appointment;

import com.assignments.project.appointment.AppointmentService;
import com.assignments.project.appointment.dto.AppointmentDTO;
import com.assignments.project.email.EmailService;
import com.assignments.project.receipt.ReceiptServiceFactory;
import com.assignments.project.receipt.ReceiptType;
import com.assignments.project.receipt.dto.ReceiptDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.assignments.project.UrlMapping.*;
import static com.assignments.project.UrlMapping.FIND_BY_ID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(APPOINTMENTS)
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final ReceiptServiceFactory receiptServiceFactory;
    private final EmailService emailService;

    @GetMapping(FIND_ALL)
    @Secured({"SECRETARY", "MECHANIC"})
    public List<AppointmentDTO> allConsultations() {
        return appointmentService.findAll();
    }

    @GetMapping(FIND_ALL_BY_DATE_AND_MECHANIC)
    @Secured({"SECRETARY", "MECHANIC"})
    public List<AppointmentDTO> allAppointmentsByDateAndMechanic(@RequestParam String date, @RequestParam Long id) {
        return appointmentService.findAllByDateAndMechanicId(LocalDate.parse(date), id);
    }

    @GetMapping(FIND_ALL_BY_CAR)
    @Secured({"SECRETARY", "MECHANIC"})
    public List<AppointmentDTO> allAppointmentsByPatientAndMechanic(@RequestParam Long car, @RequestParam String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return appointmentService.findAllByCarId(car, LocalDateTime.parse(date, formatter));
    }

    @DeleteMapping(DELETE)
    @Secured({"SECRETARY", "MECHANIC"})
    public void delete(@PathVariable Long id) {
        appointmentService.deleteByID(id);
    }

    @PostMapping(SAVE)
    @Secured({"SECRETARY", "MECHANIC"})
    public void save(@Valid @RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.save(appointmentDTO);
    }

    @PatchMapping(UPDATE)
    @Secured({"SECRETARY", "MECHANIC"})
    public void update(@Valid @RequestBody AppointmentDTO appointmentDTO) {
        if(appointmentDTO.getFinished()) {
            emailService.emailCarFinished(appointmentDTO.getCar().getClient().getEmail());
        }
        appointmentService.update(appointmentDTO);
    }

    @GetMapping(FIND_BY_ID)
    @Secured({"SECRETARY", "MECHANIC"})
    public AppointmentDTO findById(@PathVariable Long id) {
        return appointmentService.findById(id);
    }

    @PostMapping(RECEIPT)
    @Secured({"ADMIN"})
    public @ResponseBody byte[] generateReceipt(@PathVariable ReceiptType receiptType, @Valid @RequestBody ReceiptDTO receiptDTO) {
        String result = receiptServiceFactory.getReceiptService(receiptType).export(receiptDTO);

        if(!result.equals("Failed")) {
            try {
                emailService.emailReceipt(receiptDTO.getCar().getClient().getEmail(), result);
                return Files.readAllBytes(Paths.get(result));
            } catch (IOException | MessagingException e) {
                e.printStackTrace();
            }
        }
        return "Failed".getBytes(StandardCharsets.UTF_8);
    }
}
