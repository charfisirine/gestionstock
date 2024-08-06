package com.example.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
//import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object for Stock.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDTO {
    
    private Integer idStock;
    
    @NotNull
    private Integer idArticle;
    
    @NotNull
    private Integer idDepot;
    
    @NotNull
    private Integer quantity;
    
    private LocalDate datePeremption;

    // Getters and Setters

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Integer getIdDepot() {
        return idDepot;
    }

    public void setIdDepot(Integer idDepot) {
        this.idDepot = idDepot;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(LocalDate datePeremption) {
        this.datePeremption = datePeremption;
    }
}
