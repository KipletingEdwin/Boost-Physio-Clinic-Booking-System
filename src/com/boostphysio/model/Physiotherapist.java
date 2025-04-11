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
        List<List<String>> weeklyPatterns = Arrays.asList(
                Arrays.asList("09:00-10:00", "14:00-15:00"), // Week 1
                Arrays.asList("10:00-11:00", "15:00-16:00", "17:00-18:00"), // Week 2
                Arrays.asList("08:30-09:30", "13:00-14:00"), // Week 3
                Arrays.asList("09:00-10:00", "10:30-11:30", "16:30-17:30") // Week 4
        );

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // start from the current week’s Monday

        for (int i = 0; i < 4; i++) {
            Calendar weekStart = (Calendar) calendar.clone();
            weekStart.add(Calendar.DATE, i * 7); // Add 0, 7, 14, 21 days

            String date = String.format("%tY-%tm-%td", weekStart, weekStart, weekStart);
            schedule.put(date, new ArrayList<>(weeklyPatterns.get(i)));
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
