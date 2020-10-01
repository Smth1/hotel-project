package com.roma.distr.repository;

import com.roma.distr.entities.HotelClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelClientRepository extends JpaRepository<HotelClient, UUID> {
}
