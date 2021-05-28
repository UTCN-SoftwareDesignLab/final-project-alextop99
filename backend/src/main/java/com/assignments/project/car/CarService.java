package com.assignments.project.car;

import com.assignments.project.appointment.AppointmentService;
import com.assignments.project.car.dto.CarDTO;
import com.assignments.project.car.mapper.CarMapper;
import com.assignments.project.car.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final AppointmentService appointmentService;
    private final CarMapper carMapper;

    public List<CarDTO> findAll() {
        return carRepository.findAll().stream()
                .map(carMapper ::carToDTO)
                .collect(Collectors.toList());
    }

    public void update(CarDTO carDTO) {
        save(carDTO);
    }

    public void save(CarDTO carDTO) {
        carRepository.save(carMapper.carFromDTO(carDTO));
    }

    public void deleteByID(long id) {
        carRepository.deleteById(id);
    }

    public void deleteAllByClientId(long clientId) {
        List<Car> cars = carRepository.findAllByClientId(clientId);

        for(Car car: cars) {
            appointmentService.deleteAllByCarId(car.getId());
            carRepository.deleteById(car.getId());
        }
    }

    public CarDTO findById(Long id) {
        return carRepository.findById(id).map(carMapper ::carToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Car not found: " + id));
    }
}
