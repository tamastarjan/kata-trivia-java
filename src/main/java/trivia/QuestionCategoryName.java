package trivia;

enum QuestionCategoryName {

  POP("Pop"),
  SCIENCE("Science"),
  SPORTS("Sports"),
  ROCK("Rock");

  private final String name;

  QuestionCategoryName(String name) {
    this.name = name;
  }

  public QuestionCategory createQuestionList() {
    return QuestionCategory.create(this, 50);
  }

  @Override
  public String toString() {
    return name;
  }
}
