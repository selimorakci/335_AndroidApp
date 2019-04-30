package com.example.a335_androidapp.model;

public class Becken {
    private String name;
    private double temperature;

    public Becken(String name, double temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    public String toString() {
        return ("name: "+name+", temperature: "+temperature+"");
    }

}
