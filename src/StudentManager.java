import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentManager {

    private final ArrayList<Student> students;
    private final Scanner scanner;

    public StudentManager() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Add Student
    public void addStudent() {
        try {
            System.out.println("\n===== ADD STUDENT =====");

            int id = readPositiveInt("Enter Student ID: ");

            if (findStudentById(id) != null) {
                System.out.println("Error: Student with ID " + id + " already exists.");
                return;
            }

            String name = readValidName("Enter Student Name: ");
            double marks = readValidMarks("Enter Marks (0-100): ");

            Student student = new Student(id, name, marks);
            students.add(student);

            System.out.println("Student added successfully.");

        } catch (Exception e) {
            System.out.println("Error while adding student: " + e.getMessage());
        }
    }

    // View All Students
    public void viewStudents() {
        System.out.println("\n===== STUDENT LIST =====");

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        printTableHeader();

        for (Student student : students) {
            printStudent(student);
        }

        printTableFooter();
        System.out.println("Total Students: " + students.size());
    }

    // Search Student by ID
    public void searchById() {
        try {
            System.out.println("\n===== SEARCH STUDENT BY ID =====");

            int id = readPositiveInt("Enter Student ID: ");
            Student student = findStudentById(id);

            if (student == null) {
                System.out.println("No student found with ID: " + id);
                return;
            }

            printTableHeader();
            printStudent(student);
            printTableFooter();

        } catch (Exception e) {
            System.out.println("Error while searching student: " + e.getMessage());
        }
    }

    // Search Student by Name - Partial and Case-Insensitive
    public void searchByName() {
        try {
            System.out.println("\n===== SEARCH STUDENT BY NAME =====");

            String searchName = readNonEmptyString("Enter Student Name: ")
                    .toLowerCase();

            List<Student> matchedStudents = new ArrayList<>();

            for (Student student : students) {
                if (student.getName().toLowerCase().contains(searchName)) {
                    matchedStudents.add(student);
                }
            }

            if (matchedStudents.isEmpty()) {
                System.out.println("No students found matching: " + searchName);
                return;
            }

            System.out.println("\nFound " + matchedStudents.size() + " student(s):");
            printTableHeader();

            for (Student student : matchedStudents) {
                printStudent(student);
            }

            printTableFooter();

        } catch (Exception e) {
            System.out.println("Error while searching student: " + e.getMessage());
        }
    }

    // Update Student by ID
    public void updateStudentById() {
        try {
            System.out.println("\n===== UPDATE STUDENT =====");

            int id = readPositiveInt("Enter Student ID to update: ");
            Student student = findStudentById(id);

            if (student == null) {
                System.out.println("No student found with ID: " + id);
                return;
            }

            System.out.println("\nCurrent Student Details:");
            printTableHeader();
            printStudent(student);
            printTableFooter();

            String newName = readValidName("Enter New Name: ");
            double newMarks = readValidMarks("Enter New Marks (0-100): ");

            student.setName(newName);
            student.setMarks(newMarks);

            System.out.println("Student updated successfully.");

        } catch (Exception e) {
            System.out.println("Error while updating student: " + e.getMessage());
        }
    }

    // Delete Student by ID
    public void deleteStudentById() {
        try {
            System.out.println("\n===== DELETE STUDENT =====");

            int id = readPositiveInt("Enter Student ID to delete: ");
            Student student = findStudentById(id);

            if (student == null) {
                System.out.println("No student found with ID: " + id);
                return;
            }

            System.out.println("\nStudent Details:");
            printTableHeader();
            printStudent(student);
            printTableFooter();

            String confirmation = readNonEmptyString(
                    "Are you sure you want to delete this student? (Y/N): "
            );

            if (confirmation.equalsIgnoreCase("Y")
                    || confirmation.equalsIgnoreCase("YES")) {

                students.remove(student);
                System.out.println("Student deleted successfully.");

            } else {
                System.out.println("Delete operation cancelled.");
            }

        } catch (Exception e) {
            System.out.println("Error while deleting student: " + e.getMessage());
        }
    }

    // Sort Students by Name
    public void sortByName() {
        if (students.isEmpty()) {
            System.out.println("No students available to sort.");
            return;
        }

        try {
            students.sort(
                    Comparator.comparing(
                            Student::getName,
                            String.CASE_INSENSITIVE_ORDER
                    )
            );

            System.out.println("Students sorted by name successfully.");
            viewStudents();

        } catch (Exception e) {
            System.out.println("Error while sorting students by name: "
                    + e.getMessage());
        }
    }

    // Sort Students by Marks - Highest to Lowest
    public void sortByMarks() {
        if (students.isEmpty()) {
            System.out.println("No students available to sort.");
            return;
        }

        try {
            students.sort(
                    Comparator.comparingDouble(Student::getMarks)
                            .reversed()
            );

            System.out.println("Students sorted by marks successfully.");
            viewStudents();

        } catch (Exception e) {
            System.out.println("Error while sorting students by marks: "
                    + e.getMessage());
        }
    }

    // Summary Report
    public void displaySummaryReport() {
        System.out.println("\n===== SUMMARY REPORT =====");

        if (students.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        try {
            double totalMarks = 0;
            Student highestScorer = students.get(0);
            Student lowestScorer = students.get(0);

            for (Student student : students) {
                totalMarks += student.getMarks();

                if (student.getMarks() > highestScorer.getMarks()) {
                    highestScorer = student;
                }

                if (student.getMarks() < lowestScorer.getMarks()) {
                    lowestScorer = student;
                }
            }

            double averageMarks = totalMarks / students.size();

            System.out.println("Total Students  : " + students.size());
            System.out.printf("Average Marks   : %.2f%n", averageMarks);
            System.out.printf(
                    "Highest Marks   : %.2f (%s, ID: %d)%n",
                    highestScorer.getMarks(),
                    highestScorer.getName(),
                    highestScorer.getId()
            );
            System.out.printf(
                    "Lowest Marks    : %.2f (%s, ID: %d)%n",
                    lowestScorer.getMarks(),
                    lowestScorer.getName(),
                    lowestScorer.getId()
            );

        } catch (Exception e) {
            System.out.println("Error while generating summary report: "
                    + e.getMessage());
        }
    }

    // Grade Statistics
    public void displayGradeStatistics() {
        System.out.println("\n===== GRADE STATISTICS =====");

        if (students.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        try {
            int gradeA = 0;
            int gradeB = 0;
            int gradeC = 0;
            int gradeD = 0;
            int gradeF = 0;

            for (Student student : students) {
                double marks = student.getMarks();

                if (marks >= 90) {
                    gradeA++;
                } else if (marks >= 80) {
                    gradeB++;
                } else if (marks >= 70) {
                    gradeC++;
                } else if (marks >= 60) {
                    gradeD++;
                } else {
                    gradeF++;
                }
            }

            int total = students.size();

            System.out.printf(
                    "Grade A (90-100) : %d (%.2f%%)%n",
                    gradeA,
                    calculatePercentage(gradeA, total)
            );

            System.out.printf(
                    "Grade B (80-89)  : %d (%.2f%%)%n",
                    gradeB,
                    calculatePercentage(gradeB, total)
            );

            System.out.printf(
                    "Grade C (70-79)  : %d (%.2f%%)%n",
                    gradeC,
                    calculatePercentage(gradeC, total)
            );

            System.out.printf(
                    "Grade D (60-69)  : %d (%.2f%%)%n",
                    gradeD,
                    calculatePercentage(gradeD, total)
            );

            System.out.printf(
                    "Grade F (0-59)   : %d (%.2f%%)%n",
                    gradeF,
                    calculatePercentage(gradeF, total)
            );

        } catch (Exception e) {
            System.out.println("Error while generating grade statistics: "
                    + e.getMessage());
        }
    }

    // Find Student by ID
    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    // Input Validation - Positive Integer
    private int readPositiveInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = scanner.nextInt();
                scanner.nextLine();

                if (value <= 0) {
                    System.out.println(
                            "Invalid input. ID must be a positive number."
                    );
                    continue;
                }

                return value;

            } catch (InputMismatchException e) {
                System.out.println(
                        "Invalid input. Please enter a valid integer."
                );
                scanner.nextLine();
            }
        }
    }

    // Input Validation - Marks
    private double readValidMarks(String message) {
        while (true) {
            try {
                System.out.print(message);
                double marks = scanner.nextDouble();
                scanner.nextLine();

                if (marks < 0 || marks > 100) {
                    System.out.println(
                            "Invalid marks. Please enter marks between 0 and 100."
                    );
                    continue;
                }

                return marks;

            } catch (InputMismatchException e) {
                System.out.println(
                        "Invalid input. Please enter numeric marks."
                );
                scanner.nextLine();
            }
        }
    }

    // Input Validation - Name
    private String readValidName(String message) {
        while (true) {
            String name = readNonEmptyString(message);

            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println(
                        "Invalid name. Name should contain only letters and spaces."
                );
                continue;
            }

            return name.trim();
        }
    }

    // Input Validation - Non-Empty String
    private String readNonEmptyString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(
                        "Input cannot be empty. Please try again."
                );
                continue;
            }

            return input;
        }
    }

    // Calculate Percentage
    private double calculatePercentage(int count, int total) {
        if (total == 0) {
            return 0;
        }

        return ((double) count / total) * 100;
    }

    // Print Table Header
    private void printTableHeader() {
        System.out.println(
                "+------------+------------------------------+------------+"
        );
        System.out.printf(
                "| %-10s | %-28s | %-10s |%n",
                "ID",
                "Name",
                "Marks"
        );
        System.out.println(
                "+------------+------------------------------+------------+"
        );
    }

    // Print Student
    private void printStudent(Student student) {
        System.out.printf(
                "| %-10d | %-28s | %-10.2f |%n",
                student.getId(),
                student.getName(),
                student.getMarks()
        );
    }

    // Print Table Footer
    private void printTableFooter() {
        System.out.println(
                "+------------+------------------------------+------------+"
        );
    }
}
