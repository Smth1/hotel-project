package com.roma.distr.services.rest.impl;

import com.roma.distr.entities.HotelClient;

import com.roma.distr.repository.HotelClientRepository;
import com.roma.distr.services.rest.ClientService;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private HotelClientRepository hotelClientRepository;
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<Object> headersEntity = new HttpEntity<>(headers);


    private void addClient(HotelClient client) {
        System.out.println(client);
        hotelClientRepository.save(client);
    }

    private void removeClient(HotelClient client) {
        hotelClientRepository.delete(client);
    }

    @Override
    public List<HotelClient> getClients() {
        System.out.println(hotelClientRepository.findAll());
        return hotelClientRepository.findAll();

    }

    @Override
    public HotelClient getClientById(String id) {
        List<HotelClient> clients = hotelClientRepository.findAll();

        for (HotelClient client : clients) {
            if (client.getClientId().toString().equals(id)) {
                return client;
            }
        }

        return null;
    }

    @Override
    public void serveClient(HotelClient client) {
        List<UUID> rooms = this.getAvailableRooms();
        Random random = new Random();
        System.out.println(rooms.size());
        int index = Math.abs(random.nextInt(rooms.size()));

        System.out.println(index);
        UUID room = rooms.get(index);
        client.setRoom(room);

        this.addClient(client);

        this.concludeContract(client,room);
    }

    @Override
    public void moveOutClient(String clientId) {
        HotelClient client = this.getClientById(clientId);

        if (client != null) {
            UUID contract = this.getContractOfClient(client.getClientId());
            this.removeContract(contract);
            this.removeClient(client);
        }
        else {
            System.out.println("Client id " + clientId + " not found.");
        }
    }

    private void concludeContract(HotelClient client, UUID roomId) {
        //String URL = "http://contract-service:8083";
        String URL = "http://localhost:8083";

        ResponseEntity<String> response = restTemplate
                .exchange(URL + "/contract?" + "clientId=" + client.getClientId() + "&roomId=" + roomId,
                        HttpMethod.POST, headersEntity, String.class);
    }

    private List<UUID> getAvailableRooms() {
        //String URL = "http://room-service:8085";
        String URL = "http://localhost:8085";

        List<UUID> rooms = new ArrayList<>();
        ResponseEntity<String> response3 = restTemplate
                .exchange(URL + "/room-service/rooms", HttpMethod.GET, headersEntity, String.class);
        JSONObject jsonObject = new JSONObject(response3.getBody());

        System.out.println(response3.getBody());

        JSONArray jsonArray = jsonObject.getJSONArray("rooms");

        for (int i = 0; i < jsonArray.length(); i++) {
            String room_id = jsonArray.getJSONObject(i).getString("id");
            rooms.add(UUID.fromString(room_id));
        }

        return rooms;
    }

    private UUID getContractOfClient(UUID clientId) {
        //String URL = "http://contract-service:8083";
        String URL = "http://localhost:8083";

        ResponseEntity<String> response = restTemplate
                .exchange(URL + "/contract?clientId=" + clientId, HttpMethod.GET, headersEntity, String.class);
        JSONObject contract = new JSONObject(Objects.requireNonNull(response.getBody()));

        System.out.println(contract.getString("id"));

        UUID contractId = UUID.fromString(contract.getString("id"));

        return contractId;
    }

    private void removeContract(UUID contractId) {
        //String URL = "http://contract-service:8083";
        String URL = "http://localhost:8083";

        ResponseEntity<String> response = restTemplate
                .exchange(URL + "/contracts/" + contractId ,
                        HttpMethod.DELETE, headersEntity, String.class);
    }


    @Override
    public String toString() {
        return "ClientService{" +
                "clients=" + this.getClients() +
                '}';
    }
}
