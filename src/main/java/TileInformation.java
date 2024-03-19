public class TileInformation {
    private Character symbol;
    private String name;
    private Boolean value;
    private int difficulty;

    public TileInformation(Character symbol, String name, Boolean value) {
        this.symbol = symbol;
        this.name = name;
        this.value = value;
    }
    public TileInformation(Character symbol, String name, Boolean value, int difficulty) {
        this.symbol = symbol;
        this.name = name;
        this.value = value;
        this.difficulty = difficulty;
    }
    public int getDifficulty(){
        return difficulty;
    }

    public Character getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "{" +
                "\"tile\": \"" + symbol + "\",\n" +
                "\"info\": {\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"baseInformation\": {\n" +
                "    \"solid\": " + value + "\n" +
                "  }\n" +
                "}}";
    }
}
