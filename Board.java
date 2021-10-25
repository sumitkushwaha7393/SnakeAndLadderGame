import java.util.List;

public class Board {
    List<Snake> snakes;
    List<Ladder> ladders;
    int startPosition;
    int destinationPosition;


    public Board(List<Snake> snakes, List<Ladder> ladders, int startPosition, int destinationPosition) {
        this.snakes = snakes;
        this.ladders = ladders;
        this.startPosition = startPosition;
        this.destinationPosition = destinationPosition;
    }
}
