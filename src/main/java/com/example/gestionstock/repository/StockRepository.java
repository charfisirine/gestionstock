package com.example.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionstock.domain.Stock;

public interface StockRepository extends JpaRepository<Stock,Integer> {

}



