package com.boostphysio.view;

import com.boostphysio.controller.ClinicManager;
import com.boostphysio.model.Appointment;
import com.boostphysio.model.Patient;
import com.boostphysio.model.Physiotherapist;
import com.boostphysio.model.Treatment;

import javax.swing.*;
import java.awt.*;

public class BookingPanel extends JPanel {

    private ClinicManager clinicManager;
    private DefaultListModel<String> appointmentListModel;
    private  JList<String> appointmentList;


    public BookingPanel(ClinicManager clinicManager) {
        this.clinicManager = clinicManager;
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
        DefaultListModel<String> appointmentsListModel = new DefaultListModel<String>();
        JList<String> appointmentList = new JList<>(appointmentsListModel);
        JScrollPane scrollPane = new JScrollPane(appointmentList);

        //Buttons for Booking,Canceling, and Attending Appointments
        JPanel buttonPanel = new JPanel();
        JButton bookButton = new JButton("Book Appointment");
        JButton cancelButton = new JButton("Cancel Appointment");
        JButton attendButton = new JButton("Attend Appointment");

        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(attendButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(bookButton);
        bottomPanel.add(cancelButton);
        bottomPanel.add(attendButton);

        //Add Components to the Panel

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //Load Appointments into List

        loadAppointments();

        //Book appointment event

        bookButton.addActionListener(e->{
            //Code to book appointment
            String selectedExpertise = (String) expertiseDropdown.getSelectedItem();
            String physioName = searchField.getText();

            //Find a matching physiotherapist

            for(Physiotherapist physio : clinicManager.getPhysiotherapists()){
                if(physio.getName().equalsIgnoreCase(physioName) || physio.getExpertise().contains(selectedExpertise)){
                    //Get First available treatment
                    if(!physio.getTreatments().isEmpty()){
                        Treatment treatment = physio.getTreatments().get(0);
                        Patient patient = clinicManager.getPatients().get(0);  //Select first patient for now
                        clinicManager.bookAppointment(patient, treatment,physio);
                        loadAppointments();
                        return;
                    }
                }

            }
            JOptionPane.showMessageDialog(this, "No matching physiotherapist found","Error", JOptionPane.ERROR_MESSAGE);
        });

        //Cancel appointment event
        cancelButton.addActionListener(e->{
            inr index = appointmentList.getSelectedIndex();
            if(index>=0){
                int bookingId = index + 1;
                clinicManager.cancelAppointment(bookingId);
                loadAppointments();
            } else {
                JOptionPane.showMessageDialog(this, "Select an appointment to cancel", "Warning",JOptionPane.ERROR_MESSAGE);
            }
        });

        //Attend appointment event
        attendButton.addActionListener(e-> {
            int index = appointmentList.getSelectedIndex();
            if(index>=0){
                int bookingId = index + 1;
                clinicManager.attendAppointment(bookingId);
                loadAppointments();
            } else {
                JOptionPane.showMessageDialog(this, "Select an appointment to attend", "Warning", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public  void  loadAppointments(){
        appointmentListModel.clear();
        for(Appointment appointment : clinicManager.getAppointments()){
            appointmentListModel.addElement("ID: " + appointment.getBookingId() + ": " +
                    appointment.getTreatment().getName() + " | " +
                    appointment.getPatient().getName() + " | " +
                    appointment.getStatus());

        }

    }
}
