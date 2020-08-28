/****************************************************************************
 *  Command: HexBoardStats N0 N1 T
 *
 *  This program takes the board sizes N0,N1 and game count T as a command-line
 *  arguments. Then, the program runs T games for each board size N where
 *  N0 <= N <= N1 and where each play randomly chooses an unset tile to set in
 *  order to estimate the probability that player 1 will win.
 ****************************************************************************/

public class HexBoardStats {
    int T;
    int N0;
    int N1;

    public static void main(String[] args) {
        HexBoardStats HexBoard = new HexBoardStats(2, 15, 100);
    }
    public HexBoardStats(int N0, int N1, int T){
        this.T = T;
        this. N0 = N0;
        this. N1 = N1;
        for (int N = N0; N <= N1; N++){
           double numberEstimated= getP1WinProbabilityEstimate(N);
           StdOut.println(N);
           StdOut.println(numberEstimated);
        }

    }

    public double getP1WinProbabilityEstimate(int N){
        int playerCount=0;
        for(int i = 0; i < T; i++){
            if (playGame(N))
                playerCount++;
        }
        return (playerCount/T);
    }
    private boolean playGame(int N){
        HexBoard board = new HexBoard(N);
        int player = 1;
        /*while(!true ){
            StdRandom.uniform(0.0, (double)N);
        }*/

        return true;
    }
}
