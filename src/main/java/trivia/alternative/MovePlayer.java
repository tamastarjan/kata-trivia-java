package trivia.alternative;

import trivia.Board;
import trivia.player.Players;
import trivia.question.Questions;

class MovePlayer implements RollRule {

  @Override
  public void applyTo(int rollValue, Players players, Board board, Questions questions) {
    if (players.isCurrentPlayerInPenaltyBox()) {
      return;
    }

    board.movePlayer(players.getCurrentPlayer(), rollValue);
    
    System.out.println(players.getCurrentPlayerName()
        + "'s new location is "
        + players.getCurrentPlayerPosition());
    System.out.println("The category is " + board.getCategoryName(players.getCurrentPlayerPosition()));
  }
}
