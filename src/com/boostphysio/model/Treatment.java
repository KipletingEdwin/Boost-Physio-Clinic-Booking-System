package com.boostphysio.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Treatment {
    private String name;
    private String date;
    private String time;
    private Physiotherapist physiotherapist;

    public Treatment(String name, String date, String time, Physiotherapist physiotherapist) {
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

    public String getFormattedDateWithDay() {
        try {
            LocalDate localDate = LocalDate.parse(date);
            String day = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d MMMM yyyy");
            return day + " " + localDate.format(fmt);
        } catch (Exception e) {
            return date;
        }
    }

    @Override
    public String toString() {
        return name + " on " + getFormattedDateWithDay() + " at " + time + " with " + physiotherapist.getName();
    }
}
