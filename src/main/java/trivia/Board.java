package trivia;

import java.util.Arrays;
import java.util.List;

class Board {

  private String[] positionToQuestionCategoryName;

  private Board(List<String> questionCategoryNames) {
    positionToQuestionCategoryName = new String[questionCategoryNames.size() * 3];
    for (int i = 0; i < positionToQuestionCategoryName.length; i++) {
      positionToQuestionCategoryName[i] = questionCategoryNames.get(i % questionCategoryNames.size());
    }
  }

  public static Board create(String... questionCategoryNames) {
    return new Board(Arrays.asList(questionCategoryNames));
  }

  public String getCategoryName(int position) {
    return positionToQuestionCategoryName[position];
  }
}
