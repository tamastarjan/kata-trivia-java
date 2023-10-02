package trivia;

import java.util.HashMap;
import java.util.Map;

class QuestionBank {

  private Map<String, QuestionList> categoryToQuestionList = new HashMap<>();

  public QuestionBank(QuestionList... questionLists) {
    for (QuestionList questionList : questionLists) {
      categoryToQuestionList.put(questionList.getCategoryName(), questionList);
    }
  }

  public void askQuestion(String category) {
    if (!categoryToQuestionList.containsKey(category)) {
      throw new IllegalArgumentException("Unknown category: " + category);
    }

    var questionList = categoryToQuestionList.get(category);
    var question = questionList.next();

    System.out.println(question);
  }
}
