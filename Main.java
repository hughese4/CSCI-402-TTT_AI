import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        TTT_AI ttt_ai = new TTT_AI();

        String gameType = ttt_ai.intro(scanner);
        ttt_ai.playGame(gameType, scanner, random);

        scanner.close();
    }
}
