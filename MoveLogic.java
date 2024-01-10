import java.util.*;

public class MoveLogic {
    
    public int randomMove(int[] posLeft) {
        // Create a Random object
        Random random = new Random();

        // Generate a random index within the range of the list size
        int randomIndex = random.nextInt(posLeft.length);

        return posLeft[randomIndex];
    }

    public int userMove(int[] posLeft) {
        Scanner sc = new Scanner(System.in);
        boolean validMove = false;

        while (validMove == false) {
            System.out.println("Enter a number between 0 and 8 to make your move: ");
            int move = sc.nextInt();
            // if the move exists in the array of possible moves
            if (Arrays.asList(posLeft).contains(move)) {
                validMove = true;
                return move;
            } else {
                System.out.println("Invalid move, try again.");
            }
            sc.close();
        }
        return 0;
    }

    public void botvBot(String coinflip) {
        System.out.println("inside botvbot");
        if (coinflip.equals("heads")) {
            System.out.println("Bot 1 will go first");
        } else {
            System.out.println("Bot 2 will go first");
        }
    }

    public void playervBot(String coinflip) {
        if (coinflip.equals("heads")) {
            System.out.println("Coin landed on heads! Player will go first");
        } else {
            System.out.println("Coind landed on tails! Tbot will go first");
        }

        
    }
}
