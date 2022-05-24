package game;

/**
 * This class represents the board and gives information about the status of the game.
 */
public class FourColorsGame {

    /**
     * The size of the game board.
     */
    public static final int BOARD_SIZE = 4;

    private final int red_value = 1;
    private final int blue_value = 10;
    private final int green_value = 100;
    private final int yellow_value = 1000;

    /**
     * The number of red disks that can be placed on the board.
     */
    public int red_num = 3;

    /**
     * The number of blue disks that can be placed on the board.
     */
    public int blue_num = 3;

    /**
     * The number of green disks that can be placed on the board.
     */

    public int green_num = 3;

    /**
     * The number of yellow disks that can be placed on the board.
     */
    public int yellow_num = 3;

    private boolean gameIsDraw = false;
    private boolean gameIsOver = false;
    private GameCheck checkStatus = new GameCheck();

    /**
     * The currently active player, initialized to the first player.
     */
    public Player player = Player.ONE;

    /**
     * A 2D matrix that represents the game board.
     */
    public int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    /**
     * Places a disk of the specified color on the board.
     * Sets the next player's turn if the game has not ended.
     * Checks if the game has ended or not.
     * @param i the row of the board where we want to place a disk
     * @param j the column of the board where we want to place a disk
     * @param color the color of the disk we want to place
     */
    public void placeDisk(int i, int j, DiskColor color) {
        if(!gameIsDraw && !gameIsOver) {
            switch (color) {
                case RED -> {
                    if(red_num != 0)
                        placeRed(i, j);
                }
                case BLUE -> {
                    if(blue_num != 0)
                        placeBlue(i, j);
                }
                case GREEN -> {
                    if(green_num != 0)
                        placeGreen(i, j);
                }
                case YELLOW -> {
                    if(yellow_num != 0)
                        placeYellow(i, j);
                }
            }

            if (checkStatus.winCheck(board)) {
                gameIsOver = true;
            } else if (checkStatus.drawCheck(red_num, blue_num, green_num, yellow_num)) {
                gameIsDraw = true;
            } else switchPlayer(player);

        }
    }

    /**
     * Places a red disk on the board and decreases the amount of remaining red disks.
     * @param i the row of the board where we want to place a red disk
     * @param j the column of the board where we want to place a red disk
     */
    public void placeRed(int i, int j) {
        board[i][j] = red_value;
        red_num--;
    }

    /**
     * Places a blue disk on the board and decreases the amount of remaining blue disks.
     * @param i the row of the board where we want to place a blue disk
     * @param j the column of the board where we want to place a blue disk
     */
    public void placeBlue(int i, int j) {
        board[i][j] = blue_value;
        blue_num--;
    }

    /**
     * Places a green disk on the board and decreases the amount of remaining green disks.
     * @param i the row of the board where we want to place a green disk
     * @param j the column of the board where we want to place a green disk
     */
    public void placeGreen(int i, int j) {
        board[i][j] = green_value;
        green_num--;
    }

    /**
     * Places a yellow disk on the board and decreases the amount of remaining yellow disks.
     * @param i the row of the board where we want to place a yellow disk
     * @param j the column of the board where we want to place a yellow disk
     */
    public void placeYellow(int i, int j) {
        board[i][j] = yellow_value;
        yellow_num--;
    }

    /**
     * Checks whether there are any remaining disks of a certain color.
     * @param color the color of the remaining disks we want to check
     * @return {@code true} if there is at least one remaining disk of the specified color, {@code false} if not
     */
    public boolean areDisksLeft(DiskColor color) {
        if(diskNum(color) == 0)
            return false;
        return  true;
    }

    /**
     * Returns the number of disks left of a certain color.
     * @param color the color of the disk we want to get the remaining number of
     * @return the number of disks left of the specified color
     */
    public int diskNum(DiskColor color) {
        int numOfDisksLeft = 0;
        switch (color) {
            case RED -> numOfDisksLeft = red_num;
            case BLUE -> numOfDisksLeft = blue_num;
            case GREEN -> numOfDisksLeft = green_num;
            case YELLOW -> numOfDisksLeft = yellow_num;
        }

        return numOfDisksLeft;
    }

