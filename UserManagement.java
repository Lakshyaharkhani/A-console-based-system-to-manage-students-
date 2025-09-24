import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserManagement {
    private final ArrayList<User> users;
    private final Scanner scanner;

    public UserManagement(Scanner scanner) {
        this.users = new ArrayList<>();
        this.scanner = scanner;
        // Initialize the default admin user
        users.add(new User(generateUniqueUserId(), "admin", "admin@example.com", "1234567890", "admin123", "admin"));
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addStudent() {
        System.out.println("\n=== Add New Student ===");

        String username = Utils.readInput(scanner, "Enter username: ");
        String email = Utils.readInputWithValidation(scanner,
            "Enter email: ",
            "Invalid email format! Please try again.",
            Utils::isValidEmail);

        String phone = Utils.readInputWithValidation(scanner,
            "Enter phone number (10 digits): ",
            "Invalid phone number! Please try again.",
            Utils::isValidPhone);

        String password = Utils.readInputWithValidation(scanner,
            "Enter password (min 6 characters): ",
            "Password must be at least 6 characters!",
            Utils::isValidPassword);

        if (users.stream().anyMatch(u -> u.getEmail().equals(email))) {
            System.out.println("Error: A user with this email already exists!");
            return;
        }

        User newStudent = new User(
            generateUniqueUserId(),
            username,
            email,
            phone,
            password,
            "student"
        );

        users.add(newStudent);
        System.out.println("Student added successfully!");
    }

    private long generateUniqueUserId() {
        while (true) {
            long candidate = Utils.generateUniqueUserId10Digits();
            final long checkId = candidate;
            boolean exists = users.stream().anyMatch(u -> u.getUserId() == checkId);
            if (!exists) {
                return candidate;
            }
        }
    }

    public void deleteStudent() {
        System.out.println("\n=== Delete Student ===");
        System.out.println("Available students:");

        users.stream()
             .filter(u -> u.getUserType().equals("student"))
             .forEach(u -> System.out.println("ID: " + u.getUserId() +
                                            ", Username: " + u.getUsername() +
                                            ", Email: " + u.getEmail()));

        String studentId = Utils.readInput(scanner, "Enter student ID to delete: ");

        try {
            long id = Long.parseLong(studentId);
            User studentToDelete = users.stream()
                                      .filter(u -> u.getUserId() == id && u.getUserType().equals("student"))
                                      .findFirst()
                                      .orElse(null);

            if (studentToDelete != null) {
                users.remove(studentToDelete);
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    public User findUser(String email, String password) {
        return users.stream()
                   .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                   .findFirst()
                   .orElse(null);
    }

    public User findUserByEmail(String email) {
        return users.stream()
                   .filter(u -> u.getEmail().equals(email))
                   .findFirst()
                   .orElse(null);
    }

    public void searchStudents() {
        System.out.println("\n=== Search Students ===");
        String query = Utils.readInput(scanner, "Enter ID or name or email: ");
        // Try ID first
        try {
            long id = Long.parseLong(query);
            User student = users.stream()
                              .filter(u -> u.getUserType().equals("student") && u.getUserId() == id)
                              .findFirst()
                              .orElse(null);
            displaySearchResult(student);
            return;
        } catch (NumberFormatException ignore) {
            // Not an ID, proceed
        }

        // If not ID, search by name (contains) or exact email match
        List<User> foundStudents = users.stream()
                                      .filter(u -> u.getUserType().equals("student") &&
                                             (u.getUsername().toLowerCase().contains(query.toLowerCase()) ||
                                              u.getEmail().equalsIgnoreCase(query)))
                                      .collect(Collectors.toList());
        if (foundStudents.size() == 1) {
            displaySearchResult(foundStudents.get(0));
        } else {
            displaySearchResults(foundStudents);
        }
    }

    private void searchStudentById() {
        String studentId = Utils.readInput(scanner, "Enter student ID: ");
        try {
            long id = Long.parseLong(studentId);
            User student = users.stream()
                              .filter(u -> u.getUserId() == id && u.getUserType().equals("student"))
                              .findFirst()
                              .orElse(null);
            displaySearchResult(student);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private void searchStudentByName() {
        String name = Utils.readInput(scanner, "Enter student name: ");
        List<User> foundStudents = users.stream()
                                      .filter(u -> u.getUserType().equals("student") &&
                                                 u.getUsername().toLowerCase().contains(name.toLowerCase()))
                                      .collect(Collectors.toList());
        displaySearchResults(foundStudents);
    }

    private void searchStudentByEmail() {
        String email = Utils.readInput(scanner, "Enter student email: ");
        User student = users.stream()
                          .filter(u -> u.getUserType().equals("student") &&
                                     u.getEmail().toLowerCase().contains(email.toLowerCase()))
                          .findFirst()
                          .orElse(null);
        displaySearchResult(student);
    }

    private void displaySearchResult(User student) {
        if (student != null) {
            System.out.println("\nStudent found:");
            displayStudentDetails(student);
        } else {
            System.out.println("No student found!");
        }
    }

    private void displaySearchResults(List<User> students) {
        if (!students.isEmpty()) {
            System.out.println("\nFound " + students.size() + " student(s):");
            students.forEach(this::displayStudentDetails);
        } else {
            System.out.println("No students found!");
        }
    }

    private void displayStudentDetails(User student) {
        System.out.println("\nID: " + student.getUserId());
        System.out.println("Name: " + student.getUsername());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Phone: " + student.getPhone());
        System.out.println("Enrolled Courses: " + student.getCourses().size());
        if (!student.getCourses().isEmpty()) {
            student.getCourses().forEach(c -> System.out.println("  - " + c.getCourseName() + " (" + c.getCourseId() + ")"));
        }
    }
}