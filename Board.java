
public class Board {

    private static final int SIZE = 3;
    private int[][] board;

    // constructor
    public Board() {
        this.board = new int[SIZE][SIZE];
    }
    
    // constructor for cloning the board
    private Board(int[][] state) {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(state[i], 0, this.board[i], 0, SIZE);
        }
    }

    public int[][] getBoard() {
        return this.board;
    }

    // This creates a new Board instance with the updated move
    public Board updateBoard(int moveIndex, int currentPlayer) {
        int row = moveIndex / SIZE;
        int col = moveIndex % SIZE;
        Board newBoard = new Board(this.board);
        newBoard.board[row][col] = currentPlayer;
        return newBoard;
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int eval() {
        if(isWinner(1))
            return 1;
        else if(isWinner(2))
            return -1;
        else
            return 0;
    }

    private boolean isWinner(int player) {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] == player) ||
                (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] == player)) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == player) ||
            (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == player)) {
            return true;
        }

        return false;
    }
}
