package day2;

public enum LostRules {
    O_ROCK("P_SCISSOR"),
    O_PAPER("P_ROCK"),
    O_SCISSOR("P_PAPER");

    private final String value;

    LostRules(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
