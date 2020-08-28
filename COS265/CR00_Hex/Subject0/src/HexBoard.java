/****************************************************************************
 *  This class manages an N-by-N hex game board .
 ****************************************************************************/

public class HexBoard {
    private int[][] board;
    private WeightedQuickUnionUF UF;
    //private QuickFindUF UF;
    private WeightedQuickUnionUF physicalConnectionsUF;
    private int UFIndexOfLastTileSet;
    private int unsetTiles;
    private int[] virtualNodes_playerOne = new int[2];
    private int[] virtualNodes_playerTwo = new int[2];

    // constructor
    public HexBoard(int N) {
        if (N<=0) {
            throw new java.lang.IllegalArgumentException("Board size must be greater than zero");
        }

        // initialize the logical board
        board = new int[N][N];
        for (int r=0; r<board.length; r++) {
            for (int c=0; c<board[r].length; c++) {
                board[r][c] = 0;
            }
        }
        unsetTiles = N*N;
        // initialize the logical UnionFind data structure
        UF = new WeightedQuickUnionUF(N*N+4);
        //UF = new QuickFindUF(N*N+4);
        physicalConnectionsUF = new WeightedQuickUnionUF(N*N);
        UFIndexOfLastTileSet = -1;

        virtualNodes_playerOne[0] = N*N;
        virtualNodes_playerOne[1] = N*N+1;
        virtualNodes_playerTwo[0] = N*N+2;
        virtualNodes_playerTwo[1] = N*N+3;
    }

    // return player at tile
    public int getPlayer(int row, int col) {
        validateRowCol(row, col);
        return board[row][col];
    }

    // is tile set?
    public boolean isSet(int row, int col) {
        validateRowCol(row, col);
        return board[row][col] != 0;
    }

    // is tile on winning path?
    public boolean isOnWinningPath(int row, int col) {
        validateRowCol(row, col);
        int winner = -1;
        if (hasPlayer1Won()) {winner = 1;}
        else if (hasPlayer2Won()) {winner = 2;}
        if (getPlayer(row, col) == winner) {
            int currentTile = getUFRepresentation(row,col);
            return physicalConnectionsUF.connected(currentTile, UFIndexOfLastTileSet);
        }
        return false;
    }

    // mark a tile according to which player
    public void setTile(int row, int col, int player) {
        validateRowCol(row, col);
        if (isSet(row,col)) {
            throw new java.lang.IllegalArgumentException("Tile at " + row + ", " + col + " is already set");
        }
        board[row][col] = player;
        unsetTiles--;

        // keep track of connections within UF data structure
        linkTouchingTiles(row, col, player);

        // check if a connection should be made to a virtual node on the edge of the board
        int UFIndex = getUFRepresentation(row, col);
        UFIndexOfLastTileSet = UFIndex;
        if (player == 1) {
            if (col == 0) {UF.union(UFIndex, virtualNodes_playerOne[0]);}
            if (col == board.length-1) {UF.union(UFIndex, virtualNodes_playerOne[1]);}
        }
        else if (player == 2) {
            if (row == 0) {UF.union(UFIndex, virtualNodes_playerTwo[0]);}
            if (row == board.length-1) {UF.union(UFIndex, virtualNodes_playerTwo[1]);}
        }
        else {
            throw new java.lang.IllegalArgumentException("Player " + player + " is invalid. Valid players are 1 and 2");
        }
    }

    // has player1 connected their opposing sides?
    public boolean hasPlayer1Won() {
        return UF.connected(virtualNodes_playerOne[0], virtualNodes_playerOne[1]);
    }

    // has player2 connected their opposing sides?
    public boolean hasPlayer2Won() {
        return UF.connected(virtualNodes_playerTwo[0], virtualNodes_playerTwo[1]);
    }

    // return the number of free, untouched tiles
    public int numberOfUnsetTiles() {
        return unsetTiles;
    }

    // helper function to validate a tile coordinate
    private void validateRowCol(int row, int col) {
        if (row<0 || row>=board.length || col<0 || col>=board[row].length) {
            throw new java.lang.IndexOutOfBoundsException("Invalid row and column arguments " + row + ", " + col);
        }
    }

    // helper function to convert from tile coordinate to UnionFind parent array index
    private int getUFRepresentation(int row, int col) {
        return (board.length * row) + col;
    }

    // helper function to search around a tile that has just been placed and to make any available connections
    private void linkTouchingTiles(int row, int col, int player) {
        int[][] touchingTilesDeltaArray = {{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0}};
        for (int[] deltaArray: touchingTilesDeltaArray) {
            int rowToCheck = row+deltaArray[0];
            int colToCheck = col+deltaArray[1];
            if (!(rowToCheck<0 || rowToCheck>=board.length || colToCheck<0 || colToCheck>=board[rowToCheck].length)) {
                if (getPlayer(rowToCheck, colToCheck) == player) {
                    int p = getUFRepresentation(row,col);
                    int q = getUFRepresentation(rowToCheck,colToCheck);
                    UF.union(p,q);
                    physicalConnectionsUF.union(p,q);
                }
            }
        }
    }
}
