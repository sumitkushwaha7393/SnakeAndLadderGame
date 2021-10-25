import java.util.Random;

public class Dice {
    int numberOfDices;

    public Dice(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public int rollDice() {
        int res = new Random().nextInt(1*this.numberOfDices, 6*this.numberOfDices+1);
        return res;
    }
}
