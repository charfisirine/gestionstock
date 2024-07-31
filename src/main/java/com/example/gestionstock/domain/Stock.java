package com.example.gestionstock.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import com.example.gestionstock.dto.ArticleDTO;
import io.micrometer.common.lang.NonNull;

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

    @NonNull
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
   

