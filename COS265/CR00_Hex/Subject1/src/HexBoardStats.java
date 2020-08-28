import javafx.scene.paint.Stop;

import java.util.Timer;

/****************************************************************************
 *  Command: HexBoardStats N0 N1 T
 *
 *  This program takes the board sizes N0,N1 and game count T as a command-line
 *  arguments. Then, the program runs T games for each board size N where
 *  N0 <= N <= N1 and where each play randomly chooses an unset tile to set in
 *  order to estimate the probability that player 1 will win.
 ****************************************************************************/

public class HexBoardStats {
    private int low, high, tests;

    public static void main(String[] args) {
//        HexBoardStats stats = new HexBoardStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        Stopwatch global = new Stopwatch();
        int T = 1000;
        int N = 10;

        StdOut.println("T    |    N    | time");
        for (; N <= 100; N+=10) {
            HexBoardStats stats = new HexBoardStats(1,1000000,T);
            Stopwatch local = new Stopwatch();
            double prob = stats.getP1WinProbabilityEstimate(N);
            //StdOut.printf("N = %d, P1 = %1.2f, P2 = %1.2f\n", N, prob, 1-prob);
            StdOut.printf("%d | %d | %.3f\n" , T, N, local.elapsedTime());
        }
        StdOut.println(global.elapsedTime());
    }

    public HexBoardStats(int N0, int N1, int T) {
        if (N0 <= 0 || N1 < N0 || T <= 0) throw new IllegalArgumentException();

        low = N0;
        high = N1;
        tests = T;
    }

    public double getP1WinProbabilityEstimate(int N) {
        if (N < low || N > high) throw new IndexOutOfBoundsException();

        int player1Victories = 0;

        for (int test = 0; test < tests; test++) {
            if (playGame(N)) {
                player1Victories++;
            }
        }

        return (double)player1Victories/tests;
    }

    private boolean playGame(int N) { // Returns true if player 1 won
       HexBoard board = new HexBoard(N);

       int currentPlayer = 1;

       do {
           int move = getNewMove(board, N);
           board.setTile(move/N, move%N, currentPlayer);
           currentPlayer = currentPlayer == 1 ? 2 : 1; // Switch Player

       } while (!board.hasPlayer1Won() && !board.hasPlayer2Won());
       return board.hasPlayer1Won();
    }

    private int getNewMove(HexBoard board, int N) {
       int move = -1;
       while (move == -1 || board.isSet(move/N, move%N)){
           move = (int)(StdRandom.random()*N*N);
       }
       return move;
    }
}
