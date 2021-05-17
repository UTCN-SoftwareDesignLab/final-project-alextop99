package com.assignments.project;

import com.assignments.project.appointment.AppointmentRepository;
import com.assignments.project.car.CarRepository;
import com.assignments.project.client.ClientRepository;
import com.assignments.project.user.RoleRepository;
import com.assignments.project.user.UserRepository;
import com.assignments.project.user.model.ERole;
import com.assignments.project.user.model.Role;
import com.assignments.project.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final AppointmentRepository appointmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(bootstrap) {
            appointmentRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            carRepository.deleteAll();
            clientRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }

            User user = User.builder()
                    .username("admin")
                    .email("x@x.com")
                    .password(passwordEncoder.encode("admin"))
                    .build();

            Role role = roleRepository.findByName(ERole.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Cannot find ADMIN role"));

            user.setRole(role);
            userRepository.save(user);

            user = User.builder()
                    .username("mechanic")
                    .email("x2@x2.com")
                    .password(passwordEncoder.encode("mechanic"))
                    .build();

            role = roleRepository.findByName(ERole.MECHANIC)
                    .orElseThrow(() -> new RuntimeException("Cannot find MECHANIC role"));

            user.setRole(role);
            userRepository.save(user);

            user = User.builder()
                    .username("secretary")
                    .email("e@e.com")
                    .password(passwordEncoder.encode("secretary"))
                    .build();

            role = roleRepository.findByName(ERole.SECRETARY)
                    .orElseThrow(() -> new RuntimeException("Cannot find SECRETARY role"));

            user.setRole(role);
            userRepository.save(user);
        }
    }
}