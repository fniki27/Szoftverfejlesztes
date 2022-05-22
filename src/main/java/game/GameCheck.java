package game;

import org.tinylog.Logger;

import static game.FourColorsGame.BOARD_SIZE;

public class GameCheck {

    public boolean drawCheck(int num_of_red, int num_of_blue, int num_of_green, int num_of_yellow) {
        if (num_of_red == 3 && num_of_blue == 3 && num_of_green == 3 && num_of_yellow == 3) {
            Logger.info("Game is Draw!");
            return  true;
        } else {
            return false;
        }
    }

    private boolean verticalCheck(int[][] board) {
        int i = 0;
        for (int j=0; j<BOARD_SIZE-1; j++)
            if (board[i][j] + board[i+1][j] + board[i+2][j] + board[i+3][j] == 1001)
                return true;
        return false;
    }

    private boolean horizontalCheck(int[][] board) {
        int j = 0;
        for (int i=0; i<BOARD_SIZE-1; i++)
            if (board[i][j] + board[i][j+1] + board[i][j+2] + board[i][j+3] == 1001)
                return true;
        return false;
    }

    private boolean diagonalCheck(int[][] board) {
        int sum_main_diagonal = 0;
        int sum_counter_diagonal = 0;
        for (int i = 0; i < BOARD_SIZE-1; i++)
            for (int j = 0; j< BOARD_SIZE-1; j++) {
                if(i == j)
                    sum_main_diagonal = sum_main_diagonal + board[i][j];
                if(i + j == BOARD_SIZE-1)
                    sum_counter_diagonal = sum_counter_diagonal + board[i][j];

            }
        if (sum_main_diagonal == 10 || sum_counter_diagonal == 1001)
            return true;
        return false;
    }

    public boolean winCheck(int[][] board) {
        if (verticalCheck(board) || horizontalCheck(board) || diagonalCheck(board)) {
            Logger.info("Game Over!");
            return true;
        } else {
            return false;
        }
    }
}
