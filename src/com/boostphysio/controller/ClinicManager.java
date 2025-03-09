package com.boostphysio.controller;

import com.boostphysio.model.Appointment;
import com.boostphysio.model.Patient;
import com.boostphysio.model.Physiotherapist;
import com.boostphysio.model.Treatment;

import java.util.ArrayList;
import java.util.List;

public class ClinicManager {
    private List<Physiotherapist> physiotherapists;
    private  List<Patient> patients;
    private  List<Appointment> appointments;
    private int bookingIdCounter;


    public  ClinicManager (){
        this.physiotherapists = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public  void  addPatient(Patient patient){
        patients.add(patient);
        System.out.println("Patient added: " + patient.getName());
    }

    public  void  removePatient(int patientId){
        patients.removeIf(patient -> patient.getId() == patientId);
        System.out.println("Patient removed: ID " + patientId);
    }

    public  boolean bookAppointment(Patient patient, Treatment treatment,Physiotherapist physiotherapist){
        for (Appointment existing : appointments){
            if(existing.getPatient().getId() == patient.getId() && existing.getTreatment().getDate().equals(treatment.getDate()) &&
            existing.getTreatment().getTime().equals(treatment.getTime())){
                System.out.println("Booking failed! Patient already has an appointment at this time");
                return  false;
            }
        }
        Appointment newAppointment = new Appointment(bookingIdCounter++,patient,treatment,physiotherapist);
        appointments.add(newAppointment);
        System.out.println("Appointment booked: " + treatment.getName() + " with " + physiotherapist.getName());
        return  true;
    }

    public  void  cancelAppointment(int bookingId){
        for (Appointment appointment : appointments){
            if(appointment.getBookingId() == bookingId){
                appointment.cancel();
                System.out.println("Appointment cancelled: ID " + bookingId);
                return;
            }
        }
        System.out.println("Appointment not found: ID " + bookingId);
    }


    }
