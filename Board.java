import java.util.*;
import java.util.stream.IntStream;

// Board class to manage the board state and moves
public class Board {

    private static final int SIZE = 3;
    private static final int EMPTY = 0;
    private int[] board;

    public Board() {
        this.board = new int[SIZE * SIZE]; // 1D array to simplify the code
    }

    // Provide a way to check if a move is valid
    public boolean isValidMove(int index) {
        return index >= 0 && index < SIZE * SIZE && board[index] == EMPTY;
    }

    // Make move on the board
    public void makeMove(int index, int player) {
        if (isValidMove(index)) {
            board[index] = player;
        }
    }

    // Undo a move
    public void undoMove(int index) {
        board[index] = EMPTY;
    }

    // Check if the game is a draw
    public boolean isDraw() {
        return Arrays.stream(board).allMatch(spot -> spot != EMPTY);
    }

    // Check if the game is over (win or draw)
    public boolean isGameOver() {
        return hasWinner(1) || hasWinner(-1) || isDraw();
    }

    // Check for a winning situation
    public boolean hasWinner(int player) {
        // Rows and columns
        for (int i = 0; i < SIZE; i++) {
            if ((board[i * SIZE] == player && board[i * SIZE + 1] == player && board[i * SIZE + 2] == player) ||
                (board[i] == player && board[i + SIZE] == player && board[i + 2 * SIZE] == player)) {
                return true;
            }
        }
        // Diagonals
        if ((board[0] == player && board[4] == player && board[8] == player) ||
            (board[2] == player && board[4] == player && board[6] == player)) {
            return true;
        }
        return false;
    }

    // Print the current state of the board
    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            if (i % SIZE == 0 && i != 0) {
                System.out.println();
            }
            String mark = board[i] == EMPTY ? "_" : (board[i] == 1 ? "X" : "O");
            System.out.print(mark + " ");
        }
        System.out.println();
    }

    // Get all available moves
    public int[] availableMoves() {
        return IntStream.range(0, board.length).filter(i -> board[i] == EMPTY).toArray();
    }

    // Get the value of a position on the board
    public int get(int index) {
        return board[index];
    }

    // Get variable board size
    public int getSize() {
        return SIZE;
    }
}