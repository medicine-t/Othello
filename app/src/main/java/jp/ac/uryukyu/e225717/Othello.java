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
    private int[] di = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private int[] dj = { -1, 0, 1, -1, 1, -1, 0, 1 };

    Othello() {
        System.out.println("white: o / black: x");
    }

    boolean put(int i, int j, boolean isBlack) {
        var puttable = checkPuttable(i, j, isBlack);
        if (puttable.isEmpty()) {
            return false;
        }
        var myBoard = board.blackBoard;
        var enemyBoard = board.whiteBoard;
        if (!isBlack) {
            myBoard = board.whiteBoard;
            enemyBoard = board.blackBoard;
        }

        // myBoardとEnemyBoardは参照で入っていることに注意
        myBoard[i][j] = true;
        enemyBoard[i][j] = false;

        for (Reversible rev : puttable) {
            int newDir = (7 - rev.dir);
            while (rev.i != i || rev.j != j) {
                myBoard[rev.i][rev.j] = true;
                enemyBoard[rev.i][rev.j] = false;
                rev.i += di[newDir];
                rev.j += dj[newDir];

                if (isBlack) {
                    board.blackCount++;
                    if (rev.i != i || rev.j != j)
                        board.whiteCount--;
                } else {
                    board.whiteCount++;
                    if (rev.i != i || rev.j != j)
                        board.blackCount--;
                }
            }
        }
        return true;
    }

    /**
     * 駒の反転処理は別関数で…
     * 
     * @param i
     * @param j
     * @param isBlack
     * @return true/false
     */
    ArrayList<Reversible> checkPuttable(int i, int j, boolean isBlack) {
        if ((i < 0 || i > 8) || (j < 0 || i > 8)) {
            return new ArrayList<>();
        }
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

    void printStatics() {
        System.out.printf("Statics: Black%d,White%d\n", board.blackCount, board.whiteCount);
    }
}
