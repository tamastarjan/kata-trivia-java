package trivia;

class QuestionCategory {

  private String categoryName;

  private int numberOfQuestions;

  private int currentQuestionIndex;

  private QuestionCategory() {
  }

  public static QuestionCategory create(String categoryName, int numberOfQuestions) {
    QuestionCategory questionList = new QuestionCategory();
    questionList.categoryName = categoryName;
    questionList.numberOfQuestions = numberOfQuestions;
    questionList.currentQuestionIndex = 0;
    return questionList;
  }

  public String next() {
    String question = categoryName + " Question " + currentQuestionIndex;
    currentQuestionIndex++;
    if (currentQuestionIndex > numberOfQuestions) {
      throw new IllegalStateException("No more questions in this category");
    }

    return question;
  }

  public String getCategoryName() {
    return categoryName;
  }
}
