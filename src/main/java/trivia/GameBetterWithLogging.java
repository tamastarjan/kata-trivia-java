package trivia;

// REFACTOR ME
public class GameBetterWithLogging implements IGame {

  private Questions questions = new Questions(
      QuestionCategoryName.POP.createQuestionList(),
      QuestionCategoryName.SCIENCE.createQuestionList(),
      QuestionCategoryName.SPORTS.createQuestionList(),
      QuestionCategoryName.ROCK.createQuestionList());

  private Board board = Board.create(QuestionCategoryName.POP, QuestionCategoryName.SCIENCE,
      QuestionCategoryName.SPORTS, QuestionCategoryName.ROCK);

  private Players players = Players.create();

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
    System.out.println("The category is " + getCurrentCategory());
  }

  private void askQuestion() {
    var question = questions.nextQuestion(getCurrentCategory());
    System.out.println(question);
  }

  private QuestionCategoryName getCurrentCategory() {
    return board.getCategoryName(players.getCurrentPlayerPosition());
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
