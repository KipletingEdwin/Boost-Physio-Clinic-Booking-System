package com.boostphysio.model;

public class Appointment {
    private  int bookingId;
    private  Patient patient;
    private  Treatment treatment;
    private  Physiotherapist physiotherapist;
    private  String status;

    public  Appointment(int bookingId, Patient patient,Treatment treatment, Physiotherapist physiotherapist){
        this.bookingId = bookingId;
        this.patient = patient;
        this.treatment = treatment;
        this.physiotherapist = physiotherapist;
        this.status = "Booked";
    }
}
