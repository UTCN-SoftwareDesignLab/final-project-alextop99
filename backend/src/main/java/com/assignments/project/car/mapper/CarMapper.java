package com.assignments.project.car.mapper;

import com.assignments.project.car.dto.CarDTO;
import com.assignments.project.car.model.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car carDTOToCar(CarDTO carDTO);
    CarDTO carToCarDTO(Car car);
}