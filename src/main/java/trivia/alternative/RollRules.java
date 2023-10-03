package trivia.alternative;

import java.util.List;

public final class RollRules {

  private RollRules() {
  }
  
  public static List<RollRule> RULES = List.of(
      new GetOutOfPenaltyBox(),
      new MovePlayer(),
      new AskQuestion()
  );
}
