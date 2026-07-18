public class Student {

    // Instance Variables
    private int id;
    private String name;
    private double marks;

    // Constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Calculate Grade
    public String getGrade() {
        if (marks >= 90) {
            return "A";
        } else if (marks >= 80) {
            return "B";
        } else if (marks >= 70) {
            return "C";
        } else if (marks >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Check Pass or Fail
    public String getResult() {
        if (marks >= 40) {
            return "PASS";
        } else {
            return "FAIL";
        }
    }

    // Display Student Details
    @Override
    public String toString() {
        return "Student ID: " + id +
                " | Name: " + name +
                " | Marks: " + marks +
                " | Grade: " + getGrade() +
                " | Result: " + getResult();
    }
}