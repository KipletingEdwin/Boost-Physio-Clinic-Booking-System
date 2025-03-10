package com.boostphysio.view;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {
    public ReportPanel() {
        //Add report panel components here
        setLayout(new BorderLayout());

        //Report Display Area

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
    }
}
