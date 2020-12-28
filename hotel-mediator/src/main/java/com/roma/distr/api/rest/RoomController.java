package com.roma.distr.api.rest;

import com.roma.distr.dto.RoomDTO;
import com.roma.distr.dto.RoomsDTO;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RoomController {
    //private static final String URL = "http://room-service:8085";
    private static final String URL = "http://localhost:8085";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    private final String exchange="exchange";
    private final String routingKey="mediator.to.room";
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RoomController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/rooms")
    public ResponseEntity<RoomsDTO> getAvailableRooms() {
        ResponseEntity<RoomsDTO> responseEntity = restTemplate
                .exchange(URL + "/room-service/rooms", HttpMethod.GET, headersEntity, RoomsDTO.class);

        return responseEntity;
    }

    @PostMapping("/room")
    public ResponseEntity<Void> addRoom(@RequestBody RoomDTO roomDTO) {
        HttpEntity<RoomDTO> deliverRoom = new HttpEntity<>(roomDTO, headers);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/room-service/room", HttpMethod.POST, deliverRoom, Void.class);

        return responseEntity;
    }

    @PostMapping("/rabbitmq/room")
    public ResponseEntity<Void> addRoomRabbitmq(@RequestBody RoomDTO roomDTO) {
        rabbitTemplate.convertAndSend(exchange, routingKey, roomDTO);

        return ResponseEntity.ok().build();
    }
}
