import java.util.Random;

public class RandomStrategy implements Strategy {

    private final Random gen;


    public RandomStrategy() {
        gen = new Random();
    }

    public RandomStrategy(int seed) {
        gen = new Random(seed);
    }

    @Override
    public Sign makeMove() {
        int result = gen.nextInt(3);
        return Sign.getSignFromInt(result);
    }
}
 
 
    

