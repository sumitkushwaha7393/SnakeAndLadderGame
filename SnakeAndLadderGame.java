package src;
import java.util.*;

public class SnakeAndLadderGame {
    Queue<Player> turn;
    Dice dice;
    Board board;
    public static List<Ladder> ladders = new ArrayList<>();
    public static List<Snake> snakes = new ArrayList<>();
    HashMap<Player, Integer> currentGameSituation = new HashMap<>();
    public SnakeAndLadderGame(int noOfDices, List<Player> players) {
        dice = new Dice(noOfDices);
        turn = new ArrayDeque<>();
        board = new Board(snakes, ladders, 1, 100);
        initializeLaddersAndSnakes();
        for(Player player: players) {
            turn.add(player);
            currentGameSituation.put(player, board.startPosition);
        }
    }


    // HARD CODED VALUES as user shouldn't have any access to those
    private void initializeLaddersAndSnakes() {
        snakes.add(new Snake(40,10));
        snakes.add(new Snake(99,20));
        ladders.add(new Ladder(10,21));
        ladders.add(new Ladder(40,80));
    }


    public void startGame() {
        int currentPosition = 0;
        // Acts as an infinite loop until somone wins the GAME
        while (!turn.isEmpty()) {
            Player currentPlayer = turn.poll();
            currentPosition = currentGameSituation.get(currentPlayer);
            System.out.println("Player "+ currentPlayer.id + " who is at position: " + currentPosition +
                    " making the move");
            int rolledDiceValue = dice.rollDice();
            System.out.println("Player "+ currentPlayer.id + " got " + rolledDiceValue + " moves");
            int move = rolledDiceValue + currentPosition;
            
            // currentPlayer won, so print and break the infinite loop
            if(move == 100) {
                System.out.println("Player " + currentPlayer.id + " won......wohoooo");
                break;
            }
            turn.add(currentPlayer);
            if(move > 100) {
                System.out.println("Player"+ currentPlayer.id + "  can't move further" );
                continue;
            } else {
                int finalMove = move;
                Optional<Ladder> ladder = ladders.stream().filter(i -> i.currentPosition == finalMove).findFirst();
                if(ladder.isPresent()) {
                    System.out.println("Ladder found at position: " + move);
                    move = ladder.get().destinationPosition;
                    currentGameSituation.put(currentPlayer, move);
                    System.out.println(currentPlayer.id + " moved to new location" + move + "via ladder");
                    continue;
                }
                Optional<Snake> snake = snakes.stream().filter(i -> i.currentPosition == finalMove).findFirst();
                if(snake.isPresent()) {
                    System.out.println("Snake found at position: " + move);
                    move = snake.get().destinationPosition;
                    currentGameSituation.put(currentPlayer, move);
                    System.out.println(currentPlayer.id + " moved to new location" + move + "because snake bite");
                    continue;
                }
                currentGameSituation.put(currentPlayer, move);
            }
        }
    }

}
