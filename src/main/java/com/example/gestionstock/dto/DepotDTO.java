package com.example.gestionstock.dto;

import java.util.Collection;

public class DepotDTO {
    private Integer idDepot;
    private String nomDepot;
    Collection<StockDTO> stocks;

    public Integer getIdDepot() {
        return idDepot;
    }

    public void setIdDepot(Integer idDepot) {
        this.idDepot = idDepot;
    }

    public String getNomDepot() {
        return nomDepot;
    }

    public void setNomDepot(String nomDepot) {
        this.nomDepot = nomDepot;
    }

    public Collection<StockDTO> getStocks() {
        return stocks;
    }

    public void setStocks(Collection<StockDTO> stocks) {
        this.stocks = stocks;
    }
}

