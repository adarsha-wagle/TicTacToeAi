import javax.swing.*;

public class TicTacToe {


    char[][] board;
    private static final int SIZE = 3;
    char player1 = 'X';
    char player2 = 'O';
    char turn;

    public TicTacToe() {
        board = new char[SIZE][SIZE];

//        //PLAYER X WILL BE FIRST TO MOVE
        turn = player1;

        //INITIALIZING GAME BOARD
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }


    }

    //DISPLAYING BOARD
    public void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE-1) {
                    System.out.print(" |");
                }
            }
            if (i < SIZE-1)
                System.out.println("\n---------");

        }
    }

    //RETURNS TURN OF CURRENT PLAYER
    public char whoseTurn() {
        return turn;
    }

    public void putMark(int row, int col) {
        board[row][col] = turn;
        switchPlayer();
    }

    public char getMark(int row, int col) {
        return board[row][col];
    }

    //SWITCHES PLAYER
    public void switchPlayer() {
        if (turn == player1)
            turn = player2;
        else
            turn = player1;
    }

    //CHECKS ROWS, COLUMNS AND DIAGONALS OF BOARD AND IF SOMEONE
    // WINS THEN RETURNS PLAYER_NAME ELSE RETURNS EMPTY CHAR
    public char getWinner() {

        //checking rows
        for (int i = 0; i < SIZE; i++) {
            char cellA = board[i][0];
            char cellB = board[i][1];
            char cellC = board[i][2];
            if (cellA == ' ' || cellB == ' ' || cellC == ' ') {
                continue;
            }
            if (cellA == cellB && cellB == cellC && cellA != ' ')
                return cellA;
        }
        //checking columns
        for (int j = 0; j < SIZE; j++) {
            char cellA = board[0][j];
            char cellB = board[1][j];
            char cellC = board[2][j];
            if (cellA == ' ' || cellB == ' ' || cellC == ' ') {
                continue;
            }
            if (cellA == cellB && cellB == cellC && cellA != ' ')
                return cellA;
        }
        //checking diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return board[0][0];
        }
        if(board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2]!=' ')
        {
            return board[0][2];
        }
        //returning empty value if no one wins
        return ' ';

    }


    //RETURNS TRUE IF THE BOARD IS FULL AND FALSE IF THERE IS SPACE
    public boolean isBoardFull()
    {
        for (int i = 0;i<SIZE;i++)
        {
            for (int j = 0;j<SIZE;j++)
            {
                if(getMark(i,j) == ' ' )
                    return false;
            }
        }
        return true;
    }


    //TRAVERSES ALL THE AVAILABLE SPACE AND GETS THE BEST MOVE
    public int[] getBestMove()
    {
        int[] bestMove = new int[SIZE-1];
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0;i<SIZE;i++)
        {
            for (int j = 0;j<SIZE;j++)
            {
                if(getMark(i,j)==' ')
                {
                    board[i][j] = player2;
                   int currentScore = minMax(false);
                   board[i][j] = ' ';
                   if(currentScore>bestScore)
                   {
                       bestScore = currentScore;
                       bestMove[0] = i;
                       bestMove[1] = j;
                   }
                }
            }
        }
        return bestMove;
    }

    //RETURNS THE SCORE FOR EACH SPACE
    public int minMax(boolean maximizingPlayer)
    {
        if(getWinner()== player2)
        {

            return 1;
        }
        else if (getWinner() == player1)
        {
            return -1;
        }
        else if (isBoardFull())
        {

            return 0;
        }
        if(maximizingPlayer)
        {
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0;i<SIZE;i++)
            {
                for (int j = 0;j<SIZE;j++)
                {
                    if(getMark(i,j) == ' ')
                    {

                        board[i][j] = player2;
                        int score = minMax(false);
                        board[i][j] = ' ' ;

                        maxEval = Math.max(score,maxEval);
                    }
                }
            }
            return maxEval;
        }
        else
        {
            int minEval = Integer.MAX_VALUE;
            for (int  i = 0;i<SIZE;i++)
            {
                for (int j = 0;j<SIZE;j++)
                {
                    if(getMark(i,j) == ' ')
                    {
                        board[i][j] =player1;
                        int score = minMax(true);
                        board[i][j] = ' ';
                        minEval = Integer.min(minEval,score) ;

                    }
                }
            }
            return minEval;
        }
    }
}