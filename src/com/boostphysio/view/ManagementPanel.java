package com.boostphysio.view;

import com.boostphysio.controller.ClinicManager;
import com.boostphysio.model.Patient;

import javax.swing.*;
import java.awt.*;

public class ManagementPanel extends JPanel {
    private ClinicManager clinicManager;
    private DefaultListModel<String> patientListModel;
    private JList<String> patientList;
    private JTextField patientNameField;

    public ManagementPanel(ClinicManager clinicManager) {
        this.clinicManager = clinicManager;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // 📋 Title Label
        JLabel titleLabel = new JLabel("👥 Manage Patients");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // 📋 Patient List
        patientListModel = new DefaultListModel<>();
        patientList = new JList<>(patientListModel);
        patientList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        patientList.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(patientList);

        // ➕➖ Add and Remove Buttons
        JPanel buttonPanel = new JPanel();
        patientNameField = new JTextField(15);
        patientNameField.setFont(new Font("SansSerif", Font.PLAIN, 14));

        buttonPanel.add(new JLabel("Patient Name:"));
        buttonPanel.add(patientNameField);

        JButton addButton = new JButton("➕ Add Patient");
        JButton removeButton = new JButton("➖ Remove Patient");

        // 🔵 Style Buttons
        addButton.setBackground(new Color(46, 204, 113));  // Green
        removeButton.setBackground(new Color(231, 76, 60));  // Red
        addButton.setForeground(Color.WHITE);
        removeButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        removeButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // 📌 Add Components to Panel
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // ✅ Load Existing Patients
        loadPatients();

        // ✅ Add Patient Button Event
        addButton.addActionListener(e -> {
            String name = patientNameField.getText().trim();
            if (!name.isEmpty()) {
                int id = clinicManager.getPatients().size() + 1;
                Patient patient = new Patient(id, name, "Unknown");
                clinicManager.addPatient(patient);
                JOptionPane.showMessageDialog(this, "✅ Patient Added: " + name, "Success", JOptionPane.INFORMATION_MESSAGE);
                patientNameField.setText("");  // Clear input field
                loadPatients();  // Refresh list
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Please enter a patient name!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ✅ Remove Patient Button Event
        removeButton.addActionListener(e -> {
            int index = patientList.getSelectedIndex();
            if (index >= 0) {
                int patientId = clinicManager.getPatients().get(index).getId();
                clinicManager.removePatient(patientId);
                JOptionPane.showMessageDialog(this, "❌ Patient Removed!", "Info", JOptionPane.INFORMATION_MESSAGE);
                loadPatients();  // Refresh list
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Select a patient to remove!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    // ✅ Load Patients into the List
    private void loadPatients() {
        patientListModel.clear();
        for (Patient patient : clinicManager.getPatients()) {
            patientListModel.addElement("ID " + patient.getId() + ": " + patient.getName());
        }
    }
}
