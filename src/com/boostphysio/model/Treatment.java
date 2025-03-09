package com.boostphysio.model;

public class Treatment {
    private  String name;
    private  String date;
    private  String time;
    private  Physiotherapist physiotherapist;

    public Treatment(String name, String date, String time, Physiotherapist physiotherapist){
        this.name = name;
        this.date = date;
        this.time = time;
        this.physiotherapist = physiotherapist;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }
}
