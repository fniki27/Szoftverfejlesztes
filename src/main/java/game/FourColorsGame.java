package game;

/**
 *A class that represents the board and gives information about the status of the game
 */
public class FourColorsGame {

    public static final int RED = 1;
    public static final int BLUE= 10;
    public static final int GREEN = 100;
    public static final int YELLOW = 1000;
    public static final int BOARD_SIZE = 4;

    private int red_num = 0;
    private int blue_num = 0;
    private int green_num = 0;
    private int yellow_num = 0;

    private boolean gameIsDraw = false;
    private boolean gameIsOver = false;
    private GameCheck checkStatus = new GameCheck();

    public static Player activePlayer = Player.ONE;
    public static Player winner;

    public int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    public void emptyBoard() {
        for (var i = 0; i < BOARD_SIZE-1; i++)
            for (var j = 0; j < BOARD_SIZE-1; j++)
                board[i][j] = 0;
    }

    public void placeDisk(int i, int j, int color) {
        if (!gameIsDraw && !gameIsOver)
            if (board[i][j] == 0) {

                Player currentPlayer = activePlayer;

                if (currentPlayer == Player.ONE) {
                    switch (color) {
                        case RED -> placeRed(i,j);
                        case BLUE -> placeBlue(i,j);
                    }
                }

                if (currentPlayer == Player.TWO) {
                    switch (color) {
                        case GREEN -> placeGreen(i,j);
                        case YELLOW -> placeYellow(i,j);
                    }
                }

                if (checkStatus.winCheck(board)) {
                    winner = currentPlayer;
                    gameIsOver = true;
                } else if (checkStatus.drawCheck(red_num, blue_num, green_num, yellow_num)) {
                    gameIsDraw = true;
                }

                changePlayer(activePlayer);
            }
    }

    public void changePlayer(Player last_player) {
        if(last_player == Player.ONE){
            activePlayer = Player.TWO;
        } else {
            activePlayer = Player.ONE;
        }
    }

    private void placeRed(int i, int j) {
        if (red_num < 4) {
            if(canPlaceDisk(i,j,RED)) {
                board[i][j] = RED;
                red_num++;
            }
        }
    }

    private void placeBlue(int i, int j) {
        if (blue_num < 4) {
            if(canPlaceDisk(i,j,BLUE)) {
                board[i][j] = BLUE;
                blue_num++;
            }
        }
    }

    private void placeGreen(int i, int j) {
        if (green_num < 4) {
            if(canPlaceDisk(i,j,GREEN)) {
                board[i][j] = GREEN;
                green_num++;
            }
        }
    }

    private void placeYellow(int i, int j) {
        if (yellow_num < 4) {
            if(canPlaceDisk(i,j,YELLOW)) {
                board[i][j] = YELLOW;
                yellow_num++;
            }
        }
    }

    private boolean canPlaceDisk(int i, int j, int color) {
        if (board[i - 1][j] == color || board[i + 1][j] == color || board[i][j - 1] == color || board[i][j + 1] == color || board[i + 1][j + 1] == color || board[i - 1][j - 1] == color || board[i + 1][j - 1] == color || board[i - 1][j + 1] == color) {
            return false;
        }
        return true;
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

}
