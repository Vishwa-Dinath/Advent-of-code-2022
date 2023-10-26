package day2;

public enum Score {
    WON(6),
    DRAW(3),
    LOST(0),
    P_ROCK(1),
    P_PAPER(2),
    P_SCISSOR(3);

    private final int score;
    Score(int score) {
       this.score = score;
    }

    public int score() {
        return score;
    }
}
