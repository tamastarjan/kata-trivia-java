package trivia;

class Player {

  private final String name;

  private int positionOnBoard = 0;

  private int coins = 0;

  private boolean inPenaltyBox = false;

  private Player(String name) {
    this.name = name;
  }

  public static Player create(String playerName) {
    return new Player(playerName);
  }

  public String getName() {
    return name;
  }

  public int getPositionOnBoard() {
    return positionOnBoard;
  }

  public void setPositionOnBoard(int positionOnBoard) {
    this.positionOnBoard = positionOnBoard;
  }

  public void giveCoin() {
    coins++;
  }

  public int getCoins() {
    return coins;
  }

  public void putInPenaltyBox() {
    inPenaltyBox = true;
  }

  public boolean isInPenaltyBox() {
    return inPenaltyBox;
  }

  public void removeFromPenaltyBox() {
    inPenaltyBox = false;
  }
}
