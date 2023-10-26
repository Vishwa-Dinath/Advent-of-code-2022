package day2;

public enum DrawRules {

    O_ROCK("P_ROCK"),
    O_PAPER("P_PAPER"),
    O_SCISSOR("P_SCISSOR");

    private final String value;

    DrawRules(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
