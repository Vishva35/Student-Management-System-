
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class StudentManager {

    private ArrayList<Student> students = new ArrayList<>();

    // Constructor
    public StudentManager() {
        loadStudents();
    }

    // Check duplicate student ID
    private boolean isDuplicateID(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return true;
            }
        }

        return false;
    }

    // Add Student
    public void addStudent(Scanner input) {

        System.out.print("Enter Student ID: ");
        int id = input.nextInt();
        input.nextLine();

        if (id <= 0) {
            System.out.println("Student ID must be positive.");
            return;
        }

        if (isDuplicateID(id)) {
            System.out.println("Student ID already exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = input.nextLine().trim();

        System.out.print("Enter Student Age: ");
        int age = input.nextInt();
        input.nextLine();

        System.out.print("Enter Student Course: ");
        String course = input.nextLine().trim();

        if (name.isEmpty() || course.isEmpty() || age <= 0) {
            System.out.println("Invalid student details!");
            return;
        }

        if (name.contains(",") || course.contains(",")) {
            System.out.println("Commas are not allowed in name or course.");
            return;
        }

        Student student = new Student(id, name, age, course);

        students.add(student);

        if (saveStudents()) {
            System.out.println("Student added successfully!");
        } else {
            students.remove(student);
            System.out.println("Student could not be saved.");
        }
    }

    // View Students
    public void viewStudents() {

        if (students.size() == 0) {

            System.out.println("No students found.");

        } else {

            System.out.println("\n========== STUDENT LIST ==========");

            for (Student student : students) {

                student.displayStudent();

                System.out.println("----------------------------------");
            }
        }
    }

    // Update Student
    public void updateStudent(Scanner input) {

        System.out.print("Enter Student ID to update: ");
        int id = input.nextInt();
        input.nextLine();

        for (Student student : students) {

            if (student.getId() == id) {

                System.out.print("Enter New Name: ");
                String name = input.nextLine().trim();

                System.out.print("Enter New Age: ");
                int age = input.nextInt();
                input.nextLine();

                System.out.print("Enter New Course: ");
                String course = input.nextLine().trim();

                if (name.isEmpty() || course.isEmpty() || age <= 0) {
                    System.out.println("Invalid student details!");
                    return;
                }

                if (name.contains(",") || course.contains(",")) {
                    System.out.println("Commas are not allowed.");
                    return;
                }

                String oldName = student.getName();
                int oldAge = student.getAge();
                String oldCourse = student.getCourse();

                student.setName(name);
                student.setAge(age);
                student.setCourse(course);

                if (saveStudents()) {
                    System.out.println("Student updated successfully!");
                } else {
                    student.setName(oldName);
                    student.setAge(oldAge);
                    student.setCourse(oldCourse);

                    System.out.println("Update could not be saved.");
                }

                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Delete Student
    public void deleteStudent(Scanner input) {

        System.out.print("Enter Student ID to delete: ");
        int id = input.nextInt();

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getId() == id) {

                Student deletedStudent = students.remove(i);

                if (saveStudents()) {
                    System.out.println("Student deleted successfully!");
                } else {
                    students.add(i, deletedStudent);
                    System.out.println("Deletion could not be saved.");
                }

                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Save Students to File
    private boolean saveStudents() {

        try (FileWriter writer = new FileWriter("students.txt")) {

            for (Student student : students) {

                writer.write(
                    student.getId() + "," +
                    student.getName() + "," +
                    student.getAge() + "," +
                    student.getCourse() + "\n"
                );
            }

            return true;

        } catch (IOException e) {

            System.out.println("Error while saving students: " + e.getMessage());
            return false;
        }
    }

    // Load Students from File
    private void loadStudents() {

        File file = new File("students.txt");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                new BufferedReader(new FileReader(file))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {

                lineNumber++;

                String[] data = line.split(",", -1);

                if (data.length != 4) {
                    System.out.println("Skipping invalid record at line " + lineNumber);
                    continue;
                }

                try {

                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    int age = Integer.parseInt(data[2].trim());
                    String course = data[3].trim();

                    if (id <= 0 || age <= 0 ||
                        name.isEmpty() || course.isEmpty()) {

                        System.out.println("Skipping invalid record at line " + lineNumber);
                        continue;
                    }

                    if (name.contains(",") || course.contains(",")) {
                        System.out.println("Skipping invalid record at line " + lineNumber);
                        continue;
                    }

                    if (isDuplicateID(id)) {
                        System.out.println("Skipping duplicate ID at line " + lineNumber);
                        continue;
                    }

                    Student student = new Student(id, name, age, course);

                    students.add(student);

                } catch (NumberFormatException e) {

                    System.out.println("Skipping invalid number at line " + lineNumber);
                }
            }

        } catch (IOException e) {

            System.out.println("Error while loading students: " + e.getMessage());
        }
    }
}
