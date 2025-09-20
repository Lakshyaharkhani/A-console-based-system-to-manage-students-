public class CourseModel {
    private int courseId;
    private String courseName;
    private String courseDuration;

    public CourseModel(int courseId, String courseName, String courseDuration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDuration = courseDuration;
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
}