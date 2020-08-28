/****************************************************************************
 *  Command: HexBoardStats N0 N1 T
 *
 *  This program takes the board sizes N0,N1 and game count T as a command-line
 *  arguments. Then, the program runs T games for each board size N where
 *  N0 <= N <= N1 and where each play randomly chooses an unset tile to set in
 *  order to estimate the probability that player 1 will win.
 ****************************************************************************/

public class HexBoardStats {
    private int minBoardSize;
    private int maxBoardSize;
    private int numGames;

    public static void main(String[] args) {
        // default argument values
        int minBoardSize = 2;
        int maxBoardSize = 15;
        int numGames = 100000;
        int choiceOfStatisticsOutput = 0;
        // command-line argument values, if supplied
        if (args.length == 3 || args.length == 4) {
            minBoardSize = Integer.parseInt(args[0]);
            maxBoardSize = Integer.parseInt(args[1]);
            numGames = Integer.parseInt(args[2]);
            if (args.length == 4) {
                choiceOfStatisticsOutput = Integer.parseInt(args[3]);
            }
        }

        if (choiceOfStatisticsOutput == 0) {
            // run the tests for seeing the probabilities of player 1 winning
            Stopwatch stopwatch = new Stopwatch();
            HexBoardStats gameStatistics = new HexBoardStats(minBoardSize, maxBoardSize, numGames);
            // print out the statistics
            StdOut.println("T = " + numGames);
            double P1Odds;
            for (int boardSize=minBoardSize; boardSize<=maxBoardSize; boardSize++) {
                P1Odds = gameStatistics.getP1WinProbabilityEstimate(boardSize);
                StdOut.print("N = " + boardSize + ", ");
                StdOut.printf("P1 = %.4f (%d), ", P1Odds, (int)Math.round((numGames*P1Odds)));
                StdOut.printf("P2 = %.4f (%d)", (1.0-P1Odds), (int)Math.round((numGames*(1.0-P1Odds))));
                StdOut.println();
            }
            StdOut.println("\nExecution of " + numGames + " hex games for board sizes " + minBoardSize + " to " + maxBoardSize + " took " + stopwatch.elapsedTime() + " seconds");
        }

        else if (choiceOfStatisticsOutput == 1) {
            // run the tests for seeing runtimes for varying board sizes (numGames is constant)
            HexBoardStats gameStatistics = new HexBoardStats(minBoardSize, maxBoardSize, numGames);
            //int[] boardSizesArray = {1,10,20,40,80,160,320,640,1280,2560,5120,10240};
            int[] boardSizesArray = {1,10,20,40,80,160,320};
            for (int boardSize : boardSizesArray) {
                gameStatistics.getP1WinProbabilityEstimate(boardSize);
            }
        }
        else if (choiceOfStatisticsOutput == 2) {
            // run the tests for seeing runtimes for varying numGames (board size is constant)
            int[] numberOfGamesArray = {1,10,100,1000,10000,20000,40000,80000,160000,320000};
            for (int numberOfGames : numberOfGamesArray) {
                HexBoardStats gameStatistics = new HexBoardStats(minBoardSize, maxBoardSize, numberOfGames);
                gameStatistics.getP1WinProbabilityEstimate(minBoardSize);
            }
        }

    }

    // constructor
    public HexBoardStats(int N0, int N1, int T) {
        if (N0 <= 0 || N1 < N0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("Invalid HexBoardStats arguments " + N0 + " " + N1 + " " + T);
        }
        minBoardSize = N0;
        maxBoardSize = N1;
        numGames = T;
    }

    public double getP1WinProbabilityEstimate(int N) {
        if (N < minBoardSize || N > maxBoardSize) {
            throw new java.lang.IndexOutOfBoundsException("Board size " + N + " is not between " + minBoardSize + " and " + maxBoardSize);
        }
        HexBoard board;
        int[][] tileLocations;
        double numWinsPlayer1 = 0.0;

        double UFImplementationRuntime = 0.0;
        int numTilesPlaced = 0;
        Stopwatch UFImplementationStopwatch;

        // iterate to complete the specified number of games for board size N
        for (int game=0; game<numGames; game++) {
            board = new HexBoard(N);
            tileLocations = new int[N*N][2];

            // set up tileLocations
            for (int r=0; r<N; r++) {
                for (int c=0; c<N; c++) {
                    tileLocations[r*N +c][0] = r;
                    tileLocations[r*N +c][1] = c;
                }
            }
            shuffleArray(tileLocations);

            int tileLocationIndex = 0;
            int player = 1;

            UFImplementationStopwatch = new Stopwatch();

            // play the game with random tile setting
            while (!(board.hasPlayer1Won() || board.hasPlayer2Won())) {
                board.setTile(tileLocations[tileLocationIndex][0], tileLocations[tileLocationIndex][1], player);
                if (player == 1) player = 2;
                else player = 1;
                tileLocationIndex++;
            }
            if (board.hasPlayer1Won()) {
                numWinsPlayer1 += 1.0;
            }
            UFImplementationRuntime += UFImplementationStopwatch.elapsedTime();
            numTilesPlaced += tileLocationIndex;
        }

        // after all the games done with a specific board size, return player1's win ratio

        /*
        double numTilesPlacedFloat = numTilesPlaced;
        StdOut.printf("Implementation of UF for board size %d over %d games. ", N, numGames);
        StdOut.printf("Time per tile placement: %.4f. ", (UFImplementationRuntime/numTilesPlacedFloat)*10000000);
        StdOut.printf("Overall running time: %.4f seconds\n", UFImplementationRuntime);
        */

        return numWinsPlayer1/numGames;
    }

    // helper function to randomly mix an array's contents
    private void shuffleArray(int[][] array) {
        int randomIndexA;
        int randomIndexB;
        int[] temp;
        for (int s=0; s<array.length; s++) {
            randomIndexA = StdRandom.uniform(0, array.length);
            randomIndexB = StdRandom.uniform(0, array.length);
            temp = array[randomIndexA];
            array[randomIndexA] = array[randomIndexB];
            array[randomIndexB] = temp;
        }
    }
}
