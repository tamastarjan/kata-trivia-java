package trivia;

import java.util.HashMap;
import java.util.Map;

class Questions {

  private Map<String, QuestionCategory> categoryToQuestionList = new HashMap<>();

  public Questions(QuestionCategory... categories) {
    for (QuestionCategory category : categories) {
      categoryToQuestionList.put(category.getCategoryName(), category);
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
