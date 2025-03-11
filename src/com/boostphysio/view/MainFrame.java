package com.boostphysio.view;

import com.boostphysio.controller.ClinicManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private ClinicManager clinicManager;

    public MainFrame() {
        this.clinicManager = new ClinicManager();
        //Set Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Boost Physio Clinic Booking System");
//        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        // Modern theme
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }


        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD,14));
        tabbedPane.setBackground(new Color(0x2E86C1));
        tabbedPane.setForeground(Color.WHITE);


        tabbedPane.addTab("Book Appointment", new BookingPanel(clinicManager));
        tabbedPane.addTab("Manage Patients", new ManagementPanel(clinicManager));
        tabbedPane.addTab("Generate Reports", new ReportPanel(clinicManager));

        add(tabbedPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
