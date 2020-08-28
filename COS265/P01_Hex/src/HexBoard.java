/****************************************************************************
 *  This class manages an N-by-N hex game board .
 ****************************************************************************/
public class HexBoard {
    private int[][] board;
    private int boardSize;
    private int nodes;
    private int size;
    private int allTiles;
    QuickFindUF connection;

    //Initialization
    public HexBoard(int N) {
        board = new int[N][N];
        boardSize = N * N;
        allTiles = N*N;
        nodes = (N*N) + 4;
        size = N;
        connection = new QuickFindUF(nodes);
    }

    public int getPlayer(int row, int col) {
        //If board[row][col] == 1, then the player is player 1 or if board[row][col] == 2 then the player is player 2
        return board[row][col];
    }
    
    public boolean isSet(int row, int col) {
        //Check if the tile at board[row][col] has been set by either player
        if(board[row][col] == 1 || board[row][col] == 2){
            return true;
        }
        return false;
    }

    public boolean isOnWinningPath(int row, int col) {
        return false;
    }

    public void setTile(int row, int col, int player) {
        //Set the board[row][col] equal to player 1 or 2, and decrement the number of unset tiles
        board[row][col] = player;
        boardSize -= 1;

        //The nodes that connect around another node will be equal to:
        //(row, col)
        //(row, col-1)
        //(row+1, col-1)
        //(row+1, col)
        //(row, col+1)
        //(row-1, col+1)
        //(row-1, col)

            //Connect set tile to all tiles around
            if(inRange(row+0, col-1) && getPlayer(row, col-1) == player){connection.union((col + (row*size)), (col-1 + row*size));}
            if(inRange(row+1, col-1) && getPlayer(row+1, col-1) == player){connection.union((col + (row*size)), (col-1 + (row+1)*size));}
            if(inRange(row+1, col+0) && getPlayer(row+1, col) == player){connection.union((col + (row*size)), (col+ (row+1)*size));}
            if(inRange(row+0, col+1) && getPlayer(row, col+1) == player){connection.union((col + (row*size)), (col+1 + row*size));}
            if(inRange(row-1, col+1) && getPlayer(row-1, col+1) == player){connection.union((col + (row*size)), (col+1 + (row-1)*size));}
            if(inRange(row-1, col+0) && getPlayer(row-1, col) == player){connection.union( (col + row*size), col + (row-1)*size);}

        StdOut.println("Row: " + row);
        StdOut.println("Col: " + col);
            if(player == 1 && col == 0){connection.union(col + (row*size), allTiles); } //Connect to red's north west edge
            if(player == 1 && col == size-1){connection.union((col + (row*size)), allTiles+2); } //Connect to red's south east edge
            if(player == 2 && row == 0){connection.union((col + (row*size)), allTiles+1); } //Connect to blue's north east edge
            if(player == 2 && row == size-1){connection.union((col + (row*size)), allTiles+3); } //Connect to blue's south west edge

    }

    public boolean hasPlayer1Won() {
        return false;
    }

    public boolean hasPlayer2Won() {
        return false;
    }

    public int numberOfUnsetTiles() {

        return boardSize;
    }
    private boolean inRange(int row, int col){
        if(row <= size|| col <= size || row >= 0 || col >= 0){
            return false;
        }
        return true;
    }
}
