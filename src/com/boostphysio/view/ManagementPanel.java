package com.boostphysio.view;

import com.boostphysio.controller.ClinicManager;

import javax.swing.*;
import java.awt.*;

public class ManagementPanel extends JPanel {
    public ManagementPanel(ClinicManager clinicManager) {
        setLayout(new BorderLayout());

        //Patients List
        DefaultListModel<String> patientListModel = new DefaultListModel<String>();
        JList<String> patientList = new JList<>(patientListModel);
        JScrollPane scrollPane = new JScrollPane(patientList);

        //Add and Remove buttons
        JPanel buttonPanel = new JPanel();
        JTextField patientNameField = new JTextField(10);
        buttonPanel.add(new JLabel("Patient Name: "));
        buttonPanel.add(patientNameField);
        JButton addButton = new JButton("Add Patient");
        JButton removeButton = new JButton("Remove Patient");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        //Add Components to Panel
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);



    }
}
