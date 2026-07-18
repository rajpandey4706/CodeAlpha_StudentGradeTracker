import java.util.Scanner;

public class StudentGradeTracker {

    private static final Scanner sc = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {

        int choice;

        do {
            displayMainMenu();
            choice = getValidChoice();

            switch (choice) {

                case 1:
                    manager.addStudent();
                    break;

                case 2:
                    manager.viewStudents();
                    break;

                case 3:
                    searchMenu();
                    break;

                case 4:
                    manager.updateStudentById();
                    break;

                case 5:
                    manager.deleteStudentById();
                    break;

                case 6:
                    sortMenu();
                    break;

                case 7:
                    manager.displaySummaryReport();
                    break;

                case 8:
                    manager.displayGradeStatistics();
                    break;

                case 9:
                    System.out.println("\n==========================================");
                    System.out.println("   Thank you for using Grade Tracker!");
                    System.out.println("==========================================");
                    break;

                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }

        } while (choice != 9);

        sc.close();
    }


    // ==================== MAIN MENU ====================

    private static void displayMainMenu() {

        System.out.println("\n==========================================");
        System.out.println("         STUDENT GRADE TRACKER");
        System.out.println("==========================================");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student Marks");
        System.out.println("5. Delete Student");
        System.out.println("6. Sort Students");
        System.out.println("7. Display Summary Report");
        System.out.println("8. Display Grade Statistics");
        System.out.println("9. Exit");
        System.out.println("==========================================");
        System.out.print("Enter your choice: ");
    }


    // ==================== SEARCH MENU ====================

    private static void searchMenu() {

        int choice;

        System.out.println("\n==========================================");
        System.out.println("             SEARCH STUDENT");
        System.out.println("==========================================");
        System.out.println("1. Search by Student ID");
        System.out.println("2. Search by Student Name");
        System.out.println("3. Back to Main Menu");
        System.out.println("==========================================");
        System.out.print("Enter your choice: ");

        choice = getValidChoice();

        switch (choice) {

            case 1:
                manager.searchById();
                break;

            case 2:
                manager.searchByName();
                break;

            case 3:
                System.out.println("Returning to Main Menu...");
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }


    // ==================== SORT MENU ====================

    private static void sortMenu() {

        int choice;

        System.out.println("\n==========================================");
        System.out.println("              SORT STUDENTS");
        System.out.println("==========================================");
        System.out.println("1. Sort by Name (A-Z)");
        System.out.println("2. Sort by Marks (Highest to Lowest)");
        System.out.println("3. Back to Main Menu");
        System.out.println("==========================================");
        System.out.print("Enter your choice: ");

        choice = getValidChoice();

        switch (choice) {

            case 1:
                manager.sortByName();
                break;

            case 2:
                manager.sortByMarks();
                break;

            case 3:
                System.out.println("Returning to Main Menu...");
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }


    // ==================== INPUT VALIDATION ====================

    private static int getValidChoice() {

        while (!sc.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number.");
            sc.next();
            System.out.print("Enter your choice: ");
        }

        int choice = sc.nextInt();
        sc.nextLine();

        return choice;
    }
}