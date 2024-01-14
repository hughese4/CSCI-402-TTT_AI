public class Gameplay {
    
    public void gameLoopPVB(Board board, MoveLogic moveLogic, TTT_AI ttt, String firstPlayer) {
        // initialize and display board
        
        int[] posLeft = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        // assign 1 to first player, -1 to other player
        int currentPlayer = 1;
        int otherPlayer = -1;

        boolean gameover = false;
        int userMove = -1; // might want to change these later
        int botMove = -1;
        String win = "";
        boolean tie = false;

        if (firstPlayer.equals("player")){
            // game loop
            while (!gameover) {
                // Player's turn     
                board.printBoard();           
                userMove = moveLogic.userMove(posLeft);
                board = board.updateBoard(board, userMove, currentPlayer);                
                posLeft = moveLogic.updatePosLeft(posLeft, userMove);
                win = board.checkWin(board); // oop out
                if (win != "no win") {
                    gameover = true;
                    System.out.println(win + " wins!");
                    board.printBoard();
                    break;
                }

                tie = ttt.checkTie(posLeft, gameover);
                if (tie) {
                    board.printBoard();
                    break;
                }

                // Bot's turn
                board.printBoard();
                System.out.println("Tbot makes its move...");
                botMove = moveLogic.randomBotMove(posLeft);
                board = board.updateBoard(board, botMove, otherPlayer);
                posLeft = moveLogic.updatePosLeft(posLeft, botMove);
                win = board.checkWin(board); // oop out
                if (win != "no win") {
                    gameover = true;
                    System.out.println(win + " wins!");
                    board.printBoard();
                    break;
                }
                
                tie = ttt.checkTie(posLeft, gameover);
                if (tie) {
                    board.printBoard();
                    break;
                }
            }
        } else if (firstPlayer.equals("bot")) {
            // game loop
            while (!gameover) {
                // Bot's turn
                System.out.println("Tbot makes its move...");                
                botMove = moveLogic.randomBotMove(posLeft);
                board = board.updateBoard(board, botMove, otherPlayer);
                board.printBoard();
                posLeft = moveLogic.updatePosLeft(posLeft, botMove);
                win = board.checkWin(board); // oop out
                if (win != "no win") {
                    gameover = true;
                    System.out.println(win + " wins!");
                    break;
                }

                tie = ttt.checkTie(posLeft, gameover);
                if (tie) {
                    board.printBoard();
                    break;
                }

                // Player's turn
                userMove = moveLogic.userMove(posLeft);
                board = board.updateBoard(board, userMove, currentPlayer);
                board.printBoard();
                posLeft = moveLogic.updatePosLeft(posLeft, userMove);
                win = board.checkWin(board); // oop out
                if (win != "no win") {
                    gameover = true;
                    System.out.println(win + " wins!");
                    break;
                }

                tie = ttt.checkTie(posLeft, gameover);
                if (tie) {
                    board.printBoard();
                    break;
                }
            }
        }
    }

    public void gameLoopBVB(Board board, MoveLogic moveLogic, TTT_AI ttt, String firstPlayer) {
        // initialize and display board
        
        int[] posLeft = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        // assign 1 to first player, -1 to other player
        int currentPlayer = 1;
        int otherPlayer = -1;

        boolean gameover = false;
        int bot1Move = -1; // might want to change these later
        int bot2Move = -1;
        String win = "";
        boolean tie = false;

        
        // game loop
        while (!gameover) {
            // Bot 1's turn
            System.out.println("Bot 1 makes its move...");                
            bot1Move = moveLogic.randomBotMove(posLeft);
            board = board.updateBoard(board, bot1Move, currentPlayer);
            board.printBoard();
            posLeft = moveLogic.updatePosLeft(posLeft, bot1Move);
            win = board.checkWin(board); // oop out

            if (win != "no win") {
                gameover = true;
                System.out.println("Bot 1 wins!");
                break;
            }

            tie = ttt.checkTie(posLeft, gameover);
            if (tie) {
                board.printBoard();
                break;
            }

            // Bot 2's turn
            System.out.println("Bot 2 makes its move...");
            bot2Move = moveLogic.randomBotMove(posLeft);
            board = board.updateBoard(board, bot2Move, otherPlayer);
            board.printBoard();
            posLeft = moveLogic.updatePosLeft(posLeft, bot2Move);
            win = board.checkWin(board); // oop out
            if (win != "no win") {
                gameover = true;
                System.out.println("Bot 2 wins!");
                break;
            }
            
            tie = ttt.checkTie(posLeft, gameover);
            if (tie) {
                board.printBoard();
                break;
            }
        }
    } 


}
