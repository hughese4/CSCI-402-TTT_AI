
// board setup and mechanics
public class Board {

    private int[][] board;

    // constructor
    public Board(int size) {
        this.board = new int[size][size];
    }
    
    public int[][] getBoard() {
        return board;
    }

    public int[][] updateBoard(int[][] currentBoard, int moveIndex, int currentPlayer) { // maybe make move index a tuple for a 2d board
        int row = moveIndex / 3;
        int col = moveIndex % 3;
        currentBoard[row][col] = currentPlayer;
        return currentBoard;
    }

    public void printBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
