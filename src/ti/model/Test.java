package ti.model;

import java.util.Date;

public class Test {
    private int questionId;
    private int testId;
    private int questNum;
    private String testName;
    private int  points;
    private Date odData;
    private Date doData;
    private int ileMin;

    private String question;

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    private Boolean correct1;
    private Boolean correct2;
    private Boolean correct3;
    private Boolean correct4;

    public Test(int questionId, int testId, String testName, String question, int points, Date odData, Date doData, int ileMin, String answer1, String answer2, String answer3, String answer4, Boolean correct1, Boolean correct2, Boolean correct3, Boolean correct4) {
        this.questionId = questionId;
        this.testId = testId;
        this.testName = testName;
        this.question = question;
        this.points = points;
        this.odData = odData;
        this.doData = doData;
        this.ileMin = ileMin;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correct1 = correct1;
        this.correct2 = correct2;
        this.correct3 = correct3;
        this.correct4 = correct4;
    }

    public Test(int questionId, int testId, String testName, String question, int points, String answer1, String answer2, String answer3, String answer4, Boolean correct1, Boolean correct2, Boolean correct3, Boolean correct4) {
        this.questionId = questionId;
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

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
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

    public int getQuestNum() {
        return questNum;
    }

    public void setQuestNum(int questNum) {
        this.questNum = questNum;
    }

    public Date getOdData() {
        return odData;
    }

    public void setOdData(Date odData) {
        this.odData = odData;
    }

    public Date getDoData() {
        return doData;
    }

    public void setDoData(Date doData) {
        this.doData = doData;
    }

    public int getIleMin() {
        return ileMin;
    }

    public void setIleMin(int ileMin) {
        this.ileMin = ileMin;
    }


    public static final class TestBuilder {
        private int questId;
        private int testId;
        private int questNum;
        private String testName;
        private String question;
        private int  points;
        private Date odData;
        private Date doData;
        private int ileMin;
        private String answer1;
        private String answer2;
        private String answer3;
        private String answer4;
        private Boolean correct1;
        private Boolean correct2;
        private Boolean correct3;
        private Boolean correct4;

        TestBuilder() {
        }

        public static TestBuilder aTest() {
            return new TestBuilder();
        }

        public TestBuilder withQuestId(int questId) {
            this.questId = questId;
            return this;
        }

        public TestBuilder withTestId(int testId) {
            this.testId = testId;
            return this;
        }

        public TestBuilder withQuestNum(int questNum) {
            this.questNum = questNum;
            return this;
        }

        public TestBuilder withTestName(String testName) {
            this.testName = testName;
            return this;
        }

        public TestBuilder withQuestion(String question) {
            this.question = question;
            return this;
        }

        public TestBuilder withPoints(int points) {
            this.points = points;
            return this;
        }

        public TestBuilder withOdData(Date odData) {
            this.odData = odData;
            return this;
        }

        public TestBuilder withDoData(Date doData) {
            this.doData = doData;
            return this;
        }

        public TestBuilder withIleMin(int ileMin) {
            this.ileMin = ileMin;
            return this;
        }

        public TestBuilder withAnswer1(String answer1) {
            this.answer1 = answer1;
            return this;
        }

        public TestBuilder withAnswer2(String answer2) {
            this.answer2 = answer2;
            return this;
        }

        public TestBuilder withAnswer3(String answer3) {
            this.answer3 = answer3;
            return this;
        }

        public TestBuilder withAnswer4(String answer4) {
            this.answer4 = answer4;
            return this;
        }

        public TestBuilder withCorrect1(Boolean correct1) {
            this.correct1 = correct1;
            return this;
        }

        public TestBuilder withCorrect2(Boolean correct2) {
            this.correct2 = correct2;
            return this;
        }

        public TestBuilder withCorrect3(Boolean correct3) {
            this.correct3 = correct3;
            return this;
        }

        public TestBuilder withCorrect4(Boolean correct4) {
            this.correct4 = correct4;
            return this;
        }

        public Test build() {
            Test test = new Test(questId, testId, testName, question, points, answer1, answer2, answer3, answer4, correct1, correct2, correct3, correct4);
            test.setQuestNum(questNum);
            test.setOdData(odData);
            test.setDoData(doData);
            test.setIleMin(ileMin);
            return test;
        }
    }
}
