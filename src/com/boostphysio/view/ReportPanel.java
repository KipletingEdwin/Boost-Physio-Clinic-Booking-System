package com.boostphysio.view;

import com.boostphysio.controller.ClinicManager;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {
    public ReportPanel(ClinicManager clinicManager) {
        //Add report panel components here
        setLayout(new BorderLayout());

        //Report Display Area

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);

        //Generate Report Button
        JButton generateReportButton = new JButton("Generate Report");

        //Add Components to Panel
        add(scrollPane, BorderLayout.CENTER);
        add(generateReportButton, BorderLayout.SOUTH);

    }
}
