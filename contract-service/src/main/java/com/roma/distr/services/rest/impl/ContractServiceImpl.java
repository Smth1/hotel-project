package com.roma.distr.services.rest.impl;

import com.roma.distr.entities.HotelClientContract;
import com.roma.distr.repository.HotelClientContractRepository;
import com.roma.distr.services.rest.ContractService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private HotelClientContractRepository hotelClientContractRepository;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Override
    public void removeContract(UUID contractId) {
        HotelClientContract contract =
                this.hotelClientContractRepository.getOne(contractId);
        hotelClientContractRepository.delete(contract);
    }

    private void addContract(HotelClientContract contract) {
        hotelClientContractRepository.save(contract);
    }

    @Override
    public void concludeContract(UUID client, UUID room) {
        //String URL = "http://admin-service:8081";
        String URL = "http://localhost:8081";

        ResponseEntity<String> response3 = restTemplate
                .exchange(URL + "/administration/admin", HttpMethod.GET, headersEntity, String.class);
        JSONObject admin = new JSONObject(Objects.requireNonNull(response3.getBody()));

        response3 = restTemplate
                .exchange(URL + "/administration/cashier", HttpMethod.GET, headersEntity, String.class);
        JSONObject cashier = new JSONObject(Objects.requireNonNull(response3.getBody()));

        response3 = restTemplate
                .exchange(URL + "/administration/porter", HttpMethod.GET, headersEntity, String.class);
        JSONObject porter = new JSONObject(Objects.requireNonNull(response3.getBody()));

        UUID adminId = UUID.fromString(admin.getString("id"));
        UUID cashierId = UUID.fromString(cashier.getString("id"));
        UUID porterId = UUID.fromString(porter.getString("id"));

        HotelClientContract contract =
                new HotelClientContract.ContractBuilder().
                        addAdministrator(adminId).
                        addClient(client).
                        addRoom(room).
                        addCashier(cashierId).
                        addPorter(porterId).
                        createContract();
        this.addContract(contract);
    }

    public void closeContract(UUID client) {
        HotelClientContract contract =
                this.getContractOfClient(client);
        if (contract != null) {
            contract.setClosedContract();
            this.removeContract(contract.getId());
        }
    }

    @Override
    public List<HotelClientContract> getContracts() {
        return this.hotelClientContractRepository.findAll();
    }

    @Override
    public HotelClientContract getContractOfClient(UUID clientId) {
        List<HotelClientContract> contractList = this.hotelClientContractRepository.findAll();

        for (HotelClientContract contract : contractList) {
            if (contract.getClient().equals(clientId)) {
                return contract;
            }
        }

        return null;
    }
}
