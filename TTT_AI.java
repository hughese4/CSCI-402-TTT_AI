import java.util.Random;
import java.util.Scanner;

// Main driver class for Tic Tac Toe game with AI
public class TTT_AI {

    private static int PLAYER_X = 1; // assume X is maximizer
    private static int PLAYER_O = -1; // O is minimizer

    public static String intro(Scanner scanner) {
        System.out.println("Welcome to Tic Tac Toe AI!");
        String input;
        do {
            System.out.print("Enter '1' to play or '2' for AI vs AI: ");
            input = scanner.nextLine();
        } while (!input.equals("1") && !input.equals("2"));

        return input.equals("1") ? "player" : "ai";
    }

    public static String flipCoin(Random random) {
        return random.nextBoolean() ? "heads" : "tails";
    }

    public static void playGame(String gameType, Scanner scanner, Random random) {
        Board board = new Board();
        MoveLogic moveLogic = new MoveLogic();

        int currentPlayer = flipCoin(random).equals("heads") ? PLAYER_X : PLAYER_O;
        boolean gameOver = false;

        while (!gameOver) {
            board.printBoard();

            // Player's or AI's move
            int move;
            if ((currentPlayer == PLAYER_X && gameType.equals("player")) ||
                (currentPlayer == PLAYER_O && gameType.equals("ai"))) {
                move = moveLogic.userMove(board, scanner);
            } else {
                move = moveLogic.randomMove(board); // Placeholder for AI move
                System.out.println("AI makes its move...");
            }
            board.makeMove(move, currentPlayer);

            gameOver = board.isGameOver();

            // Switch player
            currentPlayer = -currentPlayer;
        }

        board.printBoard();
        if (board.hasWinner(-currentPlayer)) {
            System.out.println(((currentPlayer == PLAYER_X) ? "X" : "O") + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}