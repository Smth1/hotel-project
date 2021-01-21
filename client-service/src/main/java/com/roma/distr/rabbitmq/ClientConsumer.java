package com.roma.distr.rabbitmq;

import com.roma.distr.entities.HotelClient;
import com.roma.distr.entities.dto.HotelClientDTO;
import com.roma.distr.services.rest.ClientService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientConsumer {
    private final ClientService clientService;

    private final static String QUEUE_NAME = "clientQueue";

    @Autowired
    public ClientConsumer(ClientService clientService) {
        this.clientService = clientService;
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void consume(HotelClientDTO hotelClientDTO) {
        HotelClient client = new HotelClient(hotelClientDTO.getClientName());
        System.out.println("CONSUMER TRIGGERED");
        clientService.serveClient(client);
    }
}
