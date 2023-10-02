package trivia;

import java.util.ArrayList;
import java.util.List;

class Players {

  private List<Player> players = new ArrayList<>();

  private Players() {
  }

  public static Players create() {
    return new Players();
  }

  public void add(Player player) {
    players.add(player);
  }

  public int count() {
    return players.size();
  }

  public String getCurrentPlayerName() {
    return getCurrentPlayer().getName();
  }

  public Player getCurrentPlayer() {
    return players.get(0);
  }

  public boolean isCurrentPlayerInPenaltyBox() {
    return getCurrentPlayer().isInPenaltyBox();
  }

  public int getCurrentPlayerPosition() {
    return getCurrentPlayer().getPositionOnBoard();
  }

  public void setCurrentPlayerPosition(int newPosition) {
    getCurrentPlayer().setPositionOnBoard(newPosition);
  }

  public void giveCoinToCurrentPlayer() {
    getCurrentPlayer().giveCoin();
  }

  public int getCurrentPlayerCoins() {
    return getCurrentPlayer().getCoins();
  }

  public void putCurrentPlayerInPenaltyBox() {
    getCurrentPlayer().putInPenaltyBox();
  }

  public void endTurn() {
    players.add(players.remove(0));
  }

  public boolean isNotAWinner() {
    return getCurrentPlayerCoins() != 6;
  }
}
