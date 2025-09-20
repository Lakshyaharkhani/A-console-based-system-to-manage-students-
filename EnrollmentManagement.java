import java.util.Scanner;

public class EnrollmentManagement {
    private final UserManagement userManagement;
    private final CourseManagement courseManagement;
    private final Scanner scanner;

    public EnrollmentManagement(UserManagement userManagement, CourseManagement courseManagement) {
        this.userManagement = userManagement;
        this.courseManagement = courseManagement;
        this.scanner = new Scanner(System.in);
    }

    public void registerUser() {
        System.out.println("\n=== User Registration ===");
        
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

        if (userManagement.findUserByEmail(email) != null) {
            System.out.println("Error: A user with this email already exists!");
            return;
        }

        User newUser = new User(
            Utils.generateUniqueId(),
            username,
            email,
            phone,
            password,
            "student"
        );

        userManagement.getUsers().add(newUser);
        System.out.println("Registration successful! You can now login.");
    }

    public User login() {
        System.out.println("\n=== User Login ===");
        
        String email = Utils.readInput(scanner, "Enter email: ");
        String password = Utils.readInput(scanner, "Enter password: ");

        LoginModel loginModel = new LoginModel(email, password);
        User user = userManagement.findUser(loginModel.getEmail(), loginModel.getPassword());

        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUsername() + "!");
            return user;
        } else {
            System.out.println("Login failed! Invalid email or password.");
            return null;
        }
    }

    public void enrollInCourse(User student) {
        if (student == null) {
            System.out.println("Error: Invalid student information!");
            return;
        }

        if (!"student".equals(student.getUserType())) {
            System.out.println("Error: Only students can enroll in courses!");
            return;
        }

        System.out.println("\n=== Course Enrollment ===");
        courseManagement.displayAllCourses();

        if (courseManagement.getCourses().isEmpty()) {
            return;
        }

        String courseId = Utils.readInput(scanner, "Enter course ID to enroll: ");
        
        try {
            int id = Integer.parseInt(courseId);
            CourseModel course = courseManagement.findCourseById(id);

            if (course != null) {
                if (student.getCourses().contains(course)) {
                    System.out.println("You are already enrolled in this course!");
                    return;
                }

                student.addCourse(course);
                System.out.println("Successfully enrolled in: " + course.getCourseName());
            } else {
                System.out.println("Course not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    public void dropCourse(User student) {
        if (!"student".equals(student.getUserType())) {
            System.out.println("Error: Only students can drop courses!");
            return;
        }

        System.out.println("\n=== Drop Course ===");
        System.out.println("Your enrolled courses:");
        
        if (student.getCourses().isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }

        student.getCourses().forEach(c -> System.out.println(c.toString()));
        String courseId = Utils.readInput(scanner, "Enter course ID to drop: ");
        
        try {
            int id = Integer.parseInt(courseId);
            CourseModel course = student.getCourses().stream()
                                                   .filter(c -> c.getCourseId() == id)
                                                   .findFirst()
                                                   .orElse(null);

            if (course != null) {
                student.removeCourse(course);
                System.out.println("Successfully dropped the course: " + course.getCourseName());
            } else {
                System.out.println("Course not found in your enrolled courses!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    public void viewEnrolledCourses(User student) {
        if (!"student".equals(student.getUserType())) {
            System.out.println("Error: Only students can view enrolled courses!");
            return;
        }

        System.out.println("\n=== Your Enrolled Courses ===");
        if (student.getCourses().isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
        } else {
            student.getCourses().forEach(c -> System.out.println(c.toString()));
        }
    }
}