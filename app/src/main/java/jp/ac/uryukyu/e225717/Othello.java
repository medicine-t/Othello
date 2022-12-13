package jp.ac.uryukyu.e225717;

public class Othello {
    OthelloBoard board = new OthelloBoard();
    private int[] di = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
    private int[] dj = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

    Othello() {
        System.out.println("white: o / black: x");
    }

    boolean isValid(int i, int j, boolean isBlack) {
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

    void printBoard() {
        board.printBoard();
    }
}
