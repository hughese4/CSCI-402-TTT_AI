
// board setup and mechanics
public class Board {

    private int[][] board;

    // constructor
    public Board(int size) {
        this.board = new int[size][size];
    }
    
    public int[][] getBoard() {
        return this.board;
    }

    public Board updateBoard(Board currentBoard, int moveIndex, int currentPlayer) { // maybe make move index a tuple for a 2d board
        int row = moveIndex / 3;
        int col = moveIndex % 3;
        currentBoard.getBoard()[row][col] = currentPlayer;
        return currentBoard;
    }

    public void printBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public String checkWin(Board currentBoard) {
        // check if row, columns, or diagonals add up to absoulte value of 3
        // if so, return the player who won

        // very inefficient soltion by copilot
        for (int i = 0; i < 3; i++) {
            if (Math.abs(currentBoard.getBoard()[i][0] + currentBoard.getBoard()[i][1] + currentBoard.getBoard()[i][2]) == 3) {
                if (currentBoard.getBoard()[i][0] == 1) {
                    return "player";
                } else {
                    return "bot";
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (Math.abs(currentBoard.getBoard()[0][i] + currentBoard.getBoard()[1][i] + currentBoard.getBoard()[2][i]) == 3) {
                if (currentBoard.getBoard()[0][i] == 1) {
                    return "player";
                } else {
                    return "bot";
                }
            }
        }
        if (Math.abs(currentBoard.getBoard()[0][0] + currentBoard.getBoard()[1][1] + currentBoard.getBoard()[2][2]) == 3) {
            if (currentBoard.getBoard()[0][0] == 1) {
                return "player";
            } else {
                return "bot";
            }
        }
        return "no win";
    }
}
