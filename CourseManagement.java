import java.util.ArrayList;
import java.util.Scanner;

public class CourseManagement {
    private final ArrayList<CourseModel> courses;
    private final Scanner scanner;

    public CourseManagement() {
        this.courses = new ArrayList<>();
        this.scanner = new Scanner(System.in);
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
}