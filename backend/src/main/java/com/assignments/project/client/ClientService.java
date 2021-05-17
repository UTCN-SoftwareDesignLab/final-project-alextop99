package com.assignments.project.client;

import com.assignments.project.client.dto.ClientDTO;
import com.assignments.project.client.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper ::clientToClientDTO)
                .collect(Collectors.toList());
    }

    public void update(ClientDTO clientDTO) {
        save(clientDTO);
    }

    public void save(ClientDTO clientDTO) {
        clientRepository.save(clientMapper.clientDTOToClient(clientDTO));
    }

    public void deleteByID(long id) {
        clientRepository.deleteById(id);
    }

    public ClientDTO findById(Long id) {
        Optional<ClientDTO> result = clientRepository.findById(id).map(clientMapper :: clientToClientDTO);
        return result.orElse(null);
    }
}