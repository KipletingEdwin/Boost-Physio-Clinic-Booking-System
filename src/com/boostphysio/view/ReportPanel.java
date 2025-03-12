package com.boostphysio.view;

import com.boostphysio.controller.ClinicManager;
import com.boostphysio.model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportPanel extends JPanel {
    private ClinicManager clinicManager;
    private DefaultTableModel tableModel;
    private JTable reportTable;

    public ReportPanel(ClinicManager clinicManager) {
        this.clinicManager = clinicManager;
        setLayout(new BorderLayout());

        // 📊 Title Label
        JLabel titleLabel = new JLabel("📊 Clinic Appointment Report");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // 📅 Table for Appointment Report
        tableModel = new DefaultTableModel();
        reportTable = new JTable(tableModel);

        // 📝 Set Column Names
        tableModel.addColumn("ID");
        tableModel.addColumn("Treatment");
        tableModel.addColumn("Patient");
        tableModel.addColumn("Physiotherapist");
        tableModel.addColumn("Date");
        tableModel.addColumn("Time");
        tableModel.addColumn("Status");

        JScrollPane scrollPane = new JScrollPane(reportTable);
        add(scrollPane, BorderLayout.CENTER);

        // 🔄 Refresh Button
        JButton refreshButton = new JButton("🔄 Refresh Report");
        refreshButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        refreshButton.setBackground(new Color(52, 152, 219)); // Blue
        refreshButton.setForeground(Color.WHITE);
        refreshButton.addActionListener(e -> loadReport()); // Refresh when clicked

        add(refreshButton, BorderLayout.SOUTH);

        // 📌 Load report when panel opens
        loadReport();
    }

    // ✅ Load Appointments into Table
    private void loadReport() {
        tableModel.setRowCount(0); // Clear table before adding new data

        for (Appointment appointment : clinicManager.getAppointments()) {
            tableModel.addRow(new Object[]{
                    appointment.getBookingId(),
                    appointment.getTreatment().getName(),
                    appointment.getPatient().getName(),
                    appointment.getPhysiotherapist().getName(),
                    appointment.getTreatment().getDate(),
                    appointment.getTreatment().getTime(),
                    appointment.getStatus()
            });
        }
    }
}
