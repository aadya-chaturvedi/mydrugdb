package com.aadya.mydrugdb.dto;

public class MedicationSearchResult {

    private String id;
    private String name;
    private String manufacturer;
    private String usage;
    private String sideEffects;

    public MedicationSearchResult() {
    }

    public MedicationSearchResult(String id, String name, String manufacturer, String usage, String sideEffects) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.usage = usage;
        this.sideEffects = sideEffects;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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