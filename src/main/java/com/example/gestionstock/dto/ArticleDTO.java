package com.example.gestionstock.dto;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO {
    private Integer idArticle;
    private String designationArticle;

    // List <StockDTO> stocks;

    Collection<StockDTO> stocks;

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public String getDesignationArticle() {
        return designationArticle;
    }

    public void setDesignationArticle(String designationArticle) {
        this.designationArticle = designationArticle;

    }
    public Collection<StockDTO> getStocks() {
        return stocks;
    }

    public void setStocks(Collection<StockDTO> stocks) {
        this.stocks = stocks;
    }
}

