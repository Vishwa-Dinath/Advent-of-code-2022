package day2;

public enum Reference {
    A("O_ROCK"),
    B("O_PAPER"),
    C("O_SCISSOR"),
    X("P_ROCK"),
    Y("P_PAPER"),
    Z("P_SCISSOR");

    private final String value;

    Reference(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
