package com.example.pruebatec4.repository;

import com.example.pruebatec4.model.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Long> {
}