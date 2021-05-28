package com.assignments.project.car.mapper;

import com.assignments.project.car.dto.CarDTO;
import com.assignments.project.car.model.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car carFromDTO(CarDTO carDTO);
    CarDTO carToDTO(Car car);
}