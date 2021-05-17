package com.assignments.project.client.mapper;

import com.assignments.project.client.dto.ClientDTO;
import com.assignments.project.client.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client clientDTOToClient(ClientDTO clientDTO);
    ClientDTO clientToClientDTO(Client client);
}
