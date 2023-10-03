package trivia.alternative;

import trivia.Board;
import trivia.player.Players;
import trivia.question.Questions;

class AskQuestion implements RollRule {

  @Override
  public void applyTo(int rollValue, Players players, Board board, Questions questions) {
    if (players.isCurrentPlayerInPenaltyBox()) {
      return;
    }

    askQuestion(questions, board, players);
  }

  private void askQuestion(Questions questions, Board board, Players players) {
    var category = board.getCategoryName(players.getCurrentPlayerPosition());
    var question = questions.nextQuestion(category);

    System.out.println(question);
  }
}
