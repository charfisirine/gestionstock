package com.example.gestionstock.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Collection;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class StockDTO {
    private Integer idStock;
    private Integer idArticle; 
    private Integer idDepot;   
    private Integer quantity;
    private LocalDate datePeremption;
    Collection<ArticleDTO> articles;


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

    public Collection<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(Collection<ArticleDTO> articles) {
        this.articles = articles;
    }
}

