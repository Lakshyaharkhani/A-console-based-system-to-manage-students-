import java.util.Scanner;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@(.+)$"
    );
    
    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^[0-9]{10}$"
    );

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    public static String readInput(Scanner scanner, String prompt) {
        if (scanner == null || prompt == null) {
            throw new IllegalArgumentException("Scanner and prompt cannot be null");
        }
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            System.out.println("\nNo more input. Exiting.");
            System.exit(0);
        }
        String input = scanner.nextLine();
        return input != null ? input.trim() : "";
    }

    public static String readInputWithValidation(Scanner scanner, String prompt, String errorMessage, ValidationFunction validator) {
        while (true) {
            String input = readInput(scanner, prompt);
            if (validator.validate(input)) {
                return input;
            }
            System.out.println(errorMessage);
        }
    }

    @FunctionalInterface
    public interface ValidationFunction {
        boolean validate(String input);
    }

    public static int generateUniqueId() {
        return (int) (System.currentTimeMillis() % 100000);
    }

    public static long generateUniqueUserId10Digits() {
        long base = System.currentTimeMillis();
        // Ensure 10 digits by taking last 10 digits and avoiding leading zeros
        long ten = 1_000_000_0000L; // 10^10
        long candidate = base % ten;
        if (candidate < 1_000_000_000L) { // ensure it is exactly 10 digits (>= 1,000,000,000)
            candidate += 1_000_000_000L;
        }
        return candidate;
    }
}