    /**
     * Checks whether a disk can be placed in a certain square of the board.
     * @param i the row of the board where we want to place a disk
     * @param j the column of the board where we want to place a disk
     * @param diskColor the color of the disk we want to place
     * @return {@code true} if the neighbouring squares don't have a disk of the same color we want to place {@code false} if they do
     */
    public boolean canPlaceDisk(int i, int j, DiskColor diskColor) {
        int color = getColorValue(diskColor);

        if (i == 0) {
            if (j == 0) {
                if(board[i+1][j] == color || board[i][j+1] == color || board[i+1][j+1] == color)
                    return false;
            }
            else if (j == 3) {
                if(board[i][j-1] == color || board[i+1][j-1] == color || board[i+1][j] == color)
                    return false;
            }
            else {
                if(board[i][j-1] == color || board[i][j+1] == color || board[i+1][j+1] == color || board[i+1][j] == color || board[i+1][j-1] == color)
                    return false;
            }
        }

        else if (i == 3) {
            if (j == 0) {
                if(board[i-1][j] == color || board[i][j+1] == color || board[i-1][j+1] == color)
                    return false;
            }
            else if (j == 3) {
                if(board[i-1][j] == color || board[i][j-1] == color || board[i-1][j-1] == color)
                    return false;
            }
            else {
                if(board[i-1][j-1] == color || board[i-1][j] == color || board[i-1][j+1] == color || board[i][j-1] == color || board[i][j+1] == color)
                    return false;
            }
        }

        else if (j == 0 && (i == 1 || i == 2)) {
            if (board[i-1][j] == color || board[i-1][j+1] == color || board[i][j+1] == color || board[i+1][j] == color || board[i+1][j+1] == color)
                    return false;
        }

        else if (j == 3 && (i == 1 || i == 2)) {
            if (board[i-1][j] == color || board[i-1][j-1] == color || board[i][j-1] == color || board[i+1][j-1] == color || board[i+1][j] == color)
                    return false;
        }

        else if (board[i - 1][j - 1] == color || board[i - 1][j] == color || board[i - 1][j + 1] == color || board[i][j-1] == color || board[i][j+1] == color || board[i+1][j-1] == color || board[i+1][j] == color || board[i+1][j+1] == color){
            return false;
        }

        return true;
    }

    /**
     * Returns the value of a color.
     * @param color the color of the disk we want to get the value of
     * @return the value of the disk of a certain color
     */
    public int getColorValue(DiskColor color) {
        int colorValue = 0;
        switch (color) {
            case RED -> colorValue = red_value;
            case BLUE -> colorValue = blue_value;
            case GREEN -> colorValue = green_value;
            case YELLOW -> colorValue = yellow_value;
        }

        return colorValue;
    }


    /**
     * Returns whether the game is over or not.
     * @return {@code true} if the game is over, {@code false} if not.
     */
    public boolean isGameOver(){
        if (gameIsOver){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns whether the game has ended in a draw or not.
     * @return {@code true} if the game is draw, {@code false} if not.
     */
    public boolean isGameDraw(){
        if (gameIsDraw){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether a square of the board is empty or not.
     * @return {@code true} if the square is empty {@code false} if not
     */
    public boolean isSquareEmpty(int i, int j) {
         if(board[i][j] != 0)
             return false;
         return true;
    }

    /**
     * Sets the value of every square of the board to 0.
     * @param board the board we want to set the values of
     */
    public void setEmptySquare(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * Switches the turns of two players.
     * @param currentPlayer the player who is active at the moment
     */
    public void switchPlayer(Player currentPlayer) {
        if(currentPlayer == Player.ONE)
            player = Player.TWO;
        else if (currentPlayer == Player.TWO)
            player = Player.ONE;
    }
}
