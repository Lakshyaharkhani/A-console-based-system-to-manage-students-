import java.util.Scanner;


public class Main {
    private static UserManagement userManagement;
    private static CourseManagement courseManagement;
    private static EnrollmentManagement enrollmentManagement;
    private static Scanner scanner;

    public static void main(String[] args) {
        initialize();
        
        while (true) {
            System.out.println("\n=== Course Management System ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = Utils.readInput(scanner, "");
            
            switch (choice) {
                case "1":
                    handleLogin();
                    break;
                case "2":
                    enrollmentManagement.registerUser();
                    break;
                case "3":
                    System.out.println("Thank you for using the Course Management System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void initialize() {
        scanner = new Scanner(System.in);
        userManagement = new UserManagement(scanner);
        courseManagement = new CourseManagement(scanner);
        enrollmentManagement = new EnrollmentManagement(userManagement, courseManagement, scanner);
    }

    private static void handleLogin() {
        User user = enrollmentManagement.login();
        if (user != null) {
            if ("admin".equals(user.getUserType())) {
                handleAdminMenu(user);
            } else {
                handleStudentMenu(user);
            }
        }
    }

    private static void handleAdminMenu(User admin) {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Add Course");
            System.out.println("4. Delete Course");
            System.out.println("5. View All Courses");
            System.out.println("6. Add Assignment");
            System.out.println("7. View Assignments");
            System.out.println("8. Delete Assignment");
            System.out.println("9. Search Students");
            System.out.println("10. Logout");
            System.out.print("Choose an option: ");

            String choice = Utils.readInput(scanner, "");
            
            switch (choice) {
                case "1":
                    userManagement.addStudent();
                    break;
                case "2":
                    userManagement.deleteStudent();
                    break;
                case "3":
                    courseManagement.addCourse();
                    break;
                case "4":
                    courseManagement.deleteCourse();
                    break;
                case "5":
                    courseManagement.displayAllCourses();
                    break;
                case "6":
                    courseManagement.addAssignment();
                    break;
                case "7":
                    System.out.print("Enter course ID to view assignments: ");
                    try {
                        int courseId = Integer.parseInt(scanner.nextLine().trim());
                        courseManagement.viewAssignments(courseId);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid course ID format!");
                    }
                    break;
                case "8":
                    courseManagement.deleteAssignment();
                    break;
                case "9":
                    userManagement.searchStudents();
                    break;
                case "10":
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void handleStudentMenu(User student) {
        while (true) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. View Available Courses");
            System.out.println("2. Enroll in Course");
            System.out.println("3. View My Courses");
            System.out.println("4. Drop Course");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            String choice = Utils.readInput(scanner, "");
            
            switch (choice) {
                case "1":
                    courseManagement.displayAllCourses();
                    break;
                case "2":
                    enrollmentManagement.enrollInCourse(student);
                    break;
                case "3":
                    enrollmentManagement.viewEnrolledCourses(student);
                    break;
                case "4":
                    enrollmentManagement.dropCourse(student);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}