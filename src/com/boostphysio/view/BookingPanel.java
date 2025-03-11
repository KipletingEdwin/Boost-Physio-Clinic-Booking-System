package com.boostphysio.view;

import com.boostphysio.controller.ClinicManager;
import com.boostphysio.model.*;

import javax.swing.*;
import java.awt.*;

public class BookingPanel extends JPanel {
    private ClinicManager clinicManager;
    private DefaultListModel<String> appointmentListModel;
    private JList<String> appointmentList;

    public BookingPanel(ClinicManager clinicManager) {
        this.clinicManager = clinicManager;
        setLayout(new BorderLayout());
        setBackground(new Color(240,240,240));

        // Title label
        JLabel titleLabel = new JLabel("📅 Book an Appointment");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // 📅 List of Available appointments
        appointmentListModel = new DefaultListModel<>();
        appointmentList = new JList<>(appointmentListModel);
        JScrollPane scrollPane = new JScrollPane(appointmentList);

        // 📋 Dropdown for Selecting Expertise
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(255,255,255));
        topPanel.add(new JLabel("Select Expertise:"));
        JComboBox<String> expertiseDropdown = new JComboBox<>(new String[]{"Rehabilitation", "Massage", "Osteopathy", "Acupuncture"});
        topPanel.add(expertiseDropdown);

        // 🔎 Search Field for Physiotherapist
        topPanel.add(new JLabel("Search Physiotherapist:"));
        JTextField searchField = new JTextField(15);
        searchField.setFont(new Font("SansSerif", Font.PLAIN,14));
        topPanel.add(searchField);

        // 🎟️ Buttons for Booking, Canceling, and Attending Appointments
        JPanel buttonPanel = new JPanel();
        JButton bookButton = new JButton("✅ Book Appointment");
        JButton cancelButton = new JButton("❌ Cancel Appointment");
        JButton attendButton = new JButton("🎯 Attend Appointment");

        // 🔵 Style Buttons
        bookButton.setBackground(new Color(46,204,113));  // Green
        cancelButton.setBackground(new Color(231,76,60));  // Red
        attendButton.setBackground(new Color(241,196,15)); // Yellow

        bookButton.setForeground(Color.WHITE);
        cancelButton.setForeground(Color.WHITE);
        attendButton.setForeground(Color.BLACK);

        bookButton.setFont(new Font("SansSerif", Font.BOLD,14));
        cancelButton.setFont(new Font("SansSerif", Font.BOLD,14));
        attendButton.setFont(new Font("SansSerif", Font.BOLD,14));

        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(attendButton);

        // 📌 Add Components to Panel
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // ✅ Load Appointments into List AFTER initialization
        loadAppointments();

        // ✅ Add Action Listener for Booking an Appointment
        bookButton.addActionListener(e -> {
            String selectedExpertise = (String) expertiseDropdown.getSelectedItem();
            String physioName = searchField.getText().trim();

            for (Physiotherapist physio : clinicManager.getPhysiotherapists()) {
                if (physio.getName().equalsIgnoreCase(physioName) || physio.getExpertise().contains(selectedExpertise)) {
                    if (!physio.getTreatments().isEmpty()) {
                        Treatment treatment = physio.getTreatments().get(0);
                        Patient patient = clinicManager.getPatients().get(0); // Select the first patient for now
                        boolean success = clinicManager.bookAppointment(patient, treatment, physio);

                        if (success) {
                            JOptionPane.showMessageDialog(this, "✅ Appointment Booked!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            loadAppointments(); // ✅ Refresh appointment list
                        } else {
                            JOptionPane.showMessageDialog(this, "⚠️ Patient already has an appointment at this time!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "⚠️ No matching physiotherapist found!", "Error", JOptionPane.ERROR_MESSAGE);
        });

        // ✅ Add Action Listener for Canceling an Appointment
        cancelButton.addActionListener(e -> {
            int index = appointmentList.getSelectedIndex();
            if (index >= 0) {
                int bookingId = index + 1;
                clinicManager.cancelAppointment(bookingId);
                JOptionPane.showMessageDialog(this, "❌ Appointment Cancelled!", "Info", JOptionPane.INFORMATION_MESSAGE);
                loadAppointments();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Select an appointment to cancel!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // ✅ Add Action Listener for Attending an Appointment
        attendButton.addActionListener(e -> {
            int index = appointmentList.getSelectedIndex();
            if (index >= 0) {
                int bookingId = index + 1;
                clinicManager.attendAppointment(bookingId);
                JOptionPane.showMessageDialog(this, "🎯 Appointment Marked as Attended!", "Info", JOptionPane.INFORMATION_MESSAGE);
                loadAppointments();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Select an appointment to mark as attended!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void loadAppointments() {
        appointmentListModel.clear(); // Clears old data before adding new
        for (Appointment appointment : clinicManager.getAppointments()) {
            appointmentListModel.addElement("ID " + appointment.getBookingId() + ": " +
                    appointment.getTreatment().getName() + " | " +
                    appointment.getPatient().getName() + " | " +
                    appointment.getStatus());
        }
    }
}
