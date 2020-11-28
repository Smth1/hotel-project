package com.roma.distr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class CashierDTO {
    private String name;
    private int age;
}
