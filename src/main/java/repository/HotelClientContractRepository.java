package repository;

import entities.HotelClientContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelClientContractRepository extends JpaRepository<HotelClientContract, UUID> {
}
