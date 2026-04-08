
public class Computer extends Player {

    private final Strategy strategy;

    /**
     * Create a Computer with a given strategy.
     * @param strategy the algorithm to use (RandomStrategy or MLStrategy)
     */
    public Computer(Strategy strategy) {
        super.setPlayerName("Computer");
        this.strategy = strategy;
    }


    @Override
    public Sign makeChoice() {
        Sign chosen = strategy.makeMove();
        super.setCurSign(chosen);
        return chosen;
    }


    public Strategy getStrategy() {
        return strategy;
    }
}