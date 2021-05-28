package com.assignments.project.user;

import com.assignments.project.user.dto.UserDTO;
import com.assignments.project.user.mapper.UserMapper;
import com.assignments.project.user.model.ERole;
import com.assignments.project.user.model.Role;
import com.assignments.project.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper ::userToDTO)
                .collect(Collectors.toList());
    }

    public void update(UserDTO userDTO) {
        if(!userDTO.getPassword().equals("")) {
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        }
        else {
            userDTO.setPassword(null);
        }

        save(userDTO);
    }

    public void save(UserDTO userDTO) {
        User user = userMapper.userFromDTO(userDTO);

        Role role;

        if (userDTO.getRole() == null) {
            role = roleRepository.findByName(ERole.SECRETARY)
                    .orElseThrow(() -> new RuntimeException("Cannot find SECRETARY role"));
        } else {
            role = roleRepository.findByName(ERole.valueOf(userDTO.getRole()))
                    .orElseThrow(() -> new RuntimeException("Cannot find role: " + userDTO.getRole()));
        }

        user.setRole(role);
        userRepository.save(user);
    }

    public void deleteByID(long id) {
        userRepository.deleteById(id);
    }

    public UserDTO findById(Long id) {
        return userRepository.findById(id).map(userMapper ::userToDTO)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public List<UserDTO> findByRole(ERole eRole) {
        Role role;
        if (eRole == null) {
            role = roleRepository.findByName(ERole.SECRETARY)
                    .orElseThrow(() -> new RuntimeException("Cannot find SECRETARY role"));
        } else {
            role = roleRepository.findByName(eRole)
                    .orElseThrow(() -> new RuntimeException("Cannot find role: " + eRole));
        }

        return userRepository.findByRole(role).stream().map(userMapper ::userToDTO).collect(Collectors.toList());
    }
}
