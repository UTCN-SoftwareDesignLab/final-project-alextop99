package com.assignments.project.car;

import com.assignments.project.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByClientId(long clientId);
}
