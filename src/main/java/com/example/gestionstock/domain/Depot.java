package com.example.gestionstock.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
 
@Entity
public class Depot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepot;

    private String nomDepot;

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
}


