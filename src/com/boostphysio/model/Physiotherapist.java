package com.boostphysio.model;

import java.util.*;

public class Physiotherapist {
    private int id;
    private String name;
    private String address;
    private String contact;
    private List<String> expertise;
    private Map<String, List<String>> schedule;

    public Physiotherapist(int id, String name, String address, String contact, List<String> expertise) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.expertise = new ArrayList<>(expertise);
        this.schedule = generate4WeekSchedule();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public List<String> getExpertise() {
        return expertise;
    }

    public Map<String, List<String>> getSchedule() {
        return schedule;
    }

    private Map<String, List<String>> generate4WeekSchedule() {
        Map<String, List<String>> schedule = new LinkedHashMap<>();
        List<List<String>> weeklyPatterns = Arrays.asList(
                Arrays.asList("09:00-10:00", "14:00-15:00"),
                Arrays.asList("10:00-11:00", "15:00-16:00", "17:00-18:00"),
                Arrays.asList("08:30-09:30", "13:00-14:00"),
                Arrays.asList("09:00-10:00", "10:30-11:30", "16:30-17:30")
        );

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        for (int i = 0; i < 4; i++) {
            Calendar weekStart = (Calendar) calendar.clone();
            weekStart.add(Calendar.DATE, i * 7);
            String date = String.format("%tY-%tm-%td", weekStart, weekStart, weekStart);
            schedule.put(date, new ArrayList<>(weeklyPatterns.get(i)));
        }

        return schedule;
    }

    public void removeBookedSlot(String date, String timeSlot) {
        if (schedule.containsKey(date) && schedule.get(date).contains(timeSlot)) {
            schedule.get(date).remove(timeSlot);
            System.out.println(" Slot " + timeSlot + " on " + date + " booked for " + name);
        } else {
            System.out.println(" Slot " + timeSlot + " on " + date + " is not available!");
        }
    }

    public void printAvailableSlots() {
        System.out.println("\n Available Slots for " + name);
        for (Map.Entry<String, List<String>> entry : schedule.entrySet()) {
            System.out.println(" " + entry.getKey() + " → " + entry.getValue());
        }
    }

    @Override
    public String toString() {
        return name + " | Address: " + address + " | Contact: " + contact + " | Expertise: " + expertise;
    }
}
