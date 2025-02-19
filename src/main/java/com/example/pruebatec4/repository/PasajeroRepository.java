package com.example.pruebatec4.repository;

import com.example.pruebatec4.model.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
}