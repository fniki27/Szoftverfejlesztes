package game;

/**
 *A class that represents the board and gives information about the status of the game
 */
public class FourColorsGame {

    public static final int BOARD_SIZE = 4;

    private final int red_value = 1;
    private final int blue_value = 10;
    private final int green_value = 100;
    private final int yellow_value = 1000;

    public int red_num = 3;
    public int blue_num = 3;
    public int green_num = 3;
    public int yellow_num = 3;

    private boolean gameIsDraw = false;
    private boolean gameIsOver = false;
    private GameCheck checkStatus = new GameCheck();

    public Player player = Player.ONE;

    public int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

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

            switchPlayer(player);

            if (checkStatus.winCheck(board)) {
                gameIsOver = true;
            } else if (checkStatus.drawCheck(red_num, blue_num, green_num, yellow_num)) {
                gameIsDraw = true;
            }
        }
    }


    public void placeRed(int i, int j) {
        board[i][j] = red_value;
        red_num--;
    }

    public void placeBlue(int i, int j) {
        board[i][j] = blue_value;
        blue_num--;
    }

    public void placeGreen(int i, int j) {
        board[i][j] = green_value;
        green_num--;
    }

    public void placeYellow(int i, int j) {
        board[i][j] = yellow_value;
        yellow_num--;
    }

    public boolean areDisksLeft(DiskColor color) {
        if(diskNum(color) == 0)
            return false;
        return  true;
    }

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

    public boolean canPlaceDisk(int i, int j, DiskColor diskcolor) {
        int color = getColorValue(diskcolor);

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


    public boolean isGameOver(){
        if (gameIsOver){
            return true;
        } else {
            return false;
        }
    }

    public boolean isGameDraw(){
        if (gameIsDraw){
            return true;
        } else {
            return false;
        }
    }

    public boolean isSquareEmpty(int i, int j) {
         if(board[i][j] != 0)
             return false;
         return true;
    }

    public void setEmptySquare(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void switchPlayer(Player currentPlayer) {
        if(currentPlayer == Player.ONE)
            player = Player.TWO;
        else if (currentPlayer == Player.TWO)
            player = Player.ONE;
    }
}
