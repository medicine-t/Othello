package jp.ac.uryukyu.e225717;

import org.apache.commons.lang3.tuple.MutablePair;

/**
 * ゲームセッションを管理する。
 * 交互にプレイヤーを動作させるなど
 */
public class GameManager {
    /**
     * ゲームの盤面操作にまつわる機能を管理
     */
    Othello othello;

    GameManager() {
        this.initialize();
        return;
    }

    /**
     * 状態の初期化
     */
    private void initialize() {
        this.othello = new Othello();
    }

    /**
     * ゲームセッションを初期化してスタートする。
     * 
     * @param pl1 先手。Playerのinterfaceを実装している必要がある。
     * @param pl2 後手。Playerのinterfaceを実装している必要がある。
     * @return 先手番勝利で1,引き分けで0,後手番勝利で-1
     */
    public int sessionStart(Player pl1, Player pl2) {
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

    /**
     * 盤面の終了判定を行う。
     * 各マスについて駒の設置判定を行うことで終了判定を行っている。
     * 
     * @return boolean
     */
    public boolean isFinish() {
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
