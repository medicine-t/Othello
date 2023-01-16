package jp.ac.uryukyu.e225717;

public class OthelloBoard {
    final int BOARD_WIDTH = 8;
    final int BOARD_HEIGHT = 8;
    boolean[][] whiteBoard = new boolean[BOARD_HEIGHT][BOARD_WIDTH];
    boolean[][] blackBoard = new boolean[BOARD_HEIGHT][BOARD_WIDTH];
    int whiteCount = 0;
    int blackCount = 0;

    OthelloBoard() {
        whiteBoard[3][3] = whiteBoard[4][4] = true;
        blackBoard[3][4] = blackBoard[4][3] = true;
        whiteCount = 2;
        blackCount = 2;
    }

    /**
     * 標準出力に現在の盤面を出力する。
     * この出力には縦/横のindexが含まれる
     * 先手番の駒を`x`,後手番の駒を`o`,未使用のマスは`.`で表される
     */
    void printBoard() {
        System.out.println(" 01234567");
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            System.out.printf("%d", i);
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (whiteBoard[i][j]) {
                    System.out.printf("o");
                } else if (blackBoard[i][j]) {
                    System.out.printf("x");
                } else {
                    System.out.printf(".");
                }
            }
            System.out.println();
        }
    }
}
