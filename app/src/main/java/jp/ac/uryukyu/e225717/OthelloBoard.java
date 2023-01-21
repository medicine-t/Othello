package jp.ac.uryukyu.e225717;

/**
 * 利用する盤面の情報を扱う。
 */
public class OthelloBoard {
    /**
     * 盤面の横幅
     */
    public final int BOARD_WIDTH = 8;
    /**
     * 盤面の縦幅
     */
    public final int BOARD_HEIGHT = 8;
    /**
     * 後手(白番)の駒の管理用の2次元配列
     */
    public boolean[][] whiteBoard = new boolean[BOARD_HEIGHT][BOARD_WIDTH];
    /**
     * 先手番(黒番)の駒の管理用の2次元配列
     */
    public boolean[][] blackBoard = new boolean[BOARD_HEIGHT][BOARD_WIDTH];
    /**
     * 白の駒の数
     */
    public int whiteCount = 0;
    /**
     * 黒の駒の数
     */
    public int blackCount = 0;

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
    public void printBoard() {
        System.out.println("==========================");
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
