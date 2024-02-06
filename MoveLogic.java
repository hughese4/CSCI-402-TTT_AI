import java.util.Random;
import java.util.Scanner;

// MoveLogic class handling the logic of moves and game flow
public class MoveLogic {

    // The bot or player makes a random move from available positions
    public int randomMove(Board board) {
        int[] availableMoves = board.availableMoves();
        if (availableMoves.length == 0) {
            throw new IllegalStateException("No available moves left");
        }
        Random random = new Random();
        return availableMoves[random.nextInt(availableMoves.length)];
    }

    // User move where input is validated
    public int userMove(Board board, Scanner scanner) {
        int move = -1;
        boolean validMove = false;

        while (!validMove) {
            System.out.print("Enter a number between 0 and 8 to make your move: \n> ");
            move = scanner.nextInt();
            validMove = board.isValidMove(move);
            if (!validMove) {
                System.out.println("Invalid move, try again.");
            }
        }
        return move;
    }

    public Move negamax(Board board, int player, int depth) {
        int bestValue = Integer.MIN_VALUE;
        Move bestMove = new Move(-1);

        if (board.isGameOver() || board.isDraw() || depth == 0) {
            return new Move(-1, evaluateBoardHeuristic(board, player));
        }

        for (int move : board.availableMoves()) {
            board.makeMove(move, player); // Make the move
            int value = -negamax(board, -player, depth - 1).value;
            board.undoMove(move); // Undo the move

            if (value > bestValue) {
                bestValue = value;
                bestMove.position = move;
                bestMove.value = bestValue;
            }
        }
        return bestMove;
    }

    
    public int evaluateBoardHeuristic(Board board, int player) {
        int score = 0;
    
        // Constants representing the values of various board states
        final int WIN_SCORE = 100;
        final int CENTER_SCORE = 3;
        final int CORNER_SCORE = 2;
    
        // Check for win condition for the player
        if (board.hasWinner(player)) {
            score += WIN_SCORE;
        } else if (board.hasWinner(-player)) {
            score -= WIN_SCORE;
        }
    
        // Evaluate all rows, columns, and diagonals
        for (int i = 0; i < board.getSize(); i++) {
            score += evaluateLine(board, player, i * board.getSize(), 1); // Row
            score += evaluateLine(board, player, i, board.getSize()); // Column
        }
        score += evaluateLine(board, player, 0, board.getSize() + 1); // Main diagonal
        score += evaluateLine(board, player, board.getSize() - 1, board.getSize() - 1); // Anti-diagonal
    
        // Center
        if (board.get(board.getSize() * board.getSize() / 2) == player) {
            score += CENTER_SCORE;
        }
    
        // Corners
        int[] corners = {0, board.getSize() - 1, board.getSize() * (board.getSize() - 1), (board.getSize() * board.getSize()) - 1};
        for (int corner : corners) {
            if (board.get(corner) == player) {
                score += CORNER_SCORE;
            }
        }
    
        return score;
    }
    
    private int evaluateLine(Board board, int player, int startIndex, int step) {
        final int TWO_IN_A_ROW_SCORE = 10;
        int score = 0;
        int lineScore = 0;        
    
        for (int i = 0; i < board.getSize(); i++) {
            int value = board.get(startIndex + i * step);
            if (value == player) {
                lineScore += 1;
            } else if (value == -player) {
                lineScore -= 1;
            }
        }
    
        if (lineScore == board.getSize() - 1) { // Player is "one move from winning" on this line
            score += TWO_IN_A_ROW_SCORE;
        } else if (lineScore == -(board.getSize() - 1)) { // Opponent is "one move from winning" on this line
            score -= TWO_IN_A_ROW_SCORE;
        }
    
        return score;
    }

    private static class Move {
        int position;
        int value;

        public Move(int position) {
            this.position = position;
            this.value = 0;
        }

        public Move(int position, int value) {
            this.position = position;
            this.value = value;
        }
    }
}
