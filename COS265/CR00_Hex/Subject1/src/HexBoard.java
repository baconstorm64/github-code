/****************************************************************************
 *  This class manages an N-by-N hex game board .
 ****************************************************************************/

public class HexBoard {
    private int[][] boardArray;

    private WeightedQuickUnionUF player1NodeMap; // Left/Right
    private WeightedQuickUnionUF player2NodeMap; // Top/Bottom
    private WeightedQuickUnionUF player1NodeMapNoVirtual; // Left/Right
    private WeightedQuickUnionUF player2NodeMapNoVirtual; // Top/Bottom

    private int size;
    private int area;
    private int pieces;
    private int leftVNode, rightVNode, topVNode, bottomVNode;

    private int lastMove;
    private boolean gameIsWon;


    public HexBoard(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        boardArray = new int[N][N];
        size = N;
        area = N*N;
        pieces = 0;

        lastMove = -1;
        gameIsWon = false;

        player1NodeMapNoVirtual = new WeightedQuickUnionUF(area);
        player2NodeMapNoVirtual = new WeightedQuickUnionUF(area);

        player1NodeMap = new WeightedQuickUnionUF(area+2); // Last two are virtual nodes (Left, Right)
        player2NodeMap = new WeightedQuickUnionUF(area+2); // Last four are virtual nodes (Top, Bottom)
        leftVNode = area;           // Player 1
        rightVNode = area + 1;      // Player 1
        topVNode = area;            // Player 2
        bottomVNode = area + 1;     // Player 2

        for (int i = 0; i < size; i++) { // Connect Virtual Nodes
            player1NodeMap.union(leftVNode, flatten(i, 0));
            player1NodeMap.union(rightVNode, flatten(i, size-1));
            player2NodeMap.union(bottomVNode, flatten(0, i));
            player2NodeMap.union(topVNode, flatten(size-1, i));
        }
    }

    public int getPlayer(int row, int col) {
        if (!checkIsInBounds(row, col)) throw new IndexOutOfBoundsException();

        return boardArray[row][col];
    }

    public boolean isSet(int row, int col) {
        return getPlayer(row, col) != 0;
    }

    public boolean isOnWinningPath(int row, int col) {
        if (!checkIsInBounds(row, col)) throw new IndexOutOfBoundsException();

        if (!gameIsWon)
            return false;

        return player1NodeMapNoVirtual.connected(lastMove, flatten(row, col)) || player2NodeMapNoVirtual.connected(lastMove, flatten(row, col));
    }

    public void setTile(int row, int col, int player) {
        if (!checkIsInBounds(row, col)) throw new IndexOutOfBoundsException();

        if(boardArray[row][col] == 0) {
            pieces++;
            lastMove = flatten(row, col);
        } else
            throw new IllegalArgumentException();
        boardArray[row][col] = player;

        // Iterate through surrounding cells
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (checkIsInBounds(r,c)) {
                    if (r == row && c == col)
                        continue; // Skip the center cell
                    if (r == row - 1 && c == col - 1)
                        continue; // Skip the bottom left cell (not adjacent)
                    if (r == row + 1 && c == col + 1)
                        continue; // Skip the top right cell (not adjacent)

                    if (boardArray[r][c] == player) {
                        if (player == 1) {
                            player1NodeMap.union(flatten(r, c), flatten(row, col)); // Join to adjacent cells inhabited by this player
                            player1NodeMapNoVirtual.union(flatten(r, c), flatten(row, col)); // Join in nodemap for checking winning path
                        } else {
                            player2NodeMap.union(flatten(r, c), flatten(row, col)); // Join to adjacent cells inhabited by this player
                            player2NodeMapNoVirtual.union(flatten(r, c), flatten(row, col)); // Join in nodemap for checking winning path
                        }
                    }
                }
            }
        }
    }

    public boolean hasPlayer1Won() {
        if (gameIsWon)
            return true;
        if (player1NodeMap.connected(leftVNode, rightVNode))
            gameIsWon = true;
        return gameIsWon;
    }

    public boolean hasPlayer2Won() {
        if (gameIsWon)
            return true;
        if (player2NodeMap.connected(topVNode, bottomVNode))
            gameIsWon = true;
        return gameIsWon;
    }

    public int numberOfUnsetTiles() {
        return size*size - pieces;
    }

    private boolean checkIsInBounds(int row, int col) { // Returns true if in bounds
        return !(row < 0 || row >= size || col < 0 || col >= size);
    }

    private int flatten(int row, int col){ // Returns the index of the spot given the rox, col
        return row * size + col;
    }

    private int[] expand(int i){ // Returns [row, col] of spot given index
        int col = i % size;
        int row = i / size;
        return new int[] {row, col};
    }
}
