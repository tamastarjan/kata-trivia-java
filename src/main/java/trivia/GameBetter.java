package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {
  ArrayList players = new ArrayList();
  int[] places = new int[6];
  int[] purses = new int[6];
  boolean[] inPenaltyBox = new boolean[6];

  LinkedList popQuestions = new LinkedList();
  LinkedList scienceQuestions = new LinkedList();
  LinkedList sportsQuestions = new LinkedList();
  LinkedList rockQuestions = new LinkedList();

  int currentPlayer = 0;
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

  public GameBetter() {
    for (int i = 0; i < 50; i++) {
      popQuestions.addLast("Pop Question " + i);
      scienceQuestions.addLast(("Science Question " + i));
      sportsQuestions.addLast(("Sports Question " + i));
      rockQuestions.addLast(createRockQuestion(i));
    }
  }

  public String createRockQuestion(int index) {
    return "Rock Question " + index;
  }

  public boolean add(String playerName) {
    players.add(playerName);
    places[howManyPlayers()] = 0;
    purses[howManyPlayers()] = 0;
    inPenaltyBox[howManyPlayers()] = false;

    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.size());
    return true;
  }

  public int howManyPlayers() {
    return players.size();
  }

  public void roll(int roll) {
    System.out.println(players.get(currentPlayer) + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (!inPenaltyBox[currentPlayer]) {
      regularRoll(roll);
    } else if (roll % 2 != 0) {
      isGettingOutOfPenaltyBox = true;

      System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
      regularRoll(roll);
    } else {
      System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
      isGettingOutOfPenaltyBox = false;
    }
  }

  private void regularRoll(int roll) {
    places[currentPlayer] = places[currentPlayer] + roll;
    if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

    System.out.println(players.get(currentPlayer)
        + "'s new location is "
        + places[currentPlayer]);
    System.out.println("The category is " + currentCategory());
    askQuestion();
  }

  private void askQuestion() {
    if (currentCategory() == "Pop")
      System.out.println(popQuestions.removeFirst());
    if (currentCategory() == "Science")
      System.out.println(scienceQuestions.removeFirst());
    if (currentCategory() == "Sports")
      System.out.println(sportsQuestions.removeFirst());
    if (currentCategory() == "Rock")
      System.out.println(rockQuestions.removeFirst());
  }

  private String currentCategory() {
    return positionToQuestionCategory[places[currentPlayer]];
  }

  public boolean wasCorrectlyAnswered() {
    if (!inPenaltyBox[currentPlayer]) {
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
    purses[currentPlayer]++;
  }

  private void printCurrentPlayerCoins() {
    System.out.println(players.get(currentPlayer)
        + " now has "
        + purses[currentPlayer]
        + " Gold Coins.");
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
    inPenaltyBox[currentPlayer] = true;

    endTurn();
    return true;
  }

  private boolean notAWinner() {
    return purses[currentPlayer] != 6;
  }

  private void endTurn() {
    currentPlayer++;
    if (currentPlayer == players.size()) currentPlayer = 0;
  }
}
