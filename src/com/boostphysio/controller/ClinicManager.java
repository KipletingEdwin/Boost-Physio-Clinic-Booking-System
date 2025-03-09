package com.boostphysio.controller;

import com.boostphysio.model.Appointment;
import com.boostphysio.model.Patient;
import com.boostphysio.model.Physiotherapist;

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


    }
