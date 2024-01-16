public class Board {

    private static final int SIZE = 3;
    private int[][] board;

    // constructor
    public Board() {
        this.board = new int[SIZE][SIZE];
    }
    
    public int[][] getBoard() {
        return this.board;
    }

    public Board updateBoard(int moveIndex, int currentPlayer) {
        int row = moveIndex / SIZE;
        int col = moveIndex % SIZE;
        this.board[row][col] = currentPlayer;
        return this;
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public String checkWin(int[] posLeft) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < SIZE; i++) {
            String rowResult = checkLine(board[i][0], board[i][1], board[i][2]);
            if (!rowResult.equals("no win")) {
                return rowResult;
            }

            String colResult = checkLine(board[0][i], board[1][i], board[2][i]);
            if (!colResult.equals("no win")) {
                return colResult;
            }
        }

        String diagonalResult = checkLine(board[0][0], board[1][1], board[2][2]);
        if (!diagonalResult.equals("no win")) {
            return diagonalResult;
        }

        if (posLeft.length == 0) {
            return "tie";
        }
        return "no win";
    }

    private String checkLine(int cell1, int cell2, int cell3) {
        int sum = cell1 + cell2 + cell3;
        if (Math.abs(sum) == SIZE) {
            return (sum > 0) ? "player" : "bot";
        }
        return "no win";
    }
}
