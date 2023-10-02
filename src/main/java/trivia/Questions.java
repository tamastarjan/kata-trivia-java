package trivia;

import java.util.HashMap;
import java.util.Map;

class Questions {

  private Map<String, QuestionCategory> categoryToQuestionList = new HashMap<>();

  public Questions(QuestionCategory... questionLists) {
    for (QuestionCategory questionList : questionLists) {
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
