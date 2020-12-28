package com.roma.distr.rabbitmq;

import com.roma.distr.entities.Room;
import com.roma.distr.entities.dto.RoomDTO;
import com.roma.distr.services.RoomService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomConsumer {
    private final RoomService roomService;

    private final static String QUEUE_NAME = "roomQueue";

    @Autowired
    public RoomConsumer(RoomService roomService) {
        this.roomService = roomService;
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void consume(RoomDTO roomDTO) {
        Room room = new Room(roomDTO.getNumber(), roomDTO.getClientNumber());
        System.out.println("CONSUMER TRIGGERED");
        roomService.addRoom(room);
    }
}