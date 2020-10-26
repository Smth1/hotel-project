package com.roma.distr.entities.dto;

import com.roma.distr.entities.HotelClientContract;
import lombok.Data;

import java.util.List;

@Data
public final class ContractsDTO {
    private List<HotelClientContract> contracts;
}
