package com.assignments.project.user.mapper;

import com.assignments.project.user.dto.UserDTO;
import com.assignments.project.user.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(
            target = "role",
            source = "role.name"
    )
    UserDTO userToUserDTO(User user);

    @InheritInverseConfiguration
    User userDTOToUser(UserDTO userDTO);
}
