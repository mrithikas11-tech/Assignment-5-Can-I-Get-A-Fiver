import java.io.StringReader; 
import java.util.Scanner; 

public class UnitTest {

    // Sign enum tests
    static void testSignValues(){
        assert Sign.ROCK.getValue()==0: "ROCK should have value 0";
        assert Sign.PAPER.getValue()==1: "PAPER should have value 1";
        assert Sign.SCISSORS.getValue()==2: "SCISSORS should have value 2";
        System.out.println("PASSED: testSignValues");
     }

    // Computer tests
    static void testComputerChoiceNotNull(){
        Computer comp = new Computer(new RandomStrategy(42));
        Sign choice=comp.makeChoice();
        assert choice !=null: "Computer makeChoice should not return null";
        System.out.println("PASSED: testComputerChoiceNotNull");
    }

    static void testComputerChoiceInRange(){
        Computer comp = new Computer (new RandomStrategy(7));
        for(int i = 0; i<50;i++){
            Sign choice = comp.makeChoice();
            int val = choice.getValue();
            assert val>=0 && val<=2: "Computer choice value should be 0, 1, or 2 but got " + val;
        }
        System.out.println("PASSED: testComputerChoiceInRange (50 rounds)");
    }

    // Player class tests
    static void testPlayerName(){
        Computer comp= new Computer(new RandomStrategy());
        assert "Computer".equals(comp.getPlayerName()): "Computer player name should be 'Computer'";
        System.out.println("PASSED: testPlayerName");
    }

    static void testWinTracking(){
        Computer comp = new Computer(new RandomStrategy());
        assert comp.getWins()==0: "Initial wins should be 0";
        comp.addWin();
        assert comp.getWins()==1: "Wins should be 1 after addWin()";
        comp.addWin();
        comp.addWin();
        assert comp.getWins()==3: "Wins should be 3 after three addWin()'s";
        System.out.println("PASSED: testPlayerWinTracking");
    }

 
    static void testHumanInput() {
        StringReader fakeInput = new StringReader("1"); 
        Scanner scnr = new Scanner(fakeInput); 
        HumanInput human = new HumanInput(scnr); 
         int result = human.inputThrow(true); 

        assert result == 1 : "Human input should have been 1 if they player entered in 1"; 

        System.out.println("PASSED: testHumanInput"); 
    }

    // Random Strategy Tests
    static void testRandomStrategyNotNull() {
        Strategy strategy = new RandomStrategy(42);
        Sign choice = strategy.makeMove();
        assert choice != null : "RandomStrategy makeMove should not return null";
        System.out.println("PASSED: testRandomStrategyNotNull");
    }

    static void testRandomStrategyInRange() {
        Strategy strategy = new RandomStrategy(7);
        for (int i = 0; i < 50; i++) {
            Sign choice = strategy.makeMove();
            int val = choice.getValue();
            assert val >= 0 && val <= 2 : "Choice value should be 0-2, got " + val;
        }
        System.out.println("PASSED: testRandomStrategyInRange (50 rounds)");
    }

    static void testRandomStrategyWithSeed() {
        // Two strategies with same seed should produce same sequence
        RandomStrategy strat1 = new RandomStrategy(123);
        RandomStrategy strat2 = new RandomStrategy(123);
        for (int i = 0; i < 20; i++) {
            Sign s1 = strat1.makeMove();
            Sign s2 = strat2.makeMove();
            assert s1 == s2 : "Same seed should produce same sequence at round " + i;
        }
        System.out.println("PASSED: testRandomStrategyWithSeed");
    }

    // ML Tests
    static void testMLStrategyFallsBackToRandom() {
        // With no round history, MLStrategy should fall back to random
        MLStrategy ml = new MLStrategy(42);
        Sign choice = ml.makeMove();
        assert choice != null : "MLStrategy should return a valid Sign even with no history";
        int val = choice.getValue();
        assert val >= 0 && val <= 2 : "MLStrategy choice should be valid";
        System.out.println("PASSED: testMLStrategyFallsBackToRandom");
    }

    static void testMLStrategyImplementsStrategy() {
        // Verify MLStrategy can be used anywhere Strategy is expected
        Strategy strategy = new MLStrategy(42);
        Sign choice = strategy.makeMove();
        assert choice != null : "MLStrategy via Strategy interface should work";
        System.out.println("PASSED: testMLStrategyImplementsStrategy");
    }

     static void testStrategySwap() {
        Strategy randomStrat = new RandomStrategy(42);
        Computer computerRandom = new Computer(randomStrat);
        Sign choice1 = computerRandom.makeChoice();
        assert choice1 != null : "Computer with RandomStrategy should make a choice";

        Strategy mlStrat = new MLStrategy(42);
        Computer computerML = new Computer(mlStrat);
        Sign choice2 = computerML.makeChoice();
        assert choice2 != null : "Computer with MLStrategy should make a choice";

        assert computerRandom.getStrategy() instanceof RandomStrategy
            : "First computer should use RandomStrategy";
        assert computerML.getStrategy() instanceof MLStrategy
            : "Second computer should use MLStrategy";
        System.out.println("PASSED: testStrategySwap");
    }


    public static void main(String[] args) {
        System.out.println("Running tests: ...");
        System.out.println();

        testSignValues();
        testComputerChoiceNotNull();
        testComputerChoiceInRange();
        testPlayerName();
        testWinTracking();
        testHumanInput();
        testRandomStrategyNotNull();
        testRandomStrategyInRange();
        testRandomStrategyWithSeed();
        testMLStrategyFallsBackToRandom();
        testMLStrategyImplementsStrategy();
        testStrategySwap();

        System.out.println();
        System.out.println("All tests passed!");

    }
}





