package day2;

public enum WinRules {
    O_ROCK("P_PAPER"),
    O_PAPER("P_SCISSOR"),
    O_SCISSOR("P_ROCK");

    private final String value;

    WinRules(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
