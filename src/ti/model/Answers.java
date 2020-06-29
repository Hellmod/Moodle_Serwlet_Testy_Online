package ti.model;

public class Answers {
    private int userId;
    private int questionId;
    private Boolean answer1;
    private Boolean answer2;
    private Boolean answer3;
    private Boolean answer4;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getAnswer1() {
        return answer1;
    }

    public void setAnswer1(Boolean answer1) {
        this.answer1 = answer1;
    }

    public Boolean getAnswer2() {
        return answer2;
    }

    public void setAnswer2(Boolean answer2) {
        this.answer2 = answer2;
    }

    public Boolean getAnswer3() {
        return answer3;
    }

    public void setAnswer3(Boolean answer3) {
        this.answer3 = answer3;
    }

    public Boolean getAnswer4() {
        return answer4;
    }

    public void setAnswer4(Boolean answer4) {
        this.answer4 = answer4;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }


    public static final class AnswersBuilder {
        private int userId;
        private int questionId;
        private Boolean answer1;
        private Boolean answer2;
        private Boolean answer3;
        private Boolean answer4;

        public AnswersBuilder() {
        }

        public static AnswersBuilder anAnswers() {
            return new AnswersBuilder();
        }

        public AnswersBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public AnswersBuilder questionId(int questionId) {
            this.questionId = questionId;
            return this;
        }

        public AnswersBuilder answer1(Boolean answer1) {
            this.answer1 = answer1;
            return this;
        }

        public AnswersBuilder answer2(Boolean answer2) {
            this.answer2 = answer2;
            return this;
        }

        public AnswersBuilder answer3(Boolean answer3) {
            this.answer3 = answer3;
            return this;
        }

        public AnswersBuilder answer4(Boolean answer4) {
            this.answer4 = answer4;
            return this;
        }

        public Answers build() {
            Answers answers = new Answers();
            answers.setUserId(userId);
            answers.setQuestionId(questionId);
            answers.setAnswer1(answer1);
            answers.setAnswer2(answer2);
            answers.setAnswer3(answer3);
            answers.setAnswer4(answer4);
            return answers;
        }
    }
}
