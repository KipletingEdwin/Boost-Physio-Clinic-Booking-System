package com.boostphysio.controller;

import com.boostphysio.model.Patient;
import com.boostphysio.controller.ClinicManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void removePatient() {
    }

    @Test
    void bookAppointment() {
    }

    @Test
    void cancelAppointment() {
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