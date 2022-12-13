package jp.ac.uryukyu.e225717;

public class OthelloBoard {
    boolean[][] whiteBoard = new boolean[8][8];
    boolean[][] blackBoard = new boolean[8][8];

    OthelloBoard() {
        whiteBoard[3][3] = whiteBoard[4][4] = true;
        blackBoard[3][4] = blackBoard[4][3] = true;
    }

}
