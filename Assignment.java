public class Assignment {
    private int assignmentId;
    private String title;
    private String description;
    private String dueDate;
    private int maxScore;
    private int courseId;

    public Assignment(int assignmentId, String title, String description, String dueDate, int maxScore, int courseId) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.maxScore = maxScore;
        this.courseId = courseId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return String.format("Assignment ID: %d, Title: %s, Due Date: %s, Max Score: %d",
                assignmentId, title, dueDate, maxScore);
    }
}