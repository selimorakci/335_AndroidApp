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

    public void setId (int id) {
        this.id = id;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setKanton (String kanton) {
        this.kanton = kanton;
    }

    public void setOrt (String ort) {
        this.ort = ort;
    }

    public void addBecken (Becken becken) {
        this.becken.add(becken);
    }

    public String toString() {
        return ("id: "+id+", name: "+name+", becken: "+"var"+", kanton: "+kanton+", ort: "+ort);
    }
}
