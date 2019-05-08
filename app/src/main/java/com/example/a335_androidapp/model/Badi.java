package com.example.a335_androidapp.model;

import java.util.ArrayList;
import java.util.List;

public class Badi {
    private int id;
    private String name;
    private List<Becken> becken = new ArrayList<Becken>();
    private String kanton;
    private String ort;

    public Badi (){
    }

    public Badi(int id, String name, String kanton, String ort){
        this.id = id;
        this.name = name;
        this.kanton = kanton;
        this.ort = ort;
    }

    public List<Becken> getBecken() { return this.becken; }

    public void addBecken (Becken becken) {
        this.becken.add(becken);
    }

    public int getId () {
        return this.id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getKanton () {
        return this.kanton;
    }

    public void setKanton (String kanton) {
        this.kanton = kanton;
    }

    public String getOrt () {
        return this.ort;
    }

    public void setOrt (String ort) {
        this.ort = ort;
    }

    @Override
    public String toString() {
        return (name + " " + kanton);
    }
}
