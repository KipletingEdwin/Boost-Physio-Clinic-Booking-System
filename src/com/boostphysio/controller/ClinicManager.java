package com.boostphysio.controller;

import com.boostphysio.model.Appointment;
import com.boostphysio.model.Patient;
import com.boostphysio.model.Physiotherapist;
import com.boostphysio.model.Treatment;

import java.util.ArrayList;
import java.util.List;

public class ClinicManager {
    private List<Physiotherapist> physiotherapists;
    private List<Patient> patients;
    private List<Appointment> appointments;
    private int bookingIdCounter;

    public ClinicManager() {
        this.physiotherapists = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.bookingIdCounter = 1;
    }

    public List<Physiotherapist> getPhysiotherapists() {
        return physiotherapists;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addPhysiotherapist(Physiotherapist physio) {
        physiotherapists.add(physio);
    }

    public void removePatient(int patientId) {
        patients.removeIf(patient -> patient.getId() == patientId);
        System.out.println("Patient removed: ID " + patientId);
    }

    public boolean bookAppointment(Patient patient, Treatment treatment, Physiotherapist physiotherapist) {
        for (Appointment existing : appointments) {
            if (existing.getPatient().getId() == patient.getId() &&
                    existing.getTreatment().getDate().equals(treatment.getDate()) &&
                    existing.getTreatment().getTime().equals(treatment.getTime())) {
                System.out.println("❌ Booking failed! The patient already has an appointment at this time.");
                return false;
            }

            if (existing.getPhysiotherapist().getId() == physiotherapist.getId() &&
                    existing.getTreatment().getDate().equals(treatment.getDate()) &&
                    existing.getTreatment().getTime().equals(treatment.getTime())) {
                System.out.println("❌ Booking failed! This slot is already booked with " + physiotherapist.getName());
                return false;
            }
        }

        Appointment newAppointment = new Appointment(bookingIdCounter++, patient, treatment, physiotherapist);
        appointments.add(newAppointment);
        System.out.println("✅ Appointment booked: " + treatment.getName() + " with " + physiotherapist.getName());
        return true;
    }

    public void cancelAppointment(int bookingId) {
        for (Appointment appointment : appointments) {
            if (appointment.getBookingId() == bookingId) {
                appointment.cancel();
                System.out.println("Appointment cancelled: ID " + bookingId);
                return;
            }
        }
        System.out.println("Appointment not found: ID " + bookingId);
    }

    public void attendAppointment(int bookingId) {
        for (Appointment appointment : appointments) {
            if (appointment.getBookingId() == bookingId) {
                appointment.attend();
                System.out.println("Appointment attended: ID " + bookingId);
                return;
            }
        }
        System.out.println("Appointment not found: ID " + bookingId);
    }

    public boolean changeAppointment(int bookingId, Treatment newTreatment, Physiotherapist newPhysio) {
        for (Appointment appointment : appointments) {
            if (appointment.getBookingId() == bookingId) {
                for (Appointment existing : appointments) {
                    if (existing.getBookingId() != bookingId &&
                            existing.getTreatment().getDate().equals(newTreatment.getDate()) &&
                            existing.getTreatment().getTime().equals(newTreatment.getTime()) &&
                            existing.getPatient().getId() == appointment.getPatient().getId()) {
                        System.out.println("❌ Time conflict for this patient.");
                        return false;
                    }

                    if (existing.getBookingId() != bookingId &&
                            existing.getTreatment().getDate().equals(newTreatment.getDate()) &&
                            existing.getTreatment().getTime().equals(newTreatment.getTime()) &&
                            existing.getPhysiotherapist().getId() == newPhysio.getId()) {
                        System.out.println("❌ This physiotherapist is already booked for that time.");
                        return false;
                    }
                }

                appointment.setTreatment(newTreatment);
                appointment.setPhysiotherapist(newPhysio);
                appointment.setStatus("Booked");

                System.out.println("✅ Appointment updated with the same Booking ID.");
                return true;
            }
        }

        System.out.println("❌ Booking ID not found.");
        return false;
    }

    public void generateReport() {
        System.out.println("\n📊 Clinic Report (Sorted by Most Attended Appointments):");

        physiotherapists.sort((a, b) -> {
            long attendedA = appointments.stream()
                    .filter(appt -> appt.getPhysiotherapist().getId() == a.getId() && "Attended".equals(appt.getStatus()))
                    .count();
            long attendedB = appointments.stream()
                    .filter(appt -> appt.getPhysiotherapist().getId() == b.getId() && "Attended".equals(appt.getStatus()))
                    .count();
            return Long.compare(attendedB, attendedA);
        });

        for (Physiotherapist physio : physiotherapists) {
            System.out.println("👨‍⚕️ " + physio.getName());

            for (Appointment appt : appointments) {
                if (appt.getPhysiotherapist().getId() == physio.getId()) {
                    System.out.println("   🏥 Treatment: " + appt.getTreatment().getName() +
                            " | Patient: " + appt.getPatient().getName() +
                            " | Date: " + appt.getTreatment().getFormattedDateWithDay() +
                            " | Time: " + appt.getTreatment().getTime() +
                            " | Status: " + appt.getStatus());
                }
            }
        }
    }
}
