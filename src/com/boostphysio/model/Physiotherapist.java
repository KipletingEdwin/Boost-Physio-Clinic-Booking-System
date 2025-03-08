package com.boostphysio.model;

import java.util.ArrayList;
import java.util.List;

public class Physiotherapist {

    private  int id;
    private  String name;
    private  String contact;
    private List<String> expertise;
    private List<Treatment> treatments;

    public Physiotherapist(int id, String name, String contact, List<String> expertise){
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.expertise = new ArrayList<>(expertise);
        this.treatments = new ArrayList<>();
    }

    public  void  addTreatment(Treatment treatment){
        treatments.add(treatment);
    }
}
