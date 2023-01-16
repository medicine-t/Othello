package jp.ac.uryukyu.e225717;

import org.apache.commons.lang3.tuple.MutablePair;

public class GameManager {

    Othello othello;

    private void initialize() {
        this.othello = new Othello();
    }

    /**
     * 
     * @param pl1 先手
     * @param pl2 後手
     */
    int sessionStart(Player pl1, Player pl2) {
        this.initialize();
        while (true) {
            othello.printBoard();
            othello.printStatics();
            boolean isExistPuttable = false;
            for (int i = 0; i < othello.board.BOARD_HEIGHT; i++) {
                for (int j = 0; j < othello.board.BOARD_WIDTH; j++) {
                    if (!othello.checkPuttable(i, j, othello.getTurn() % 2 == 0).isEmpty()) {
                        isExistPuttable = true;
                        break;
                    }
                }
                if (isExistPuttable)
                    break;
            }
            if (!isExistPuttable) {
                System.err.println("置ける場所がありません。スキップされます");
                othello.processTurn();
                continue;
            }

            Integer i, j;
            MutablePair<Integer, Integer> order;
            do {
                order = (othello.getTurn() % 2 == 0 ? pl1 : pl2).play(othello);
                i = order.getLeft();
                j = order.getRight();
            } while (othello.checkPuttable(i, j, othello.getTurn() % 2 == 0).isEmpty());

            othello.put(i, j, othello.getTurn() % 2 == 0);
            othello.processTurn();

            if (this.isFinish()) {
                othello.printBoard();
                if (othello.board.blackCount < othello.board.whiteCount) {
                    System.out.println("白:後手番[o] の勝ちです!");
                    return -1;
                } else if (othello.board.blackCount == othello.board.whiteCount) {
                    System.out.println("引き分けです!");
                    return 0;
                } else {
                    System.out.println("黒:先手番[x] の勝ちです!");
                    return 1;
                }
            }
        }
    }

    boolean isFinish() {
        for (int t = 0; t < 2; t++) {
            for (int i = 0; i < othello.board.BOARD_HEIGHT; i++) {
                for (int j = 0; j < othello.board.BOARD_WIDTH; j++) {
                    if (!othello.checkPuttable(i, j, t == 0).isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
