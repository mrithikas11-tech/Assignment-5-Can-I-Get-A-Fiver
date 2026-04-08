package canigetafiver.rockpaperscissors;

public class GameRPSGUI extends Game{
        private Player human;
        private Player computer;
        private int rounds = 20;
        //private final Scanner scnr;
        private final Strategy strategy;
        private final RoundSequence roundSequence;
        GameRuleGUI rules;

        public GameRPSGUI(Strategy strategy, RoundSequence roundSequence, int rounds) {

            this.strategy = strategy;
            this.roundSequence = roundSequence;
            this.rounds = rounds;
        }

        public GameRPSGUI(RoundSequence roundSequence, int rounds){
            this.strategy = new MLStrategy(roundSequence, 5);
            this.roundSequence = roundSequence;
            this.rounds = rounds;
            this.human = new HumanPlayerGUI();
            this.computer = new Computer(strategy);
            this.rules= new GameRuleGUI(human, computer);
        }

        @Override
        public void runGame() {
            human    = new HumanPlayer();
            computer = new Computer(strategy);

            if (roundSequence != null) {
                roundSequence.updatePlayers(human, computer);
            }

            GameRule rules = new GameRuleGUI(human, computer);

            for (int count = 1; count <= rounds; count++) {

                rules.getRPS();
                if (roundSequence != null) {
                    roundSequence.updateRoundSequence();
                }
            }

        }

        public void runRound(){

            if (roundSequence != null) {
                roundSequence.updatePlayers(human, computer);
            }

            GameRuleGUI rules = new GameRuleGUI(human, computer);

                rules.getRPS(human.getCurSign());

                if (roundSequence != null) {
                    roundSequence.updateRoundSequence();
                }

        }
        public Player getHumanPlayer(){
            return human;
        }
    public void runRound(Sign theSign){

        if (roundSequence != null) {
            roundSequence.updatePlayers(human, computer);
        }
        //GameRuleGUI rules = new GameRuleGUI(human, computer);
        human.setCurSign(theSign);
        rules.getRPS(human.getCurSign());

        if (roundSequence != null) {
            roundSequence.updateRoundSequence();
        }

    }


    public Player getComputer() {
        return computer;
    }
    public void humanChoice(Sign theSign){
            human.setCurSign(theSign);
    }
    public GameRuleGUI getRules(){
            return rules;
    }


}
