package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FourColorsGameTest {

    int[][] board1 = {
            {1, 100, 0, 0},
            {10, 0, 0, 0},
            {1, 100, 0, 1000},
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
            {0, 0, 0, 0},
    };

    int[][] board5 = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
    };

    @Test
    void placeDisk() {
        FourColorsGame a = new FourColorsGame();
        a.board = board1;
        a.red_num = 1;
        a.blue_num = 2;
        a.green_num = 1;
        a.yellow_num = 2;
        a.placeDisk(2,2,DiskColor.BLUE);
        assertEquals(1, a.blue_num);
        assertEquals(10, a.board[2][2]);
        assertTrue(a.isGameOver());

        FourColorsGame b = new FourColorsGame();
        b.board = board4;
        b.red_num = 0;
        b.blue_num = 0;
        b.green_num = 1;
        b.yellow_num = 0;
        b.placeDisk(3,2,DiskColor.GREEN);
        assertEquals(0, b.green_num);
        assertEquals(100, b.board[3][2]);
        assertTrue(b.isGameDraw());

        FourColorsGame f = new FourColorsGame();
        f.board = board4;
        f.red_num = 0;
        f.blue_num = 0;
        f.green_num = 1;
        f.yellow_num = 0;
        f.placeDisk(3,0,DiskColor.YELLOW);
        assertEquals(0, f.yellow_num);
        assertEquals(0,f.board[3][0]);
        assertFalse(f.isGameDraw());
    }

    @Test
    void placeRed() {
        FourColorsGame c = new FourColorsGame();
        c.board = board5;
        c.placeRed(3,1);
        assertEquals(2, c.red_num);
        assertEquals(1,c.board[3][1]);
    }

    @Test
    void placeBlue() {
        FourColorsGame g = new FourColorsGame();
        g.board = board5;
        g.placeBlue(2,3);
        assertEquals(2, g.blue_num);
        assertEquals(10,g.board[2][3]);
    }

    @Test
    void placeGreen() {
        FourColorsGame h = new FourColorsGame();
        h.board = board5;
        h.placeGreen(3,1);
        assertEquals(2, h.green_num);
        assertEquals(100,h.board[3][1]);
    }

    @Test
    void placeYellow() {
        FourColorsGame k = new FourColorsGame();
        k.board = board5;
        k.placeYellow(0,0);
        assertEquals(2, k.yellow_num);
        assertEquals(1000,k.board[0][0]);
    }

    @Test
    void areDisksLeft() {
        FourColorsGame s = new FourColorsGame();
        s.red_num = 0;
        s.blue_num = 3;
        s.green_num = 1;
        s.yellow_num = 0;
        assertFalse(s.areDisksLeft(DiskColor.RED));
        assertTrue(s.areDisksLeft(DiskColor.BLUE));
        assertTrue(s.areDisksLeft(DiskColor.GREEN));
        assertFalse(s.areDisksLeft(DiskColor.YELLOW));
    }

    @Test
    void diskNum() {
        FourColorsGame r = new FourColorsGame();
        r.blue_num = 0;
        r.green_num = 2;
        assertEquals(3,r.diskNum(DiskColor.RED));
        assertEquals(0,r.diskNum(DiskColor.BLUE));
        assertEquals(2,r.diskNum(DiskColor.GREEN));
        assertEquals(3,r.diskNum(DiskColor.YELLOW));
    }

    @Test
    void canPlaceDisk() {
        FourColorsGame p = new FourColorsGame();
        p.board = board1;
        assertFalse(p.canPlaceDisk(2,2,DiskColor.YELLOW));
        assertTrue(p.canPlaceDisk(2,2,DiskColor.BLUE));
        assertFalse(p.canPlaceDisk(3,2,DiskColor.GREEN));
        assertTrue(p.canPlaceDisk(0,3,DiskColor.RED));
    }

    @Test
    void getColorValue(){
        FourColorsGame n = new FourColorsGame();
        assertEquals(1,n.getColorValue(DiskColor.RED));
        assertEquals(10,n.getColorValue(DiskColor.BLUE));
        assertEquals(100,n.getColorValue(DiskColor.GREEN));
        assertEquals(1000,n.getColorValue(DiskColor.YELLOW));
    }

    @Test
    void isSquareEmpty() {
        FourColorsGame l = new FourColorsGame();
        l.board = board3;
        assertFalse(l.isSquareEmpty(0,2));
        assertTrue(l.isSquareEmpty(0,0));
    }

    @Test
    void setEmptySquare() {
        FourColorsGame m = new FourColorsGame();
        m.board = board2;
        m.setEmptySquare(m.board);
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                assertEquals(0,m.board[i][j]);
    }

    @Test
    void isGameOver() {
        FourColorsGame d = new FourColorsGame();
        assertFalse(d.isGameOver());
    }

    @Test
    void isGameDraw() {
        FourColorsGame e = new FourColorsGame();
        assertFalse(e.isGameOver());
    }

}