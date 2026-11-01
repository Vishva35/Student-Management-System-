
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        StudentManager manager = new StudentManager();

        while (true) {

            System.out.println("\n====================================");
            System.out.println("      STUDENT MANAGEMENT SYSTEM");
            System.out.println("====================================");

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
                continue;
            }

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {

                manager.addStudent(input);

            } else if (choice == 2) {

                manager.viewStudents();

            } else if (choice == 3) {

                manager.updateStudent(input);

            } else if (choice == 4) {

                manager.deleteStudent(input);

            } else if (choice == 5) {

                System.out.println("Thank you for using Student Management System!");
                break;

            } else {

                System.out.println("Invalid choice! Please try again.");
            }
        }

        input.close();
    }
}
