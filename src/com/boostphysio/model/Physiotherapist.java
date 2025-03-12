package com.boostphysio.model;

import java.util.*;

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

    //Generate four week schedule with available slots
    private  Map<String,List<String>> generate4WeekSchedule(){
        Map<String,List<String>> schedule = new LinkedHashMap<>();
        Calendar calendar = Calendar.getInstance();

        for(int i=0;i<4;i++){
            calendar.add(Calendar.DATE,7);
            String date = String.format("%tY-%tm-%td", calendar, calendar, calendar);
            schedule.put(date,Arrays.asList("09:00-10:00","10:30-11:30","14:00-15:00","16:30-17:30"));
        }
        return schedule;
    }

    //Remove a booked time slot
    public  void  removeBookedSlot(String date, String timeSlot){
        if(schedule.containsKey(date)){
            schedule.get(date).remove(timeSlot);
        }
    }



    public  void  addTreatment(Treatment treatment){
        treatments.add(treatment);
    }
}
