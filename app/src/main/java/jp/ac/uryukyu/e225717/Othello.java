package jp.ac.uryukyu.e225717;

import java.util.ArrayList;

import org.apache.commons.lang3.tuple.MutablePair;

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
    private int turn;

    Othello() {
        System.out.println("white: o / black: x");
        turn = 0;
    }

    void setTurn(int t) {
        turn = t;
    }

    int getTurn() {
        return this.turn;
    }

    void processTurn() {
        turn++;
    }

    /**
     * 引数に与えられたポジションに駒を設置する。
     * 
     * @param i
     * @param j
     * @param isBlack
     * @return boolean: 実際に設置できたか否か
     */
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
     * 駒の反転処理は別関数で行う。
     * 
     * @param i
     * @param j
     * @param isBlack
     * @return true/false
     */
    ArrayList<Reversible> checkPuttable(int i, int j, boolean isBlack) {
        if ((i < 0 || i > board.BOARD_HEIGHT) || (j < 0 || j > board.BOARD_WIDTH)) {
            return new ArrayList<>();
        }
        if (board.blackBoard[i][j] || board.whiteBoard[i][j])
            return new ArrayList<>();
        else {
            ArrayList<Reversible> puttable = new ArrayList<>();
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
                } while (isBlack ? board.whiteBoard[ni][nj] : board.blackBoard[ni][nj]);
                if (!breaked && reverseCount > 1 && (isBlack ? board.blackBoard[ni][nj] : board.whiteBoard[ni][nj])) {
                    puttable.add(new Reversible(ni, nj, d));
                }
            }
            return puttable;
        }
    }

    /**
     * 手番により設置可能なマスをリストアップする。
     * 
     * @return ArrayList<[設置可能なマスの情報(i,j)]>
     */
    ArrayList<MutablePair<Integer, Integer>> listUpPuttable() {
        ArrayList<MutablePair<Integer, Integer>> candicate = new ArrayList<>();
        for (Integer i = 0; i < this.board.BOARD_HEIGHT; i++) {
            for (Integer j = 0; j < this.board.BOARD_WIDTH; j++) {
                if (!this.checkPuttable(i, j, this.getTurn() % 2 == 0).isEmpty()) {
                    candicate.add(MutablePair.of(i, j));
                }
            }
        }
        return candicate;
    }

    void printBoard() {
        board.printBoard();
    }

    /**
     * 現在の各手番の持ち駒の数を表示する。
     */
    void printStatics() {
        System.out.printf("Statics: Black%d,White%d\n", board.blackCount, board.whiteCount);
    }
}
