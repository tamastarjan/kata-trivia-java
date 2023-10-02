package trivia;

// REFACTOR ME
public class GameBetterWithLogging implements IGame {

  private Players players = Players.create();

  private Questions questions = new Questions(
      QuestionCategory.create("Pop", 50),
      QuestionCategory.create("Science", 50),
      QuestionCategory.create("Sports", 50),
      QuestionCategory.create("Rock", 50));

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

    if (players.isCurrentPlayerInPenaltyBox()) {
      if (roll % 2 != 0) {
        System.out.println(players.getCurrentPlayerName() + " is getting out of the penalty box");
        players.removeCurrentPlayerFromPenaltyBox();
      } else {
        System.out.println(players.getCurrentPlayerName() + " is not getting out of the penalty box");
      }
    }

    if (!players.isCurrentPlayerInPenaltyBox()) {
      movePlayer(roll);
      askQuestion();
    }
  }

  private void movePlayer(int roll) {
    int newPosition = players.getCurrentPlayerPosition() + roll;
    if (newPosition > 11) {
      newPosition = newPosition - 12;
    }
    players.setCurrentPlayerPosition(newPosition);

    System.out.println(players.getCurrentPlayerName()
        + "'s new location is "
        + players.getCurrentPlayerPosition());
    System.out.println("The category is " + currentCategory());
  }

  private void askQuestion() {
    var question = questions.nextQuestion(currentCategory());
    System.out.println(question);
  }

  private String currentCategory() {
    return positionToQuestionCategory[players.getCurrentPlayerPosition()];
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
    players.endTurn();

    return true;
  }
}
