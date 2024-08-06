package com.example.gestionstock.repository;

import com.example.gestionstock.domain.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Integer> {
    Optional<Depot> findByNomDepot(String nomDepot);

}
