package com.roma.distr.repository;

import com.roma.distr.entities.HotelClientContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelClientContractRepository extends JpaRepository<HotelClientContract, UUID> {
}
