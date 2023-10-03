package trivia.roll;

import trivia.Board;
import trivia.player.Players;
import trivia.question.Questions;

public interface Roll {

  void applyTo(Players players, Board board, Questions questions);

  static Roll create(int rollValue) {
    if (isOdd(rollValue)) {
      return new OddRole(rollValue);
    }
    return new BaseRoll(rollValue);
  }

  static boolean isOdd(int rollValue) {
    return rollValue % 2 != 0;
  }
}
