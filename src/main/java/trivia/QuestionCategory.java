package trivia;

class QuestionCategory {

  private QuestionCategoryName categoryName;

  private int numberOfQuestions;

  private int currentQuestionIndex;

  private QuestionCategory() {
  }

  public static QuestionCategory create(QuestionCategoryName categoryName, int numberOfQuestions) {
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

  public QuestionCategoryName getCategoryName() {
    return categoryName;
  }
}
