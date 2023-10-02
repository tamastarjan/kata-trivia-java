package trivia;

// REFACTOR ME
public class GameBetter implements IGame {

  private Players players = Players.create();

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
    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.count());
    return true;
  }

  public void roll(int roll) {
    System.out.println(players.getCurrentPlayerName() + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (!players.isCurrentPlayerInPenaltyBox()) {
      regularRoll(roll);
    } else if (roll % 2 != 0) {
      isGettingOutOfPenaltyBox = true;

      System.out.println(players.getCurrentPlayerName() + " is getting out of the penalty box");
      regularRoll(roll);
    } else {
      System.out.println(players.getCurrentPlayerName() + " is not getting out of the penalty box");
      isGettingOutOfPenaltyBox = false;
    }
  }

  private void regularRoll(int roll) {
    int newPosition = players.getCurrentPlayerPosition() + roll;
    if (newPosition > 11) {
      newPosition = newPosition - 12;
    }
    players.setCurrentPlayerPosition(newPosition);

    System.out.println(players.getCurrentPlayerName()
        + "'s new location is "
        + players.getCurrentPlayerPosition());
    System.out.println("The category is " + currentCategory());
    askQuestion();
  }

  private void askQuestion() {
    questionBank.askQuestion(currentCategory());
  }

  private String currentCategory() {
    return positionToQuestionCategory[players.getCurrentPlayerPosition()];
  }

  public boolean wasCorrectlyAnswered() {
    if (!players.isCurrentPlayerInPenaltyBox()) {
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
    players.giveCoinToCurrentPlayer();
  }

  private void printCurrentPlayerCoins() {
    System.out.println(players.getCurrentPlayerName()
        + " now has "
        + players.getCurrentPlayerCoins()
        + " Gold Coins.");
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(players.getCurrentPlayerName() + " was sent to the penalty box");
    players.putCurrentPlayerInPenaltyBox();

    endTurn();
    return true;
  }

  private boolean notAWinner() {
    return players.getCurrentPlayerCoins() != 6;
  }

  private void endTurn() {
    players.endTurn();
  }
}
