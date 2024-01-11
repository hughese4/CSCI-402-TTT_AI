import java.util.*;

// driver class for the Tic Tac Toe AI
public class TTT_AI {

    public String intro() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to E's tic tac toe AI!");
        System.out.println("This AI is unbeatable, so good luck!");
        System.out.println("Would you like Tbot to play against you or another bot?");
        
        boolean cont = true;
        // input checking, figure out how to handle all bad inputs later
        while (cont) {
            System.out.println("Enter 1 to play against Tbot or 2 for bot v bot");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("You have chosen to play against Tbot!");
                System.out.println("A 'coin' will be flipped to determine who goes first");
                sc.close();
                cont = false;
                return "player";
            } else if (choice == 2) {
                System.out.println("You have chosen to watch two bots play against each other!");
                System.out.println("A 'coin' will be flipped to determine who goes first");
                sc.close();
                cont = false;
                return "bot";
            } else {
                System.out.println("Wrong input, try again.");
                cont = true;
            }
        }
        return null;
    }

    public String flipCoin() {
        // Create a Random object
        Random random = new Random();

        // Generate a random number (0 or 1)
        int randomNumber = random.nextInt(2);

        // Display the result based on the random number
        if (randomNumber == 0) {
            System.out.println("Heads");
            return "heads";
        } else {
            System.out.println("Tails");
            return "tails";
        }
    }

    // Gameplay loop
    public void playGame(String typeGame) {
        Board board = new Board(3);
        MoveLogic moveLogic = new MoveLogic();
        TTT_AI ttt = new TTT_AI();
        
        String coinResult = ttt.flipCoin();
        String firstPlayer = "";

        // game type
        if (typeGame.equals("bot")) {
            firstPlayer = moveLogic.botvBot(coinResult);
        } else {
            firstPlayer = moveLogic.playervBot(coinResult, board);
        }
        
        // initialize and display board
        
        int[] posLeft = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        // assign 1 to first player, -1 to other player
        int currentPlayer = 1;
        int otherPlayer = -1;

        boolean gameover = false;
        int userMove = (Integer) null; // might want to change these later
        int botMove = (Integer) null;
        String win = "";
        
        if (firstPlayer.equals("player")){
            // game loop
            while (!gameover) {
                board.printBoard();
                // Player's turn
                userMove = moveLogic.userMove(posLeft);
                board = board.updateBoard(board, userMove, currentPlayer);
                posLeft = moveLogic.updatePosLeft(posLeft, userMove);
                win = board.checkWin(board); // oop out
                if (win != "no win") {
                    gameover = true;
                    System.out.println(win + " wins!");
                    break;
                }

                // Bot's turn
                board.printBoard();
                botMove = moveLogic.randomBotMove(posLeft);
                board = board.updateBoard(board, botMove, otherPlayer);
                posLeft = moveLogic.updatePosLeft(posLeft, botMove);
                win = board.checkWin(board); // oop out
                if (win != "no win") {
                    gameover = true;
                    System.out.println(win + " wins!");
                    break;
                }
            }
        } else if (firstPlayer.equals("bot")) {
            // game loop
            while (!gameover) {
                botMove = moveLogic.randomBotMove(posLeft);
                userMove = moveLogic.userMove(posLeft);
            }
        }
    }

    public static void main(String[] args) {
        TTT_AI ttt = new TTT_AI();
        String typeGame = ttt.intro();
        ttt.playGame(typeGame);
    }
}