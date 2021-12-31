package TP2.Fractals;

public class LSystem {
    private String sequence;
    private final Rule[] ruleset;
    private int generation;

    public LSystem(String axiom, Rule[] ruleset) {
        this.sequence = axiom;
        this.ruleset = ruleset;
        this.generation = 0;
    }

    public String getSequence() {
        return sequence;
    }

    public int getGeneration() {
        return generation;
    }

    public void nextGeneration() {
        generation++;
        StringBuilder nextGen = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            String replace = String.valueOf(c);
            for (Rule rule : ruleset) {
                if (c == rule.getSymbol()) {
                    replace = rule.getString();
                    break;
                }
            }
            nextGen.append(replace);
        }
        sequence = nextGen.toString();
    }
}
