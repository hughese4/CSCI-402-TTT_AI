import java.util.*;

public class MoveLogic {

    // made by copilot
    public int[] updatePosLeft(int[] posLeft, int move) {
        int[] updatedPosLeft = new int[posLeft.length - 1];
        int index = 0;
        for (int i = 0; i < posLeft.length; i++) {
            if (posLeft[i] != move) {
                updatedPosLeft[index] = posLeft[i];
                index++;
            }
        }
        return updatedPosLeft;
    }

    public String botvBot(String coinflip) {
        System.out.println("inside botvbot");
        if (coinflip.equals("heads")) {
            System.out.println("Bot 1 will go first");
            return "bot1";
        } else {
            System.out.println("Bot 2 will go first");
            return "bot2";
        }
    }

    public String playervBot(String coinflip, Board board) {
        if (coinflip.equals("heads")) {
            System.out.println("Coin landed on heads! Player will go first");
            return "player";
        } else {
            System.out.println("Coind landed on tails! Tbot will go first");
            return "bot";
        }        
    }

    public int randomBotMove(int[] posLeft) {
        // Create a Random object
        Random random = new Random();

        // Generate a random index within the range of the list size
        int randomIndex = random.nextInt(posLeft.length);

        return posLeft[randomIndex];
    }

    public int userMove(int[] posLeft) {
        Scanner sc = new Scanner(System.in);
        boolean validMove = false;

        while (!validMove) {            
            System.out.print("Enter a number between 0 and 8 to make your move: \n> ");
            int move = sc.nextInt();
            
            // Box the primitive array to Integer array
            Integer[] posLeftInteger = Arrays.stream(posLeft).boxed().toArray(Integer[]::new);

            // if the move exists in the array of possible moves
            if (Arrays.asList(posLeftInteger).contains(move)) {
                validMove = true;
                return move;
            } else {
                System.out.println("Invalid move, try again.");
            }            
        }
        
        return 0;
    }

}
