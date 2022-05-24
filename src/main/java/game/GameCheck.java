package game;


public class GameCheck {

    public boolean drawCheck(int num_of_red, int num_of_blue, int num_of_green, int num_of_yellow) {
        if (num_of_red == 0 && num_of_blue == 0 && num_of_green == 0 && num_of_yellow == 0) {
            return  true;
        } else {
            return false;
        }
    }

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

    public boolean winCheck(int[][] board) {
        if (verticalCheck(board) || horizontalCheck(board) || diagonalCheck(board)) {
            return true;
        } else {
            return false;
        }
    }
}
