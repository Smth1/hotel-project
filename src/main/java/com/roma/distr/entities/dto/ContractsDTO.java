package com.roma.distr.entities.dto;

import com.roma.distr.entities.HotelClientContract;

import java.util.List;

public class ContractsDTO {
    List<HotelClientContract> contracts;

    public ContractsDTO(List<HotelClientContract> contracts) {
        this.contracts = contracts;
    }

    public List<HotelClientContract> getContracts() {
        return contracts;
    }

    public void setContracts(List<HotelClientContract> contracts) {
        this.contracts = contracts;
    }
}
