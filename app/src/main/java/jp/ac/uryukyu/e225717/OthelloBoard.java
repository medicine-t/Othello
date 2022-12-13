package jp.ac.uryukyu.e225717;

public class OthelloBoard {
    boolean[][] whiteBoard = new boolean[8][8];
    boolean[][] blackBoard = new boolean[8][8];

    OthelloBoard() {
        whiteBoard[3][3] = whiteBoard[4][4] = true;
        blackBoard[3][4] = blackBoard[4][3] = true;
    }

    void printBoard() {
        System.out.println(" 01234567");
        for (int i = 0; i < 8; i++) {
            System.out.printf("%d", i);
            for (int j = 0; j < 8; j++) {
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
