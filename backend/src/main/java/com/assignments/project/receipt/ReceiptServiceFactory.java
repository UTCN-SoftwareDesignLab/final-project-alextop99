package com.assignments.project.receipt;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReceiptServiceFactory {
    private final Map<ReceiptType, ReceiptService> receiptServices;

    public ReceiptServiceFactory(List<ReceiptService> receiptServices) {
        this.receiptServices = receiptServices.stream()
                .collect(Collectors.toMap(ReceiptService::getType, Function.identity()));
    }

    public ReceiptService getReceiptService(ReceiptType type) {
        return receiptServices.get(type);
    }
}