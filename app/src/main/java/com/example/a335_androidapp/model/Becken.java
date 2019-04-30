package com.example.a335_androidapp.model;

public class Becken {
    private String name;
    private double temperature;

    public Becken(){
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setTemperature (double temperature) {
        this.temperature = temperature;
    }

    public String toString() {
        return ("name: "+name+", temperature: "+temperature+"");
    }

}
