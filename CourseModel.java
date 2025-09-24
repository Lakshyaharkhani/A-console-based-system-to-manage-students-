import java.util.ArrayList;

public class CourseModel {
    private int courseId;
    private String courseName;
    private String courseDuration;
    private ArrayList<Assignment> assignments;

    public CourseModel(int courseId, String courseName, String courseDuration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.assignments = new ArrayList<>();
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof CourseModel)) return false;
        CourseModel course = (CourseModel) obj;
        return this.courseId == course.courseId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(courseId);
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId + 
               ", Name: " + courseName + 
               ", Duration: " + courseDuration;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void removeAssignment(int assignmentId) {
        assignments.removeIf(a -> a.getAssignmentId() == assignmentId);
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public Assignment findAssignment(int assignmentId) {
        return assignments.stream()
                         .filter(a -> a.getAssignmentId() == assignmentId)
                         .findFirst()
                         .orElse(null);
    }
}