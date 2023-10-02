package trivia;

class QuestionList {

  private String categoryName;

  private int numberOfQuestions;

  private int currentQuestionIndex;

  private QuestionList() {
  }

  public static QuestionList create(String categoryName, int numberOfQuestions) {
    QuestionList questionList = new QuestionList();
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
