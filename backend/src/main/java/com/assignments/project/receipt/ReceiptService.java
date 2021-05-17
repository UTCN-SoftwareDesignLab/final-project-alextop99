package com.assignments.project.receipt;

import com.assignments.project.receipt.dto.ReceiptDTO;

import java.time.format.DateTimeFormatter;

public interface ReceiptService {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
    String export(ReceiptDTO receiptDTO);
    ReceiptType getType();
}