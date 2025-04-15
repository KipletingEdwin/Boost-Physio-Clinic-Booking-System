package com.boostphysio.controller;

import com.boostphysio.model.Patient;
import com.boostphysio.controller.ClinicManager;
import com.boostphysio.model.Physiotherapist;
import com.boostphysio.model.Treatment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ClinicManagerTest {
    private ClinicManager clinicManager;

    @BeforeEach
    void setUp() {
        clinicManager = new ClinicManager();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPhysiotherapists() {
    }

    @Test
    void getAppointments() {
    }

    @Test
    void getPatients() {
        Patient patient = new Patient(1, "Jane Doe", "123 Main St", "0123456789");
        clinicManager.addPatient(patient);
        assertEquals(1, clinicManager.getPatients().size());
        assertEquals("Jane Doe", clinicManager.getPatients().get(0).getName());
    }

    @Test
    void addPatient() {
        Patient patient = new Patient(1, "John Smith", "456 Hilltop Rd", "0771234567");
        clinicManager.addPatient(patient);

        assertEquals(1, clinicManager.getPatients().size());
        assertEquals("John Smith", clinicManager.getPatients().get(0).getName());
        assertEquals("456 Hilltop Rd", clinicManager.getPatients().get(0).getAddress());
        assertEquals("0771234567", clinicManager.getPatients().get(0).getContact());
    }

    @Test
    void addPhysiotherapist() {
        Physiotherapist physio = new Physiotherapist(1, "Dr. Olivia White", "789 Align St", "0887654321", Arrays.asList("Rehabilitation", "Massage"));
        clinicManager.addPhysiotherapist(physio);
        assertEquals(1, clinicManager.getPhysiotherapists().size());
        assertEquals("Dr. Olivia White", clinicManager.getPhysiotherapists().get(0).getName());
    }

    @Test
    void removePatient() {
    }

    @Test
    void bookAppointment() {
    }

    @Test
    void cancelAppointment() {
        Patient patient = new Patient(1, "Eve Lin", "123 Test St", "0911223344");
        Physiotherapist physio = new Physiotherapist(1, "Dr. Rick Mason", "456 Wellness Rd", "0998877665", Arrays.asList("Massage"));
        clinicManager.addPatient(patient);
        clinicManager.addPhysiotherapist(physio);
        String date = physio.getSchedule().keySet().iterator().next();
        String time = physio.getSchedule().get(date).get(0);
        Treatment treatment = new Treatment("Massage", date, time, physio);
        clinicManager.bookAppointment(patient, treatment, physio);
        int bookingId = clinicManager.getAppointments().get(0).getBookingId();
        clinicManager.cancelAppointment(bookingId);
        assertEquals("Cancelled", clinicManager.getAppointments().get(0).getStatus());
    }

    @Test
    void attendAppointment() {
    }

    @Test
    void changeAppointment() {
    }

    @Test
    void generateReport() {
    }
}