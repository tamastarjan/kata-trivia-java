package trivia.alternative;

import trivia.Board;
import trivia.player.Players;
import trivia.question.Questions;

import static trivia.roll.Roll.isOdd;

class GetOutOfPenaltyBox implements RollRule {

  public void applyTo(int rollValue, Players players, Board board, Questions questions) {
    if (!players.isCurrentPlayerInPenaltyBox()) {
      return;
    }

    if (isOdd(rollValue)) {
      System.out.println(players.getCurrentPlayerName() + " is getting out of the penalty box");
      players.removeCurrentPlayerFromPenaltyBox();
    } else {
      System.out.println(players.getCurrentPlayerName() + " is not getting out of the penalty box");
    }
  }
}
