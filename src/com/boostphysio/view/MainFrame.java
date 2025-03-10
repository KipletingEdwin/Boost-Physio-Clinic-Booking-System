package com.boostphysio.view;

import com.boostphysio.controller.ClinicManager;

import javax.swing.*;

public class MainFrame extends JFrame {

    private ClinicManager clinicManager;
    public MainFrame() {
        this.clinicManager = new ClinicManager();
        //Set Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Boost Physio Clinic Booking System");
        setSize(800, 600);
        setLocationRelativeTo(null);


        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Book Appointment", new BookingPanel());
        tabbedPane.addTab("Manage Patients", new ManagementPanel());
        tabbedPane.addTab("Generate Reports", new ReportPanel());

        add(tabbedPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
