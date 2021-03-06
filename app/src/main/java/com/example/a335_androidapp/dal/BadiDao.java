package com.example.a335_androidapp.dal;

import java.util.ArrayList;
import java.util.List;
import com.example.a335_androidapp.model.Badi;

public class BadiDao {

    public static List<Badi> getAll() {
        List<Badi> availableBadis = new ArrayList<>();
        availableBadis.add(new Badi(71, "Schwimmbad", "BE", "Aarberg"));
        availableBadis.add(new Badi(27, "Schwimmbad Gruebi","BE","Adelboden"));
        availableBadis.add(new Badi(6, "Stadtberner Baeder","BE", "Bern"));
        availableBadis.add(new Badi(55, "Zürichsee","ZH", "Zürich"));

        return availableBadis;
    }
}