import java.util.ArrayList;
import java.util.Scanner;

public class UserManagement {
    private final ArrayList<User> users;
    private final Scanner scanner;

    public UserManagement() {
        this.users = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        users.add(new User(Utils.generateUniqueId(), "admin", "admin@example.com", "1234567890", "admin123", "admin"));
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
            Utils.generateUniqueId(),
            username,
            email,
            phone,
            password,
            "student"
        );

        users.add(newStudent);
        System.out.println("Student added successfully!");
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
            int id = Integer.parseInt(studentId);
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
}