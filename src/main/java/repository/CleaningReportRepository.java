package repository;

import entities.CleaningReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CleaningReportRepository extends JpaRepository<CleaningReport, UUID> {
}
