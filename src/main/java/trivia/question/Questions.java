package trivia.question;

import java.util.HashMap;
import java.util.Map;

public class Questions {

  private Map<QuestionCategoryName, QuestionCategory> nameToCategory = new HashMap<>();

  private Questions() {
    for (QuestionCategoryName category : QuestionCategoryName.values()) {
      nameToCategory.put(category, category.createQuestionList());
    }
  }

  public static Questions create() {
    return new Questions();
  }

  public String nextQuestion(QuestionCategoryName category) {
    if (!nameToCategory.containsKey(category)) {
      throw new IllegalArgumentException("Unknown category: " + category);
    }

    var questionList = nameToCategory.get(category);
    return questionList.next();
  }
}
