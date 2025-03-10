package com.boostphysio.view;

import javax.swing.*;
import java.awt.*;

public class BookingPanel extends JPanel {
    public BookingPanel() {
        //Add booking panel components here

        setLayout(new BorderLayout());

        //Dropdown for selecting expertise
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Expertise"));
        JComboBox<String> expertiseDropdown = new JComboBox<String>(new String[]{"Rehabilitation", "Massage", "Osteopathy","Acupuncture"});
        topPanel.add(expertiseDropdown);

        //Search Filed for Physiotherapist

        topPanel.add(new JLabel("Search Physiotherapist: "));
        JTextField searchField = new JTextField(10);
        topPanel.add(searchField);

        //List of Available Appointments
    }
}
