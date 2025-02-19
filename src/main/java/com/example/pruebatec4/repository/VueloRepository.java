package com.example.pruebatec4.repository;

import com.example.pruebatec4.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {
}