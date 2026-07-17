import java.util.ArrayList;
import java.util.Scanner;

class Student {

    private String name;
    private double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getGrade() {
        if (marks >= 90)
            return "A";
        else if (marks >= 75)
            return "B";
        else if (marks >= 60)
            return "C";
        else
            return "D";
    }

    @Override
    public String toString() {
        return "Name: " + name +
                " | Marks: " + marks +
                " | Grade: " + getGrade();
    }
}

public class StudentGradeTracker {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("      STUDENT GRADE TRACKER");
            System.out.println("==================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Marks");
            System.out.println("5. Delete Student");
            System.out.println("6. Display Summary Report");
            System.out.println("7. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateMarks();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    displaySummary();
                    break;

                case 7:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 7);
    }

    public static void addStudent() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();
        sc.nextLine();

        students.add(new Student(name, marks));

        System.out.println("Student Added Successfully!");
    }

    public static void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No Student Records Found!");
            return;
        }

        System.out.println("\n========== STUDENT LIST ==========");

        for (Student s : students) {
            System.out.println(s);
        }
    }

    public static void searchStudent() {

        if (students.isEmpty()) {
            System.out.println("No Student Records Available!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Student s : students) {

            if (s.getName().equalsIgnoreCase(name)) {
                System.out.println("\nStudent Found:");
                System.out.println(s);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student Not Found!");
        }
    }

    public static void updateMarks() {

        if (students.isEmpty()) {
            System.out.println("No Student Records Available!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Student s : students) {

            if (s.getName().equalsIgnoreCase(name)) {

                System.out.print("Enter New Marks: ");
                double marks = sc.nextDouble();
                sc.nextLine();

                s.setMarks(marks);

                System.out.println("Marks Updated Successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student Not Found!");
        }
    }

    public static void deleteStudent() {

        if (students.isEmpty()) {
            System.out.println("No Student Records Available!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        boolean removed = students.removeIf(
                s -> s.getName().equalsIgnoreCase(name));

        if (removed)
            System.out.println("Student Deleted Successfully!");
        else
            System.out.println("Student Not Found!");
    }

    public static void displaySummary() {

        if (students.isEmpty()) {
            System.out.println("No Student Data Available!");
            return;
        }

        double total = 0;

        Student highest = students.get(0);
        Student lowest = students.get(0);

        for (Student s : students) {

            total += s.getMarks();

            if (s.getMarks() > highest.getMarks()) {
                highest = s;
            }

            if (s.getMarks() < lowest.getMarks()) {
                lowest = s;
            }
        }

        double average = total / students.size();

        System.out.println("\n========== SUMMARY REPORT ==========");
        System.out.println("Total Students : " + students.size());
        System.out.printf("Average Marks  : %.2f%n", average);

        System.out.println("Highest Marks  : " +
                highest.getMarks() +
                " (" + highest.getName() + ")");

        System.out.println("Lowest Marks   : " +
                lowest.getMarks() +
                " (" + lowest.getName() + ")");

        System.out.println("====================================");
    }
}