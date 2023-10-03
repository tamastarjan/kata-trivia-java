package trivia;

import trivia.alternative.RollRules;
import trivia.player.Player;
import trivia.player.Players;
import trivia.question.QuestionCategoryName;
import trivia.question.Questions;

// REFACTOR ME
public class GameBetter implements IGame {

  private Questions questions = Questions.create();

  private Board board = Board.create(QuestionCategoryName.POP, QuestionCategoryName.SCIENCE,
      QuestionCategoryName.SPORTS, QuestionCategoryName.ROCK);

  private Players players = Players.create();

  public boolean add(String playerName) {
    players.add(Player.create(playerName));
    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.count());
    return true;
  }

  public void roll(int rollValue) {
    System.out.println(players.getCurrentPlayerName() + " is the current player");
    System.out.println("They have rolled a " + rollValue);

    // Idea 1: Represent the roll as a strategy which handles the state mutations
    // var roll = Roll.create(rollValue);
    // roll.applyTo(players, board, questions);

    // Idea 2: Separate state mutations into rules
    for (var rule : RollRules.RULES) {
      rule.applyTo(rollValue, players, board, questions);
    }
  }


  public boolean wasCorrectlyAnswered() {
    if (players.isCurrentPlayerInPenaltyBox()) {
      players.endTurn();
      return true;
    }

    System.out.println("Answer was correct!!!!");
    addCoinToCurrentPlayerPurse();
    printCurrentPlayerCoins();

    boolean notAWinner = players.isNotAWinner();
    players.endTurn();

    return notAWinner;
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(players.getCurrentPlayerName() + " was sent to the penalty box");

    players.putCurrentPlayerInPenaltyBox();
    players.endTurn();

    return true;
  }

  private void addCoinToCurrentPlayerPurse() {
    players.giveCoinToCurrentPlayer();
  }

  private void printCurrentPlayerCoins() {
    System.out.println(players.getCurrentPlayerName()
        + " now has "
        + players.getCurrentPlayerCoins()
        + " Gold Coins.");
  }
}
