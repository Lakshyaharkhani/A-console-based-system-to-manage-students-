import java.util.ArrayList;

public class User {
    private int userId;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String userType;
    private ArrayList<CourseModel> courses;

    public User(int userId, String username, String email, String phone, String password, String userType) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userType = userType;
        this.courses = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public ArrayList<CourseModel> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<CourseModel> courses) {
        this.courses = courses;
    }

    public void addCourse(CourseModel course) {
        this.courses.add(course);
    }

    public void removeCourse(CourseModel course) {
        this.courses.remove(course);
    }
}