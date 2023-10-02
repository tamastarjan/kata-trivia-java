package trivia;

import java.util.ArrayList;
import java.util.List;

// REFACTOR ME
public class GameBetter implements IGame {

  private List<Player> players = new ArrayList<>();
  
  private Player currentPlayer;

  private QuestionBank questionBank = new QuestionBank(
      QuestionList.create("Pop", 50),
      QuestionList.create("Science", 50),
      QuestionList.create("Sports", 50),
      QuestionList.create("Rock", 50));

  boolean isGettingOutOfPenaltyBox;

  private String[] positionToQuestionCategory = new String[]{
      "Pop",
      "Science",
      "Sports",
      "Rock",
      "Pop",
      "Science",
      "Sports",
      "Rock",
      "Pop",
      "Science",
      "Sports",
      "Rock"
  };

  public boolean add(String playerName) {
    players.add(Player.create(playerName));
    if (players.size() == 1) {
      currentPlayer = players.get(0);
    }

    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.size());
    return true;
  }

  public int howManyPlayers() {
    return players.size();
  }

  public void roll(int roll) {
    System.out.println(currentPlayer.getName() + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (currentPlayer.isNotInPenaltyBox()) {
      regularRoll(roll);
    } else if (roll % 2 != 0) {
      isGettingOutOfPenaltyBox = true;

      System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
      regularRoll(roll);
    } else {
      System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
      isGettingOutOfPenaltyBox = false;
    }
  }

  private void regularRoll(int roll) {
    int newPosition = currentPlayer.getPositionOnBoard() + roll;
    if (newPosition > 11) {
      newPosition = newPosition - 12;
    }
    currentPlayer.setPositionOnBoard(newPosition);

    System.out.println(currentPlayer.getName()
        + "'s new location is "
        + currentPlayer.getPositionOnBoard());
    System.out.println("The category is " + currentCategory());
    askQuestion();
  }

  private void askQuestion() {
    questionBank.askQuestion(currentCategory());
  }

  private String currentCategory() {
    return positionToQuestionCategory[currentPlayer.getPositionOnBoard()];
  }

  public boolean wasCorrectlyAnswered() {
    if (currentPlayer.isNotInPenaltyBox()) {
      System.out.println("Answer was correct!!!!");
      addCoinToCurrentPlayerPurse();
      printCurrentPlayerCoins();

      boolean notAWinner = notAWinner();
      endTurn();

      return notAWinner;
    }

    if (!isGettingOutOfPenaltyBox) {
      endTurn();
      return true;
    }

    System.out.println("Answer was correct!!!!");
    addCoinToCurrentPlayerPurse();
    printCurrentPlayerCoins();

    boolean notAWinner = notAWinner();
    endTurn();

    return notAWinner;
  }

  private void addCoinToCurrentPlayerPurse() {
    currentPlayer.giveCoin();
  }

  private void printCurrentPlayerCoins() {
    System.out.println(currentPlayer.getName()
        + " now has "
        + currentPlayer.getCoins()
        + " Gold Coins.");
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(currentPlayer.getName() + " was sent to the penalty box");
    currentPlayer.putInPenaltyBox();

    endTurn();
    return true;
  }

  private boolean notAWinner() {
    return currentPlayer.getCoins() != 6;
  }

  private void endTurn() {
    players.add(players.remove(0));
    currentPlayer = players.get(0);
  }
}
