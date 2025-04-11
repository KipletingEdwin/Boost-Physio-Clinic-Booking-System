import com.boostphysio.controller.ClinicManager;
import com.boostphysio.model.Appointment;
import com.boostphysio.model.Patient;
import com.boostphysio.model.Physiotherapist;
import com.boostphysio.model.Treatment;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final Scanner scanner = new Scanner(System.in);
    private  static final ClinicManager clinicManager = new ClinicManager();


    public static void main(String[] args) {
        setUpSampleData();

        while (true){
            System.out.println("\n=== Boost Physio Clinic ===");
            System.out.println("1.Add a Patient");
            System.out.println("2.Remove a Patient");
            System.out.println("3.Book an Appointment");
            System.out.println("4.Cancel an Appointment");
            System.out.println("5.Attend an Appointment");
            System.out.println("6.Generate Report");
            System.out.println("7.Exit");
            System.out.println("🔷 Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case  1 -> addPatient();
                case  2 -> removePatient();
                case  3 -> bookAppointment();
                case  4 -> cancelAppointment();
                case  5 -> attendAppointment();
                case  6 -> clinicManager.generateReport();
                case  7 -> {
                    System.out.println("🚀 Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("⚠️ Invalid option! Try again.");
            }
        }
    }

    private  static void setUpSampleData(){
        // Add sample Physiotherapist
        Physiotherapist physio1 = new Physiotherapist(1, "Dr. Helen Smith", "0123456789", Arrays.asList("Massage", "Rehabilitation"));
        Physiotherapist physio2 = new Physiotherapist(2, "Dr. James Lee", "0987654321", Arrays.asList("Acupuncture", "Osteopathy"));
        clinicManager.addPhysiotherapist(physio1);
        clinicManager.addPhysiotherapist(physio2);

        // Add sample Patients
        Patient patient1 = new Patient(1, "Alice Johnson", "0551234567");
        Patient patient2 = new Patient(2, "Bob Williams", "0667654321");
        clinicManager.addPatient(patient1);
        clinicManager.addPatient(patient2);
    }
    public  static  void  addPatient(){
        System.out.print("👤 Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("📞 Enter Contact: ");
        String contact = scanner.nextLine();

        int id = clinicManager.getPatients().size() + 1;
        Patient patient = new Patient(id, name, contact);
        clinicManager.addPatient(patient);
        System.out.println("✅ Patient Added: " + name);
    }

    private  static  void  removePatient(){
        System.out.print("🔍 Enter Patient ID to Remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        clinicManager.removePatient(id);
        System.out.println("✅ Patient Removed.");
    }

    public static void bookAppointment() {
        System.out.println("\n📋 Choose booking method:");
        System.out.println("1. Search by Expertise");
        System.out.println("2. Search by Physiotherapist");
        System.out.print("➡️ Enter option: ");
        int method = scanner.nextInt();
        scanner.nextLine();

        List<Physiotherapist> physios = clinicManager.getPhysiotherapists();
        Physiotherapist selectedPhysio = null;

        if (method == 1) {
            System.out.print("🔍 Enter area of expertise (e.g. Massage): ");
            String expertise = scanner.nextLine();

            System.out.println("📋 Physiotherapists with expertise in '" + expertise + "':");
            for (Physiotherapist physio : physios) {
                if (physio.getExpertise().contains(expertise)) {
                    System.out.println(physio.getId() + " - " + physio.getName());
                }
            }
        } else if (method == 2) {
            System.out.println("🧑‍⚕️ Available Physiotherapists:");
            for (Physiotherapist physio : physios) {
                System.out.println(physio.getId() + " - " + physio.getName());
            }
        } else {
            System.out.println("❌ Invalid option.");
            return;
        }

        // Select physiotherapist
        System.out.print("👨‍⚕️ Select Physiotherapist ID: ");
        int physioId = scanner.nextInt();
        scanner.nextLine();

        selectedPhysio = physios.stream()
                .filter(p -> p.getId() == physioId)
                .findFirst()
                .orElse(null);

        if (selectedPhysio == null) {
            System.out.println("❌ Invalid Physiotherapist ID!");
            return;
        }

        // Display and number available slots
        List<String[]> availableSlots = new ArrayList<>();
        int index = 1;

        System.out.println("\n📅 Available Appointment Slots:");
        for (Map.Entry<String, List<String>> entry : selectedPhysio.getSchedule().entrySet()) {
            String date = entry.getKey();
            for (String timeSlot : entry.getValue()) {
                System.out.println(index + ". " + date + " @ " + timeSlot);
                availableSlots.add(new String[]{date, timeSlot});
                index++;
            }
        }

        if (availableSlots.isEmpty()) {
            System.out.println("❌ No available slots for this physiotherapist.");
            return;
        }

        // Let user choose from the list
        System.out.print("➡️ Choose a slot number: ");
        int slotChoice = scanner.nextInt();
        scanner.nextLine();

        if (slotChoice < 1 || slotChoice > availableSlots.size()) {
            System.out.println("❌ Invalid slot number.");
            return;
        }

        String[] selectedSlot = availableSlots.get(slotChoice - 1);
        String date = selectedSlot[0];
        String time = selectedSlot[1];

        // Select patient
        System.out.println("🧑‍🦱 Available Patients:");
        for (Patient patient : clinicManager.getPatients()) {
            System.out.println(patient.getId() + " - " + patient.getName());
        }

        System.out.print("👤 Select Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient selectedPatient = clinicManager.getPatients().stream()
                .filter(p -> p.getId() == patientId)
                .findFirst()
                .orElse(null);

        if (selectedPatient == null) {
            System.out.println("❌ Invalid Patient ID!");
            return;
        }

        // Create and book treatment
        Treatment treatment = new Treatment("Physiotherapy Session", date, time, selectedPhysio);
        boolean success = clinicManager.bookAppointment(selectedPatient, treatment, selectedPhysio);

        if (success) {
            selectedPhysio.removeBookedSlot(date, time);

            Appointment newAppt = clinicManager.getAppointments().get(clinicManager.getAppointments().size() - 1);

            System.out.println("\n✅ Appointment Booked Successfully!");
            System.out.println("📄 Booking ID: " + newAppt.getBookingId());
            System.out.println("👤 Patient: " + newAppt.getPatient().getName());
            System.out.println("🧑‍⚕️ Physio: " + newAppt.getPhysiotherapist().getName());
            System.out.println("💆 Treatment: " + newAppt.getTreatment().getName());
            System.out.println("📅 Date: " + newAppt.getTreatment().getDate());
            System.out.println("⏰ Time: " + newAppt.getTreatment().getTime());
            System.out.println("📌 Status: " + newAppt.getStatus());
        } else {
            System.out.println("❌ Appointment booking failed.");
        }
    }


    private  static  void  cancelAppointment(){
        System.out.print("❌ Enter Booking ID to Cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        clinicManager.cancelAppointment(bookingId);
    }

    private  static  void  attendAppointment(){
        System.out.print("🎯 Enter Booking ID to Mark as Attended: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        clinicManager.attendAppointment(bookingId);
    }
}