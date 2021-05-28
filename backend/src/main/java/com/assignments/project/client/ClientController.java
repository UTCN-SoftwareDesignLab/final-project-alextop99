package com.assignments.project.client;

import com.assignments.project.car.CarService;
import com.assignments.project.client.ClientService;
import com.assignments.project.client.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.assignments.project.UrlMapping.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(CLIENTS)
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final CarService carService;

    @GetMapping(FIND_ALL)
    @Secured({"ADMIN", "SECRETARY"})
    public List<ClientDTO> allItems() {
        return clientService.findAll();
    }

    @DeleteMapping(DELETE)
    @Secured({"ADMIN", "SECRETARY"})
    public void delete(@PathVariable Long id) {
        carService.deleteAllByClientId(id);
        clientService.deleteByID(id);
    }

    @PostMapping(SAVE)
    @Secured({"ADMIN", "SECRETARY"})
    public void save(@Valid @RequestBody ClientDTO clientDTO) {
        clientService.save(clientDTO);
    }

    @PatchMapping(UPDATE)
    @Secured({"ADMIN", "SECRETARY"})
    public void update(@Valid @RequestBody ClientDTO clientDTO) {
        clientService.update(clientDTO);
    }

    @GetMapping(FIND_BY_ID)
    @Secured({"ADMIN", "SECRETARY"})
    public ClientDTO findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

}
