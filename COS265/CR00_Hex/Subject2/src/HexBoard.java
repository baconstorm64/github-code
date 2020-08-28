/****************************************************************************
 *  This class manages an N-by-N hex game board .
 ****************************************************************************/

public class HexBoard {

    //class variables
    int[][] board;
    int unset;
    int N;
    int i;
    QuickFindUF uf;
    int player1Left;
    int player1Right;
    int player2Left;
    int player2Right;
    Stack stack;



    /**
     * Constructor for class
     * @param N Board size
     */
    public HexBoard(int N) {

        board = new int[N][N];
        unset = N*N;
        uf = new QuickFindUF(N*N +4);
        this.N = N;
        player1Left = N*N;
        player1Right = N+N+2;
        player2Left = N*N+3;
        player2Right = N*N+1;
        stack =  new Stack();
    }

    public int getPlayer(int row, int col) {

        return board[row][col];
    }
    
    public boolean isSet(int row, int col) {
        if (board[row][col]==0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isOnWinningPath(int row, int col) {
        if (uf.connected(player1Left, CoordinatesToIndex(row, col)) && uf.connected(player1Right, CoordinatesToIndex(row, col))){
            return true;
        }
        if (uf.connected(player2Left, CoordinatesToIndex(row, col)) && uf.connected(player2Right, CoordinatesToIndex(row, col))){
            return true;
        }
        return false;
    }
    public int CoordinatesToIndex(int row, int col){
        return col+N*row;
    }

    public void setTile(int row, int col, int player) {
        board[row][col] = player;
        i = col + N *row;
        if(player==1 && col==0){
            uf.union(i, player1Left);
        }
        if(player==1 && col==N-1){
            uf.union(i, player1Right);
        }
        if(player==2 && row==0){
            uf.union(i, player2Left);
        }
        if(player==2 && row==N-1) {
            uf.union(i, player2Right);
        }
        notTile(row, col,row-1,col);
        notTile(row, col,row,col-1);
        notTile(row, col,row+1,col-1);
        notTile(row, col,row+1,col);
        notTile(row, col,row,col+1);
        notTile(row, col,row-1,col+1);
        unset = unset-1;
    }
    private void notTile(int row0, int col0, int row1, int col1){
        if(row1 == -1){ return;}
        if(col1 == N){ return;}
        if(col1 == -1){ return;}
        if(row1 == N) { return;}
        if(board[row0][col0] == board[row1][col1]){
                uf.union(col0 + N *row0, col1 +N * row1);
        }
    }

    public boolean hasPlayer1Won() {
        return uf.connected(player1Left,player1Right);
    }

    public boolean hasPlayer2Won() {
        return uf.connected(player2Left,player2Right);
    }

    public int numberOfUnsetTiles() {
        return unset;
    }
}
