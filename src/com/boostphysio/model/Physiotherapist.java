package com.boostphysio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Physiotherapist {

    private  int id;
    private  String name;
    private  String contact;
    private List<String> expertise;
    private List<Treatment> treatments;
    private Map<String, List<String>> schedule;

    public Map<String, List<String>> getSchedule() {
        return schedule;
    }

    public Physiotherapist(int id, String name, String contact, List<String> expertise){
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.expertise = new ArrayList<>(expertise);
        this.treatments = new ArrayList<>();
        this.schedule = generate4WeekSchedule();
    }

    public int getId() {
        return id;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public List<String> getExpertise() {
        return expertise;
    }

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }



    public  void  addTreatment(Treatment treatment){
        treatments.add(treatment);
    }
}
