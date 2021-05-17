package com.assignments.project.receipt.dto;

import com.assignments.project.car.dto.CarDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ReceiptDTO {
    CarDTO car;
    Float price;
}