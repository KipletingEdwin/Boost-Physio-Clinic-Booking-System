package com.boostphysio.view;

import com.boostphysio.controller.ClinicManager;
import com.boostphysio.model.Patient;
import com.boostphysio.model.Physiotherapist;
import com.boostphysio.model.Treatment;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MainFrame extends JFrame {

    private ClinicManager clinicManager;

    public MainFrame() {
        this.clinicManager = new ClinicManager();
        //Set Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Boost Physio Clinic Booking System");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        // Modern theme
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }


        // 🛠 Add Sample Physiotherapists
        Physiotherapist physio1 = new Physiotherapist(1, "Dr. Helen Smith", "0123456789", Arrays.asList("Massage", "Rehabilitation"));
        Physiotherapist physio2 = new Physiotherapist(2, "Dr. James Lee", "0987654321", Arrays.asList("Acupuncture", "Osteopathy"));
        clinicManager.addPhysiotherapist(physio1);
        clinicManager.addPhysiotherapist(physio2);

        // 🛠 Add Sample Patients
        Patient patient1 = new Patient(1, "Alice Johnson", "0551234567");
        Patient patient2 = new Patient(2, "Bob Williams", "0667654321");
        clinicManager.addPatient(patient1);
        clinicManager.addPatient(patient2);

        // 🛠 Add Treatments to Physiotherapists
        Treatment treatment1 = new Treatment("Massage Therapy", "2025-05-01", "10:00-11:00", physio1);
        Treatment treatment2 = new Treatment("Acupuncture Session", "2025-05-01", "11:30-12:30", physio2);
        physio1.addTreatment(treatment1);
        physio2.addTreatment(treatment2);


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
