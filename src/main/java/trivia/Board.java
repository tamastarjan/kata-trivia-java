package trivia;

import trivia.player.Player;
import trivia.question.QuestionCategoryName;

public class Board {

  private final QuestionCategoryName[] positionToCategory;

  private Board() {
    positionToCategory = new QuestionCategoryName[]{
        QuestionCategoryName.POP, QuestionCategoryName.SCIENCE,
        QuestionCategoryName.SPORTS, QuestionCategoryName.ROCK,

        QuestionCategoryName.POP, QuestionCategoryName.SCIENCE,
        QuestionCategoryName.SPORTS, QuestionCategoryName.ROCK,

        QuestionCategoryName.POP, QuestionCategoryName.SCIENCE,
        QuestionCategoryName.SPORTS, QuestionCategoryName.ROCK,
    };
  }

  public static Board create() {
    return new Board();
  }

  public QuestionCategoryName getCategoryName(int position) {
    return positionToCategory[position];
  }

  public void movePlayer(Player player, int roll) {
    int newPosition = player.getPositionOnBoard() + roll;
    while (newPosition > positionToCategory.length - 1) {
      newPosition = newPosition - positionToCategory.length;
    }
    player.setPositionOnBoard(newPosition);
  }
}
