package com.roma.distr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class AdministratorDTO {
    private String id;
    private String name;
    private int age;
    private String telephoneNumber;
}
