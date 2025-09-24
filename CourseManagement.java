import java.util.ArrayList;
import java.util.Scanner;

public class CourseManagement {
    private final ArrayList<CourseModel> courses;
    private final Scanner scanner;

    public CourseManagement(Scanner scanner) {
        this.courses = new ArrayList<>();
        this.scanner = scanner;
    }

    public ArrayList<CourseModel> getCourses() {
        return courses;
    }

    public void addCourse() {
        System.out.println("\n=== Add New Course ===");
        
        String courseName = Utils.readInput(scanner, "Enter course name: ");
        String courseDuration = Utils.readInput(scanner, "Enter course duration (e.g., '3 months'): ");

        if (courses.stream().anyMatch(c -> c.getCourseName().equalsIgnoreCase(courseName))) {
            System.out.println("Error: A course with this name already exists!");
            return;
        }

        CourseModel newCourse = new CourseModel(
            Utils.generateUniqueId(),
            courseName,
            courseDuration
        );

        courses.add(newCourse);
        System.out.println("Course added successfully!");
    }

    public void deleteCourse() {
        System.out.println("\n=== Delete Course ===");
        System.out.println("Available courses:");
        
        courses.forEach(c -> System.out.println(c.toString()));

        String courseId = Utils.readInput(scanner, "Enter course ID to delete: ");
        
        try {
            int id = Integer.parseInt(courseId);
            CourseModel courseToDelete = courses.stream()
                                              .filter(c -> c.getCourseId() == id)
                                              .findFirst()
                                              .orElse(null);

            if (courseToDelete != null) {
                courses.remove(courseToDelete);
                System.out.println("Course deleted successfully!");
            } else {
                System.out.println("Course not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    public CourseModel findCourseById(int courseId) {
        return courses.stream()
                     .filter(c -> c.getCourseId() == courseId)
                     .findFirst()
                     .orElse(null);
    }

    public void displayAllCourses() {
        System.out.println("\n=== Available Courses ===");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            courses.forEach(c -> System.out.println(c.toString()));
        }
    }

    public void addAssignment() {
        System.out.println("\n=== Add New Assignment ===");
        displayAllCourses();
        
        if (courses.isEmpty()) {
            return;
        }

        try {
            int courseId = Integer.parseInt(Utils.readInput(scanner, "Enter course ID: "));
            CourseModel course = findCourseById(courseId);

            if (course == null) {
                System.out.println("Course not found!");
                return;
            }

            String title = Utils.readInput(scanner, "Enter assignment title: ");
            String description = Utils.readInput(scanner, "Enter assignment description: ");
            String dueDate = Utils.readInput(scanner, "Enter due date: ");
            int maxScore = Integer.parseInt(Utils.readInput(scanner, "Enter maximum score: "));

            Assignment assignment = new Assignment(
                Utils.generateUniqueId(),
                title,
                description,
                dueDate,
                maxScore,
                courseId
            );

            course.addAssignment(assignment);
            System.out.println("Assignment added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
    }

    public void viewAssignments(int courseId) {
        CourseModel course = findCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        System.out.println("\n=== Assignments for " + course.getCourseName() + " ===");
        ArrayList<Assignment> assignments = course.getAssignments();
        
        if (assignments.isEmpty()) {
            System.out.println("No assignments found for this course.");
        } else {
            assignments.forEach(a -> System.out.println(a.toString()));
        }
    }

    public void deleteAssignment() {
        System.out.println("\n=== Delete Assignment ===");
        displayAllCourses();
        
        if (courses.isEmpty()) {
            return;
        }

        try {
            int courseId = Integer.parseInt(Utils.readInput(scanner, "Enter course ID: "));
            CourseModel course = findCourseById(courseId);

            if (course == null) {
                System.out.println("Course not found!");
                return;
            }

            viewAssignments(courseId);
            ArrayList<Assignment> assignments = course.getAssignments();
            
            if (assignments.isEmpty()) {
                return;
            }

            int assignmentId = Integer.parseInt(Utils.readInput(scanner, "Enter assignment ID to delete: "));
            Assignment assignment = course.findAssignment(assignmentId);

            if (assignment != null) {
                course.removeAssignment(assignmentId);
                System.out.println("Assignment deleted successfully!");
            } else {
                System.out.println("Assignment not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
    }
}