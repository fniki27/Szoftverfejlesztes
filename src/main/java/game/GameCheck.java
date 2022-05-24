package game;

/**
 * This class checks the game status (game over or draw).
 */
public class GameCheck {

    /**
     * Checks whether the players have any more disks to place or not.
     * @param num_of_red the number of the remaining red disks
     * @param num_of_blue the number of the remaining blue disks
     * @param num_of_green the number of the remaining green disks
     * @param num_of_yellow the number of the remaining yellow disks
     * @return {@code true} if there are no more remaining disks of any color, {@code false} if there still are remaining disks of at least one color
     */
    public boolean drawCheck(int num_of_red, int num_of_blue, int num_of_green, int num_of_yellow) {
        if (num_of_red == 0 && num_of_blue == 0 && num_of_green == 0 && num_of_yellow == 0) {
            return  true;
        } else {
            return false;
        }
    }

    /**
     * Checks for winning by having 4 different colors in a column.
     * @param board the board the method should check
     * @return {@code true} if there are 4 different colors in a column, {@code false} if not
     */
    private boolean verticalCheck(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            int sumCol = 0;
            for (int j = 0; j < board.length; j++) {
                sumCol = sumCol + board[j][i];
            }
            if (sumCol == 1111)
                return true;
        }
        return false;
    }

    /**
     * Checks for winning by having 4 different colors in a row.
     * @param board the board the method should check
     * @return {@code true} if there are 4 different colors in a row, {@code false} if not
     */
    private boolean horizontalCheck(int[][] board) {
        for(int i = 0; i < board.length; i++){
            int sumRow = 0;
            for(int j = 0; j < board.length; j++){
                sumRow = sumRow + board[i][j];
            }
            if (sumRow == 1111)
                return true;
        }
        return false;
    }

    /**
     * Checks for winning by having 4 different colors in a diagonal.
     * @param board the board the method should check
     * @return {@code true} if there are 4 different colors in a diagonal, {@code false} if not
     */
    private boolean diagonalCheck(int[][] board) {
        int sum_main_diagonal = 0;
        int sum_counter_diagonal = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++) {
                if(i == j)
                    sum_main_diagonal = sum_main_diagonal + board[i][j];
                if(i + j == board.length - 1)
                    sum_counter_diagonal = sum_counter_diagonal + board[i][j];

            }
        if (sum_main_diagonal == 1111 || sum_counter_diagonal == 1111)
            return true;
        return false;
    }

    /**
     * Checks whether the game was won.
     * @param board the board the method should check
     * @return {@code true} if at least one win condition is met, {@code false} if not
     */
    public boolean winCheck(int[][] board) {
        if (verticalCheck(board) || horizontalCheck(board) || diagonalCheck(board)) {
            return true;
        } else {
            return false;
        }
    }
}
