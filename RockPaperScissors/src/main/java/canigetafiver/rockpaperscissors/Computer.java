package canigetafiver.rockpaperscissors;
public class Computer extends Player {

    private final Strategy strategy;
    private Sign predictedMove;

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
        predictedMove = strategy.getPredictionSign();
        super.setCurSign(chosen);
        return chosen;
    }


    public Strategy getStrategy() {
        return strategy;
    }

    @Override
    public Sign getPredictedMove(){
        return predictedMove;
    }
}