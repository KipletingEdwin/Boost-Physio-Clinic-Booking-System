import com.boostphysio.controller.ClinicManager;
import com.boostphysio.model.Appointment;
import com.boostphysio.model.Patient;
import com.boostphysio.model.Physiotherapist;
import com.boostphysio.model.Treatment;

import java.util.*;
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
        clinicManager.addPhysiotherapist(new Physiotherapist(1, "Dr. Helen Smith", "123 Wellness St.", "0123456789", Arrays.asList("Massage", "Rehabilitation")));
        clinicManager.addPhysiotherapist(new Physiotherapist(2, "Dr. James Lee", "456 Recovery Rd.", "0987654321", Arrays.asList("Acupuncture", "Osteopathy")));
        clinicManager.addPhysiotherapist(new Physiotherapist(3, "Dr. Sarah Patel", "789 Spine Way", "0112233445", Arrays.asList("Physiotherapy", "Mobilisation")));
        clinicManager.addPhysiotherapist(new Physiotherapist(4, "Dr. Thomas Kim", "321 Movement Ave", "0765432198", Arrays.asList("Pool rehabilitation", "Rehabilitation")));
        clinicManager.addPhysiotherapist(new Physiotherapist(5, "Dr. Maria Gonzalez", "654 Joint St.", "0887654321", Arrays.asList("Massage", "Neural mobilisation")));

        // Add sample Patients
        clinicManager.addPatient(new Patient(1, "Alice Johnson", "101 Main Street", "0551234567"));
        clinicManager.addPatient(new Patient(2, "Bob Williams", "202 Lakeview Blvd", "0667654321"));
        clinicManager.addPatient(new Patient(3, "Cathy Miller", "303 Hilltop Rd", "0776543210"));
        clinicManager.addPatient(new Patient(4, "David Brown", "404 Green Lane", "0881234567"));
        clinicManager.addPatient(new Patient(5, "Ella White", "505 Oak Crescent", "0998765432"));
        clinicManager.addPatient(new Patient(6, "Frank Thomas", "606 Wellness Dr", "0654321987"));
        clinicManager.addPatient(new Patient(7, "Grace Evans", "707 Harmony Pl", "0765432198"));
        clinicManager.addPatient(new Patient(8, "Hannah Green", "808 Sunset St", "0876543212"));
        clinicManager.addPatient(new Patient(9, "Isaac Young", "909 Sunrise Ave", "0987654321"));
        clinicManager.addPatient(new Patient(10, "Julia King", "1010 Meadow View", "0678123456"));

    }
    public  static  void  addPatient(){
        System.out.print("👤 Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("📞 Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("📞 Enter Contact: ");
        String contact = scanner.nextLine();

        int id = clinicManager.getPatients().size() + 1;
        Patient patient = new Patient(id, name, address,contact);
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


    private static void cancelAppointment() {
        System.out.print("👤 Enter your Patient ID to view your bookings: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient patient = clinicManager.getPatients().stream()
                .filter(p -> p.getId() == patientId)
                .findFirst()
                .orElse(null);

        if (patient == null) {
            System.out.println("❌ Invalid Patient ID.");
            return;
        }

        List<Appointment> bookings = clinicManager.getAppointments().stream()
                .filter(appt -> appt.getPatient().getId() == patientId && appt.getStatus().equals("Booked"))
                .toList();

        if (bookings.isEmpty()) {
            System.out.println("😕 You have no booked appointments to cancel.");
            return;
        }

        System.out.println("\n📋 Your Booked Appointments:");
        for (Appointment appt : bookings) {
            System.out.println("🆔 ID: " + appt.getBookingId()
                    + " | " + appt.getTreatment().getDate()
                    + " @ " + appt.getTreatment().getTime()
                    + " | " + appt.getPhysiotherapist().getName());
        }

        System.out.print("🆔 Enter the Booking ID to cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        clinicManager.cancelAppointment(bookingId);
    }


    private static void attendAppointment() {
        System.out.print("👤 Enter your Patient ID to view your appointments: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient patient = clinicManager.getPatients().stream()
                .filter(p -> p.getId() == patientId)
                .findFirst()
                .orElse(null);

        if (patient == null) {
            System.out.println("❌ Invalid Patient ID.");
            return;
        }

        List<Appointment> bookings = clinicManager.getAppointments().stream()
                .filter(appt -> appt.getPatient().getId() == patientId && appt.getStatus().equals("Booked"))
                .toList();

        if (bookings.isEmpty()) {
            System.out.println("😕 You have no upcoming appointments to attend.");
            return;
        }

        System.out.println("\n📋 Your Booked Appointments:");
        for (Appointment appt : bookings) {
            System.out.println(appt.getBookingId()
                    + ". " + appt.getTreatment().getDate()
                    + " @ " + appt.getTreatment().getTime()
                    + " | " + appt.getPhysiotherapist().getName());
        }

        System.out.print("🆔 Enter the Booking ID to mark as attended: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        clinicManager.attendAppointment(bookingId);
    }

}