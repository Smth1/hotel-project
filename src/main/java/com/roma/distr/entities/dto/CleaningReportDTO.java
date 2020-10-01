package com.roma.distr.entities.dto;

import com.roma.distr.entities.CleaningReport;
import lombok.Data;

import java.util.List;

@Data
public final class CleaningReportDTO {
    private final List<CleaningReport> reports;
}
