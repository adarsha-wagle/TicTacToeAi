import java.sql.Array;
import java.util.Scanner;

public class TTTGame {
    TicTacToe game = new TicTacToe();
    char winner;

    String loading[] = {".",".","..",".","..."};
    public void startGame()
    {
        game.displayBoard();
        playGame();
        winner = game.getWinner();
        printMessage();
    }

    public void playGame()
    {
        Scanner in = new Scanner(System.in);
        int count  = 0;
        char turn;
        int row,col;

        while(game.getWinner()==' ' && count<9)
        {
            turn = game.whoseTurn();
            //AI turn to make move
            if(turn == 'O')
            {
                System.out.println("\n \nO's turn to make move: ");

                //SHOWING LOADING ARRAY
                try
                {
                for (int i = 0;i<loading.length;i++)
                {System.out.print(loading[i]);
                Thread.sleep(300);
                }
                    System.out.println("\n");
                }catch(Exception e)
                {
                    System.out.println(e);
                }

               int[] bestMove =  game.getBestMove();//returns row and col
              row = bestMove[0];
              col = bestMove[1];
            }
            //HUMAN MOVE
            else
            {
                 do {
                     System.out.println("\n\n"+turn+"'s turn. Type row and col: ");
                     row = in.nextInt();
                     col = in.nextInt();
                 } while (  row>=3 || col>=3||row<0||col<0||game.getMark(row, col) != ' ');//validating input
            }
            game.putMark(row, col);
            game.displayBoard();
            count++;
        }
        in.close();
    }
    public void printMessage()
    {
        if(winner == 'X')
        {
            System.out.println("\n\nX has won");
        }
        else if (winner == 'O')
        {
            System.out.println("\n\nO has won");
        }
        else {
            System.out.println("\n\n It's a draw");
        }
    }
}
