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
            System.out.print("Enter 1 to play against Tbot or 2 for bot v bot\n> ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("\nYou have chosen to play against Tbot!");
                System.out.println("A 'coin' will be flipped to determine who goes first\n");
                
                cont = false;
                return "player";
            } else if (choice == 2) {
                System.out.println("You have chosen to watch two bots play against each other!");
                System.out.println("A 'coin' will be flipped to determine who goes first");
                
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
            return "heads";
        } else {
            return "tails";
        }
    }

    // Gameplay loop
    public void playGame(String typeGame) {
        Board board = new Board(3);
        MoveLogic moveLogic = new MoveLogic();
        TTT_AI ttt = new TTT_AI();
        Gameplay game = new Gameplay();
        
        String coinResult = ttt.flipCoin();
        String firstPlayer = "";

        // game type
        if (typeGame.equals("bot")) {
            firstPlayer = moveLogic.botvBot(coinResult);
            game.gameLoopBVB(board, moveLogic, ttt, firstPlayer);

        } else {
            firstPlayer = moveLogic.playervBot(coinResult, board);
            game.gameLoopPVB(board, moveLogic, ttt, firstPlayer);
        }   
    }

    public boolean checkTie(int[] posLeft, boolean gameover) {
        // check tie (oop out later)
        if (!gameover && posLeft.length == 0) {
            System.out.println("It's a tie!");
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        TTT_AI ttt = new TTT_AI();
        String typeGame = ttt.intro();
        ttt.playGame(typeGame);
    }
}