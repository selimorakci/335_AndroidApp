package com.example.a335_androidapp.model;

import java.util.List;

public class Badi {
    private int id;
    private String name;
    private List<Becken> becken;
    private String kanton;
    private String ort;

    public Badi (int id, String name, List<Becken> becken, String kanton, String ort){
        this.id = id;
        this.name = name;
        this.becken = becken;
        this.kanton = kanton;
        this.ort = ort;
    }


    public String toString() {
        return ("id: "+id+", name: "+name+", becken: "+"var"+", kanton: "+kanton+", ort: "+ort);
    }
}
