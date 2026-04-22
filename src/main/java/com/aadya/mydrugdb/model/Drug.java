package com.aadya.mydrugdb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drug")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String manufacturer;
    private String usage;
    private String sideEffects;

    public Drug() {
    }

    public Drug(String name, String manufacturer, String usage, String sideEffects) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.usage = usage;
        this.sideEffects = sideEffects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getUsage() {
        return usage;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }
}