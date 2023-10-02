package trivia;

import java.util.Arrays;
import java.util.List;

class Board {

  private final QuestionCategoryName[] positionToCategory;

  private Board(List<QuestionCategoryName> categories) {
    positionToCategory = new QuestionCategoryName[categories.size() * 3];
    for (int i = 0; i < positionToCategory.length; i++) {
      positionToCategory[i] = categories.get(i % categories.size());
    }
  }

  public static Board create(QuestionCategoryName... categories) {
    return new Board(Arrays.asList(categories));
  }

  public QuestionCategoryName getCategoryName(int position) {
    return positionToCategory[position];
  }
}
