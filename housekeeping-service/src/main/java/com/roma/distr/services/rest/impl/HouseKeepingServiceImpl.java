package com.roma.distr.services.rest.impl;

import com.roma.distr.entities.CleaningReport;

import com.roma.distr.repository.CleaningReportRepository;
import com.roma.distr.services.rest.HouseKeepingService;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class HouseKeepingServiceImpl implements HouseKeepingService {
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Autowired
    private CleaningReportRepository cleaningReportRepository;

    @Override
    public void cleanRooms() {
        List<UUID> uncleanRooms = this.getUncleanRooms();
        if (uncleanRooms.size() == 0) {
            System.out.println("All rooms are clean");
            return;
        }

        this.makeCleaningReport(uncleanRooms);
        for (UUID room : uncleanRooms) {

            System.out.println(room);
            this.setClean(room);

            System.out.println("Cleaning room: " + room);
        }
    }

    @Override
    public void makeCleaningReport(List<UUID> rooms) {
        //String URL = "http://admin-service:8081";
        String URL = "http://localhost:8081";

        ResponseEntity<String> response3 = restTemplate
                .exchange(URL + "/administration/admin", HttpMethod.GET, headersEntity, String.class);
        JSONObject admin = new JSONObject(Objects.requireNonNull(response3.getBody()));

        response3 = restTemplate
                .exchange(URL + "/administration/maid", HttpMethod.GET, headersEntity, String.class);

        JSONObject maid = new JSONObject(Objects.requireNonNull(response3.getBody()));

        System.out.println(admin.get("id"));

        UUID adminId = UUID.fromString(admin.getString("id"));
        UUID maidId = UUID.fromString(maid.getString("id"));
        StringBuilder stringBuilder = new StringBuilder();
        for (UUID roomId : rooms) {
            stringBuilder.append(roomId);
            stringBuilder.append(",");
        }
        CleaningReport report =
                new CleaningReport(adminId, maidId, stringBuilder.toString());
        cleaningReportRepository.save(report);
    }

    private List<UUID> getUncleanRooms() {
        //String URL = "http://room-service:8085";
        String URL = "http://localhost:8085";

        List<UUID> rooms = new ArrayList<>();
        ResponseEntity<String> response3 = restTemplate
                .exchange(URL + "/room-service/unclean-rooms", HttpMethod.GET, headersEntity, String.class);
        JSONArray jsonArray = new JSONArray(Objects.requireNonNull(response3.getBody()));

        for (int i = 0; i < jsonArray.length(); i++) {
            String room_id = jsonArray.getJSONObject(i).getString("id");
            rooms.add(UUID.fromString(room_id));
        }

        return rooms;
    }

    private void setClean(UUID roomId) {
        //String URL = "http://room-service:8085";
        String URL = "http://localhost:8085";

        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/room-service/make-clean/" + roomId.toString(), HttpMethod.PUT, null, Void.class);
    }

    @Override
    public List<CleaningReport> getCleaningReports() {
        return cleaningReportRepository.findAll();
    }

    @Override
    public String toString() {
        return "HousekeepingService{" +
                cleaningReportRepository.findAll() +
                '}';
    }
}
