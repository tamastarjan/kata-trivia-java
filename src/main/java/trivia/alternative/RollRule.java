package trivia.alternative;

import trivia.Board;
import trivia.player.Players;
import trivia.question.Questions;

public interface RollRule {

  void applyTo(int rollValue, Players players, Board board, Questions questions);
}
