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
            System.out.println("6.Change Appointment");
            System.out.println("7.Generate Report");
            System.out.println("8.Exit");
            System.out.println("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case  1 -> addPatient();
                case  2 -> removePatient();
                case  3 -> bookAppointment();
                case  4 -> cancelAppointment();
                case  5 -> attendAppointment();
                case  6 -> changeAppointment();
                case  7 -> clinicManager.generateReport();
                case  8 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }

    private  static void setUpSampleData(){
        // Add sample Physiotherapist
        clinicManager.addPhysiotherapist(new Physiotherapist(1, "Dr. Zoe Miller", "111 Movement Rd.", "0751122334", Arrays.asList("Physiotherapy", "Massage")));
        clinicManager.addPhysiotherapist(new Physiotherapist(2, "Dr. Ryan Brooks", "222 Recovery Blvd.", "0762233445", Arrays.asList("Mobilisation of the spine and joints", "Osteopathy")));
        clinicManager.addPhysiotherapist(new Physiotherapist(3, "Dr. Emily Chen", "333 Alignment Ave", "0773344556", Arrays.asList("Acupuncture", "Rehabilitation")));
        clinicManager.addPhysiotherapist(new Physiotherapist(4, "Dr. Jason Turner", "444 Spine Park", "0784455667", Arrays.asList("Neural mobilisation", "Massage")));
        clinicManager.addPhysiotherapist(new Physiotherapist(5, "Dr. Megan Scott", "555 Flex Lane", "0795566778", Arrays.asList("Pool rehabilitation", "Rehabilitation")));


        // Add sample Patients
        clinicManager.addPatient(new Patient(1, "Kevin Adams", "111 Fitness St", "0567812345"));
        clinicManager.addPatient(new Patient(2, "Linda Baker", "212 Motion Rd", "0678923456"));
        clinicManager.addPatient(new Patient(3, "Michael Carter", "313 Balance Blvd", "0789034567"));
        clinicManager.addPatient(new Patient(4, "Natalie Diaz", "414 Spine Ct", "0890145678"));
        clinicManager.addPatient(new Patient(5, "Owen Edwards", "515 Stretch Ave", "0901256789"));
        clinicManager.addPatient(new Patient(6, "Paula Flores", "616 Physio Dr", "0912367890"));
        clinicManager.addPatient(new Patient(7, "Quinn Graham", "717 Health Loop", "0923478901"));
        clinicManager.addPatient(new Patient(8, "Rachel Hughes", "818 Recovery Pkwy", "0934589012"));
        clinicManager.addPatient(new Patient(9, "Samuel Ingram", "919 Core Ln", "0945690123"));
        clinicManager.addPatient(new Patient(10, "Tina Jenkins", "1020 Therapy Way", "0956701234"));

    }

    public  static  void  addPatient(){
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();

        int id = clinicManager.getPatients().size() + 1;
        Patient patient = new Patient(id, name, address,contact);
        clinicManager.addPatient(patient);
        System.out.println("Patient Added: " + name);
    }

    private static void removePatient() {
        List<Patient> patients = clinicManager.getPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients available to remove.");
            return;
        }
        System.out.println("\n Current Patients:");
        for (Patient patient : patients) {
            System.out.println(patient.getId() + " - " + patient.getName() + " | Address: " + patient.getAddress() + " | Contact: " + patient.getContact());
        }
        System.out.print("Enter Patient ID to Remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<Patient> patientToRemove = patients.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        if (patientToRemove.isEmpty()) {
            System.out.println("Patient ID not found.");
            return;
        }
        String removedName = patientToRemove.get().getName();
        clinicManager.removePatient(id);
        System.out.println("Patient Removed: " + removedName + " (ID " + id + ")");
    }

    public static void bookAppointment() {
        System.out.println("\n Choose booking method:");
        System.out.println("1. Search by Expertise");
        System.out.println("2. Search by Physiotherapist");
        System.out.print("Enter option: ");
        int method = scanner.nextInt();
        scanner.nextLine();

        List<Physiotherapist> physios = clinicManager.getPhysiotherapists();
        Physiotherapist selectedPhysio = null;

        if (method == 1) {
            // Collect unique expertise
            Set<String> allExpertise = new LinkedHashSet<>();
            for (Physiotherapist physio : physios) {
                allExpertise.addAll(physio.getExpertise());
            }

            List<String> expertiseList = new ArrayList<>(allExpertise);
            System.out.println("Available Expertise Areas:");
            for (int i = 0; i < expertiseList.size(); i++) {
                System.out.println((i + 1) + ". " + expertiseList.get(i));
            }

            System.out.print("Choose an expertise by number: ");
            int expertiseChoice = scanner.nextInt();
            scanner.nextLine();

            if (expertiseChoice < 1 || expertiseChoice > expertiseList.size()) {
                System.out.println("Invalid expertise selection.");
                return;
            }

            String selectedExpertise = expertiseList.get(expertiseChoice - 1);

            System.out.println("Physiotherapists with expertise in '" + selectedExpertise + "':");
            for (Physiotherapist physio : physios) {
                if (physio.getExpertise().contains(selectedExpertise)) {
                    System.out.println(physio.getId() + " - " + physio.getName());
                }
            }
        } else if (method == 2) {
            System.out.println("Available Physiotherapists:");
            for (Physiotherapist physio : physios) {
                System.out.println(physio.getId() + " - " + physio.getName());
            }
        } else {
            System.out.println("Invalid option.");
            return;
        }

        // Select physiotherapist
        System.out.print("Select Physiotherapist ID: ");
        int physioId = scanner.nextInt();
        scanner.nextLine();

        selectedPhysio = physios.stream()
                .filter(p -> p.getId() == physioId)
                .findFirst()
                .orElse(null);

        if (selectedPhysio == null) {
            System.out.println("Invalid Physiotherapist ID!");
            return;
        }

        // Display and number available slots
        List<String[]> availableSlots = new ArrayList<>();
        int index = 1;

        System.out.println("\n Available Appointment Slots:");
        for (Map.Entry<String, List<String>> entry : selectedPhysio.getSchedule().entrySet()) {
            String date = entry.getKey();
            for (String timeSlot : entry.getValue()) {
                System.out.println(index + ". " + date + " @ " + timeSlot);
                availableSlots.add(new String[]{date, timeSlot});
                index++;
            }
        }

        if (availableSlots.isEmpty()) {
            System.out.println("No available slots for this physiotherapist.");
            return;
        }

        // Let user choose from the list
        System.out.print("Choose a slot number: ");
        int slotChoice = scanner.nextInt();
        scanner.nextLine();

        if (slotChoice < 1 || slotChoice > availableSlots.size()) {
            System.out.println("Invalid slot number.");
            return;
        }

        String[] selectedSlot = availableSlots.get(slotChoice - 1);
        String date = selectedSlot[0];
        String time = selectedSlot[1];

        // Select patient
        System.out.println("Available Patients:");
        for (Patient patient : clinicManager.getPatients()) {
            System.out.println(patient.getId() + " - " + patient.getName());
        }

        System.out.print("Select Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient selectedPatient = clinicManager.getPatients().stream()
                .filter(p -> p.getId() == patientId)
                .findFirst()
                .orElse(null);

        if (selectedPatient == null) {
            System.out.println("Invalid Patient ID!");
            return;
        }

        // Create and book treatment
        Treatment treatment = new Treatment("Physiotherapy Session", date, time, selectedPhysio);
        boolean success = clinicManager.bookAppointment(selectedPatient, treatment, selectedPhysio);

        if (success) {
            selectedPhysio.removeBookedSlot(date, time);

            Appointment newAppt = clinicManager.getAppointments().get(clinicManager.getAppointments().size() - 1);

            System.out.println("\n Appointment Booked Successfully!");
            System.out.println("Booking ID: " + newAppt.getBookingId());
            System.out.println("Patient: " + newAppt.getPatient().getName());
            System.out.println("Physio: " + newAppt.getPhysiotherapist().getName());
            System.out.println("Treatment: " + newAppt.getTreatment().getName());
            System.out.println("Date: " + newAppt.getTreatment().getDate());
            System.out.println("Time: " + newAppt.getTreatment().getTime());
            System.out.println("Status: " + newAppt.getStatus());
        } else {
            System.out.println("Appointment booking failed.");
        }
    }



    private static void cancelAppointment() {
        System.out.print("Enter your Patient ID to view your bookings: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient patient = clinicManager.getPatients().stream()
                .filter(p -> p.getId() == patientId)
                .findFirst()
                .orElse(null);

        if (patient == null) {
            System.out.println("Invalid Patient ID.");
            return;
        }

        List<Appointment> bookings = clinicManager.getAppointments().stream()
                .filter(appt -> appt.getPatient().getId() == patientId && appt.getStatus().equals("Booked"))
                .toList();

        if (bookings.isEmpty()) {
            System.out.println("You have no booked appointments to cancel.");
            return;
        }

        System.out.println("\n Your Booked Appointments:");
        for (Appointment appt : bookings) {
            System.out.println("ID: " + appt.getBookingId()
                    + " | " + appt.getTreatment().getDate()
                    + " @ " + appt.getTreatment().getTime()
                    + " | " + appt.getPhysiotherapist().getName());
        }

        System.out.print("Enter the Booking ID to cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();
        clinicManager.cancelAppointment(bookingId);
    }


    private static void attendAppointment() {
        System.out.print("Enter your Patient ID to view your appointments: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();

        Patient patient = clinicManager.getPatients().stream()
                .filter(p -> p.getId() == patientId)
                .findFirst()
                .orElse(null);

        if (patient == null) {
            System.out.println("Invalid Patient ID.");
            return;
        }

        List<Appointment> bookings = clinicManager.getAppointments().stream()
                .filter(appt -> appt.getPatient().getId() == patientId && appt.getStatus().equals("Booked"))
                .toList();

        if (bookings.isEmpty()) {
            System.out.println("You have no upcoming appointments to attend.");
            return;
        }

        System.out.println("\n Your Booked Appointments:");
        for (Appointment appt : bookings) {
            System.out.println(appt.getBookingId()
                    + ". " + appt.getTreatment().getDate()
                    + " @ " + appt.getTreatment().getTime()
                    + " | " + appt.getPhysiotherapist().getName());
        }

        System.out.print("Enter the Booking ID to mark as attended: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        clinicManager.attendAppointment(bookingId);
    }

    private static void changeAppointment() {
        System.out.print("Enter Booking ID to change: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Available Physiotherapists:");
        for (Physiotherapist physio : clinicManager.getPhysiotherapists()) {
            System.out.println(physio.getId() + " - " + physio.getName());
        }

        System.out.print("Enter new Physiotherapist ID: ");
        int physioId = scanner.nextInt();
        scanner.nextLine();

        Physiotherapist newPhysio = clinicManager.getPhysiotherapists().stream()
                .filter(p -> p.getId() == physioId)
                .findFirst()
                .orElse(null);

        if (newPhysio == null) {
            System.out.println("Invalid Physiotherapist ID!");
            return;
        }

        // Show available slots
        List<String[]> availableSlots = new ArrayList<>();
        int index = 1;

        System.out.println("\n Available Slots:");
        for (Map.Entry<String, List<String>> entry : newPhysio.getSchedule().entrySet()) {
            String date = entry.getKey();
            for (String time : entry.getValue()) {
                System.out.println(index + ". " + date + " @ " + time);
                availableSlots.add(new String[]{date, time});
                index++;
            }
        }

        if (availableSlots.isEmpty()) {
            System.out.println("No available slots.");
            return;
        }

        System.out.print("Choose a slot number: ");
        int slotChoice = scanner.nextInt();
        scanner.nextLine();

        if (slotChoice < 1 || slotChoice > availableSlots.size()) {
            System.out.println("Invalid slot.");
            return;
        }

        String[] slot = availableSlots.get(slotChoice - 1);
        String date = slot[0];
        String time = slot[1];

        Treatment newTreatment = new Treatment("Changed Session", date, time, newPhysio);
        boolean updated = clinicManager.changeAppointment(bookingId, newTreatment, newPhysio);

        if (updated) {
            System.out.println("Appointment changed successfully!");
        }
    }
}