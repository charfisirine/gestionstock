package com.example.gestionstock.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.Collection;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArticle;

    private String designationArticle;
    // @JsonManagedReference
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval=true)
    @JsonIgnore
    private Collection<Stock> stocks;
    

    
 
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
    
    public Collection<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Collection<Stock> stocks) {
            this.stocks = stocks;
    }
}
