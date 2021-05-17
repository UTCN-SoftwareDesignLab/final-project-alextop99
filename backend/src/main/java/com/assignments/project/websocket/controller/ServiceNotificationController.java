package com.assignments.project.websocket.controller;

import com.assignments.project.websocket.model.ReceiveMessage;
import com.assignments.project.websocket.model.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ServiceNotificationController {

    @MessageMapping("/checkin")
    @SendTo("/mechanic/checkin")
    public ResponseMessage checkIn(ReceiveMessage receiveMessage) {
        return new ResponseMessage("The car, " + receiveMessage.getCarMatNb()
                + ", with consultation at "
                + receiveMessage.getHour()
                + " has arrived!"
                ,receiveMessage.getMechanicName());
    }
}