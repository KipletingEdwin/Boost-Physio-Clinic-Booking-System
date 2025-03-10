package com.boostphysio.view;

import javax.swing.*;
import java.awt.*;

public class ManagementPanel extends JPanel {
    public ManagementPanel() {
        setLayout(new BorderLayout());

        //Patients List
        DefaultListModel<String> patientListModel = new DefaultListModel<String>();
        JList<String> patientList = new JList<>(patientListModel);
        JScrollPane scrollPane = new JScrollPane(patientList);


        JLabel label = new JLabel("Management Panel");
        add(label);
    }
}
