package com.assignments.project.controllers;

import com.assignments.project.car.CarService;
import com.assignments.project.car.dto.CarDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.assignments.project.UrlMapping.*;
import static com.assignments.project.UrlMapping.FIND_BY_ID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(CARS)
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping(FIND_ALL)
    @Secured({"ADMIN", "SECRETARY"})
    public List<CarDTO> allItems() {
        return carService.findAll();
    }

    @DeleteMapping(DELETE)
    @Secured({"ADMIN", "SECRETARY"})
    public void delete(@PathVariable Long id) {
        carService.deleteByID(id);
    }

    @PostMapping(SAVE)
    @Secured({"ADMIN", "SECRETARY"})
    public void save(@Valid @RequestBody CarDTO carDTO) {
        carService.save(carDTO);
    }

    @PatchMapping(UPDATE)
    @Secured({"ADMIN", "SECRETARY"})
    public void update(@Valid @RequestBody CarDTO carDTO) {
        carService.update(carDTO);
    }

    @GetMapping(FIND_BY_ID)
    @Secured({"ADMIN", "SECRETARY"})
    public CarDTO findById(@PathVariable Long id) {
        return carService.findById(id);
    }

}
