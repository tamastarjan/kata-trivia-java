package trivia.roll;

import trivia.Board;
import trivia.player.Players;
import trivia.question.Questions;

class BaseRoll implements Roll {

  private final int value;

  BaseRoll(int value) {
    this.value = value;
  }

  @Override
  public void applyTo(Players players, Board board, Questions questions) {
    if (players.isCurrentPlayerInPenaltyBox()) {
      System.out.println(players.getCurrentPlayerName() + " is not getting out of the penalty box");
      return;
    }

    board.movePlayer(players.getCurrentPlayer(), value);
    askQuestion(questions, board, players);
  }

  private void askQuestion(Questions questions, Board board, Players players) {
    var category = board.getCategoryName(players.getCurrentPlayerPosition());
    var question = questions.nextQuestion(category);

    System.out.println(question);
  }
}
