package com.assignments.project.car.dto;

import com.assignments.project.client.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String make;
    private String model;
    private String matNb;
    private String vin;
    private String year;
    private ClientDTO client;
}
