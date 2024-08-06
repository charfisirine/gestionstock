package com.example.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.gestionstock.domain.Stock;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {


    Stock findByArticle_IdArticleAndDepot_IdDepotAndDatePeremption(Integer idArticle, Integer idDepot, LocalDate datePeremption);

    Collection<Stock> findByArticleIdArticleAndDepotIdDepot(int idArticle, int idDepot);

    List<Stock> findByArticle_DesignationArticleAndDepot_NomDepotOrderByDatePeremptionAsc(String designationArticle, String nomDepot);

}
