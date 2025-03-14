package com.boostphysio.model;

import java.util.*;

public class Physiotherapist {
    private int id;
    private String name;
    private String contact;
    private List<String> expertise;
    private List<Treatment> treatments;
    private Map<String, List<String>> schedule; // ✅ 4-week timetable

    public Map<String, List<String>> getSchedule() {
        return schedule;
    }

    public Physiotherapist(int id, String name, String contact, List<String> expertise) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.expertise = new ArrayList<>(expertise);
        this.treatments = new ArrayList<>();
        this.schedule = generate4WeekSchedule(); // ✅ Generate schedule on creation
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

    // ✅ Generate a 4-week schedule with available slots
    private Map<String, List<String>> generate4WeekSchedule() {
        Map<String, List<String>> schedule = new LinkedHashMap<>();
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 4; i++) { // Generate for 4 weeks
            calendar.add(Calendar.DATE, 7 * i); // Move ahead by i weeks
            String date = String.format("%tY-%tm-%td", calendar, calendar, calendar);
            schedule.put(date, new ArrayList<>(Arrays.asList("09:00-10:00", "10:30-11:30", "14:00-15:00", "16:30-17:30")));
        }
        return schedule;
    }

    // ✅ Book a slot and remove it from the available schedule
    public void removeBookedSlot(String date, String timeSlot) {
        if (schedule.containsKey(date) && schedule.get(date).contains(timeSlot)) {
            schedule.get(date).remove(timeSlot);
            System.out.println("📅 Slot " + timeSlot + " on " + date + " booked for " + name);
        } else {
            System.out.println("⚠️ Slot " + timeSlot + " on " + date + " is not available!");
        }
    }

    // ✅ Display available slots (for debugging)
    public void printAvailableSlots() {
        System.out.println("\n📅 Available Slots for " + name);
        for (Map.Entry<String, List<String>> entry : schedule.entrySet()) {
            System.out.println("📆 " + entry.getKey() + " → " + entry.getValue());
        }
    }

    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }
}
