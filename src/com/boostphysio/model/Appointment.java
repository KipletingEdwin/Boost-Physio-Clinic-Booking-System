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

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public void setPhysiotherapist(Physiotherapist physiotherapist) {
        this.physiotherapist = physiotherapist;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getBookingId() {
        return bookingId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public String getStatus() {
        return status;
    }

    public  void  cancel(){
        status = "Cancelled";
    }

    public  void  attend(){
        status = "Attended";
    }

    @Override
    public String toString() {
        return "Appointment ID: " + bookingId + " | " +
                "Patient: " + patient.getName() + " | " +
                "Date: " + treatment.getDate() + " | " +
                "Time: " + treatment.getTime() + " | " +
                "Status: " + status;
    }
}
