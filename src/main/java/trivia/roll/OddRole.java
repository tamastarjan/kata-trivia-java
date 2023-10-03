package trivia.roll;

import trivia.Board;
import trivia.player.Players;
import trivia.question.Questions;

import static trivia.roll.Roll.isOdd;

class OddRole implements Roll {

  private final BaseRoll baseRoll;

  OddRole(int value) {
    if (!isOdd(value)) {
      throw new IllegalArgumentException("value must be odd");
    }
    this.baseRoll = new BaseRoll(value);
  }

  @Override
  public void applyTo(Players players, Board board, Questions questions) {
    if (players.isCurrentPlayerInPenaltyBox()) {
      System.out.println(players.getCurrentPlayerName() + " is getting out of the penalty box");
      players.removeCurrentPlayerFromPenaltyBox();
    }
    baseRoll.applyTo(players, board, questions);
  }
}
