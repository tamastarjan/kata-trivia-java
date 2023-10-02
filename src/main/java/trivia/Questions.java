package trivia;

import java.util.HashMap;
import java.util.Map;

class Questions {

  private Map<String, QuestionCategory> categoryNameToCategory = new HashMap<>();

  public Questions(QuestionCategory... categories) {
    for (QuestionCategory category : categories) {
      categoryNameToCategory.put(category.getCategoryName(), category);
    }
  }

  public void askQuestion(String category) {
    if (!categoryNameToCategory.containsKey(category)) {
      throw new IllegalArgumentException("Unknown category: " + category);
    }

    var questionList = categoryNameToCategory.get(category);
    var question = questionList.next();

    System.out.println(question);
  }
}
