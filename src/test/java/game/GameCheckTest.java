package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameCheckTest {

    int[][] board = {
            {1, 100, 0, 0},
            {10, 0, 0, 0},
            {1, 100, 10, 1000},
            {0, 0, 0, 0},
    };

    int[][] board2 = {
            {1, 10, 1000, 0},
            {1000, 100, 0, 10},
            {1, 0, 1000, 100},
            {0, 0, 0, 10},
    };

    int[][] board3 = {
            {0, 1, 10, 0},
            {0, 100, 1000, 0},
            {0, 0, 0, 0},
            {0, 100, 0, 10},
    };

    int[][] board4 = {
            {1, 1000, 1, 10},
            {100, 10, 100, 1000},
            {1000, 1, 0, 10},
            {0, 0, 0, 100},
    };


    @Test
    void drawCheck(){
        GameCheck check = new GameCheck();
        assertFalse(check.drawCheck(1,1,1,2));
        assertFalse(check.drawCheck(1,0,1,0));
        assertFalse(check.drawCheck(2,1,1,2));
        assertTrue(check.drawCheck(0,0,0,0));
    }

    @Test
    void winCheck(){
        GameCheck winConditionChecker = new GameCheck();
        assertTrue(winConditionChecker.winCheck(board));
        assertTrue(winConditionChecker.winCheck(board2));
        assertFalse(winConditionChecker.winCheck(board3));
        assertFalse(winConditionChecker.winCheck(board4));
    }

}