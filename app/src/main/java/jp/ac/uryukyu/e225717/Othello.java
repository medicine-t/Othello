package jp.ac.uryukyu.e225717;

import java.util.ArrayList;

class Reversible {
    int i;
    int j;
    int dir;

    Reversible(int i, int j, int dir) {
        this.i = i;
        this.j = j;
        this.dir = dir;
    }
}

public class Othello {
    OthelloBoard board = new OthelloBoard();
    private int[] di = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
    private int[] dj = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

    Othello() {
        System.out.println("white: o / black: x");
    }

    boolean put(int i, int j, boolean isBlack) {
        if (board.blackBoard[i][j] || board.whiteBoard[i][j])
            return false;
        else {
            if (isBlack) {
                for (int d = 0; d < 8; d++) {
                    int ni = i;
                    int nj = j;
                    boolean breaked = false;
                    int reverseCount = 0;
                    do {
                        ni += di[d];
                        nj += dj[d];
                        if (Math.min(ni, nj) < 0) {
                            breaked = true;
                            break;
                        }
                        if (Math.max(ni, nj) >= 8) {
                            breaked = true;
                            break;
                        }
                        reverseCount++;
                    } while (board.whiteBoard[ni][nj]);
                    if (!breaked && reverseCount > 1 && board.blackBoard[ni][nj]) {
                        return true;
                    }
                }
                return false;
            } else {
                for (int d = 0; d < 8; d++) {
                    int ni = i;
                    int nj = j;
                    boolean breaked = false;
                    int reverseCount = 0;
                    do {
                        ni += di[d];
                        nj += dj[d];
                        if (Math.min(ni, nj) < 0) {
                            breaked = true;
                            break;
                        }
                        if (Math.max(ni, nj) >= 8) {
                            breaked = true;
                            break;
                        }
                        reverseCount++;
                    } while (board.blackBoard[ni][nj]);
                    if (!breaked && reverseCount > 1 && board.whiteBoard[ni][nj]) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    /**
     * 確認とともに駒の反転も行ったほうがよいと言われるかもしれないけれども、副作用なくbooleanだけほしいことも多いので切り分けておく
     * 
     * @param i
     * @param j
     * @param isBlack
     * @return true/false
     */
    ArrayList<Reversible> checkPuttable(int i, int j, boolean isBlack) {
        if (board.blackBoard[i][j] || board.whiteBoard[i][j])
            return new ArrayList<>();
        else {
            ArrayList<Reversible> puttable = new ArrayList<>();
            if (isBlack) {
                for (int d = 0; d < 8; d++) {
                    int ni = i;
                    int nj = j;
                    boolean breaked = false;
                    int reverseCount = 0;
                    do {
                        ni += di[d];
                        nj += dj[d];
                        if (Math.min(ni, nj) < 0) {
                            breaked = true;
                            break;
                        }
                        if (Math.max(ni, nj) >= 8) {
                            breaked = true;
                            break;
                        }
                        reverseCount++;
                    } while (board.whiteBoard[ni][nj]);
                    if (!breaked && reverseCount > 1 && board.blackBoard[ni][nj]) {
                        puttable.add(new Reversible(ni, nj, d));
                    }
                }
                return puttable;
            } else {
                for (int d = 0; d < 8; d++) {
                    int ni = i;
                    int nj = j;
                    boolean breaked = false;
                    int reverseCount = 0;
                    do {
                        ni += di[d];
                        nj += dj[d];
                        if (Math.min(ni, nj) < 0) {
                            breaked = true;
                            break;
                        }
                        if (Math.max(ni, nj) >= 8) {
                            breaked = true;
                            break;
                        }
                        reverseCount++;
                    } while (board.blackBoard[ni][nj]);
                    if (!breaked && reverseCount > 1 && board.whiteBoard[ni][nj]) {
                        puttable.add(new Reversible(ni, nj, d));
                    }
                }
                return puttable;
            }
        }
    }

    void printBoard() {
        board.printBoard();
    }
}
