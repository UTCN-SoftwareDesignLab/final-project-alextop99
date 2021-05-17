package com.assignments.project.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseMessage {
    private String message;
    private String mechanicName;
}
