package com.example.u5w3d1.dao;

import com.example.u5w3d1.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteDAO extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByEmail(String email);
}
