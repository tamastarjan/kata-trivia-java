package trivia;

import trivia.player.Player;
import trivia.question.QuestionCategoryName;

import java.util.Arrays;
import java.util.List;

public class Board {

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

  public void movePlayer(Player player, int roll) {
    int newPosition = player.getPositionOnBoard() + roll;
    while (newPosition > positionToCategory.length - 1) {
      newPosition = newPosition - positionToCategory.length;
    }
    player.setPositionOnBoard(newPosition);

    System.out.println(player.getName()
        + "'s new location is "
        + player.getPositionOnBoard());
    System.out.println("The category is " + getCategoryName(player.getPositionOnBoard()));
  }
}
