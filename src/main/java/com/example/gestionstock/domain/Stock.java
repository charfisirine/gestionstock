package com.example.gestionstock.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idArticle")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDepot")
    private Depot depot;

    private Integer quantity;
    private LocalDate datePeremption;


    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
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
   

