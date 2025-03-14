import com.boostphysio.controller.ClinicManager;
import com.boostphysio.model.Patient;
import com.boostphysio.model.Physiotherapist;

import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final Scanner scanner = new Scanner(System.in);
    private  static final ClinicManager clinicManager = new ClinicManager();


    public static void main(String[] args) {
        setUpSampleData();

        while (true){
            System.out.println("\n=== Boost Physio Clinic ===");
            System.out.println("1️⃣ Add a Patient");
            System.out.println("2️⃣ Remove a Patient");
            System.out.println("3️⃣ Book an Appointment");
            System.out.println("4️⃣ Cancel an Appointment");
            System.out.println("5️⃣ Attend an Appointment");
            System.out.println("6️⃣ Generate Report");
            System.out.println("7️⃣ Exit");
            System.out.print("🔷 Select an option: ");

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
    }
}