package com.example.pruebatec4.repository;

import com.example.pruebatec4.model.ReservaHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaHotelRepository extends JpaRepository<ReservaHotel, Long> {
}