package com.assignments.project.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ReceiveMessage {
    private String carMatNb;
    private String hour;
    private String mechanicName;
}
