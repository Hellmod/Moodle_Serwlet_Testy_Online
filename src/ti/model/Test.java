package ti.model;

public class Test {
    private int id;
    private int testId;
    private String testName;
    private String question;
    private int  points;

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    private Boolean correct1;
    private Boolean correct2;
    private Boolean correct3;
    private Boolean correct4;

    public Test(int id, int testId, String testName, String question, int points, String answer1, String answer2, String answer3, String answer4, Boolean correct1, Boolean correct2, Boolean correct3, Boolean correct4) {
        this.id = id;
        this.testId = testId;
        this.testName = testName;
        this.question = question;
        this.points = points;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correct1 = correct1;
        this.correct2 = correct2;
        this.correct3 = correct3;
        this.correct4 = correct4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public Boolean getCorrect1() {
        return correct1;
    }

    public void setCorrect1(Boolean correct1) {
        this.correct1 = correct1;
    }

    public Boolean getCorrect2() {
        return correct2;
    }

    public void setCorrect2(Boolean correct2) {
        this.correct2 = correct2;
    }

    public Boolean getCorrect3() {
        return correct3;
    }

    public void setCorrect3(Boolean correct3) {
        this.correct3 = correct3;
    }

    public Boolean getCorrect4() {
        return correct4;
    }

    public void setCorrect4(Boolean correct4) {
        this.correct4 = correct4;
    }
}
