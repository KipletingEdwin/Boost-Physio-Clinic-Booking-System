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

        //Title label
        JLabel titleLabel = new JLabel("Book and Appointment");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // List of Available appointments
        appointmentListModel = new DefaultListModel<>();
        appointmentList = new JList<>(appointmentListModel);
        appointmentList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        appointmentList.setBackground(Color.WHITE);
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
        JButton bookButton = new JButton("Book Appointment");
        JButton cancelButton = new JButton("Cancel Appointment");
        JButton attendButton = new JButton("Attend Appointment");

        //Style Buttons
        bookButton.setBackground(new Color(46,204,113));
        cancelButton.setBackground(new Color(231,76,60));
        attendButton.setBackground(new Color(241,196,15));

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
    }

    private void loadAppointments() {
        // ✅ Ensure appointmentListModel is not null
        if (appointmentListModel == null) {
            appointmentListModel = new DefaultListModel<>();
        }

        appointmentListModel.clear();
        for (Appointment appointment : clinicManager.getAppointments()) {
            appointmentListModel.addElement("ID " + appointment.getBookingId() + ": " +
                    appointment.getTreatment().getName() + " | " +
                    appointment.getPatient().getName() + " | " +
                    appointment.getStatus());
        }
    }
}
