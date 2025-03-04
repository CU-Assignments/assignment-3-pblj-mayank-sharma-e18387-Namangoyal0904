import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}
class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}
class Course {
    String name;
    String prerequisite;
    int capacity;
    List<String> enrolledStudents;
    public Course(String name, String prerequisite, int capacity) {
        this.name = name;
        this.prerequisite = prerequisite;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }
    public void enroll(String studentName, Map<String, Boolean> completedCourses) throws CourseFullException, PrerequisiteNotMetException {
        if (enrolledStudents.size() >= capacity) {
            throw new CourseFullException("Course " + name + " is full.");
        }
        if (prerequisite != null && !completedCourses.getOrDefault(prerequisite, false)) {
            throw new PrerequisiteNotMetException("Complete " + prerequisite + " before enrolling in " + name + ".");
        }
        enrolledStudents.add(studentName);
        System.out.println(studentName + " enrolled in " + name + " successfully.");
    }
}
public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Course> courses = new HashMap<>();
        Map<String, Boolean> completedCourses = new HashMap<>();
        courses.put("Core Java", new Course("Core Java", null, 10));
        courses.put("Advanced Java", new Course("Advanced Java", "Core Java", 5));
        courses.put("Data Structures", new Course("Data Structures", null, 8));
        courses.put("Algorithms", new Course("Algorithms", "Data Structures", 3));
        completedCourses.put("Core Java", true);
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        while (true) {
            System.out.println("\nAvailable Courses:");
            for (String courseName : courses.keySet()) {
                System.out.println("- " + courseName);
            }
            System.out.print("Enter course to enroll (or 'exit'): ");
            String courseName = scanner.nextLine();
            if (courseName.equalsIgnoreCase("exit")) {
                break;
            }
            Course course = courses.get(courseName);
            if (course != null) {
                try {
                    course.enroll(studentName, completedCourses);
                } catch (CourseFullException | PrerequisiteNotMetException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Course not found.");
            }
        }
        scanner.close();
    }
}
