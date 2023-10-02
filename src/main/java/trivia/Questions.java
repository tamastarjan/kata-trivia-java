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

  public String nextQuestion(String category) {
    if (!categoryNameToCategory.containsKey(category)) {
      throw new IllegalArgumentException("Unknown category: " + category);
    }

    var questionList = categoryNameToCategory.get(category);
    return questionList.next();
  }
}
