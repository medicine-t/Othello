package jp.ac.uryukyu.e225717;

import java.util.Scanner;
import org.apache.commons.lang3.tuple.MutablePair;

public class InteractivePlayer implements Player {
    Scanner stdin;

    // stdinは参照で渡される
    InteractivePlayer(Scanner stdin) {
        this.stdin = stdin;
    }

    @Override
    public MutablePair<Integer, Integer> play(Othello othello) {
        try {
            int i = 0, j = 0;
            while (true) {
                System.out.println("==========================");
                System.out.printf("現在の手番は%s\n", othello.getTurn() % 2 == 1 ? "o" : "x");
                System.out.println("縦の番号 横の番号  の形式で入力");
                i = stdin.nextInt();
                j = stdin.nextInt();
                if (!othello.checkPuttable(i, j, othello.getTurn() % 2 == 0).isEmpty()) {
                    break;
                } else {
                    System.err.println("無効な置き場所です.");
                }
            }
            return MutablePair.of(i, j);
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
        // ここまでこないことを想定
        // TODO: 来たときの処理(エラー返す?)

        return MutablePair.of(-1, -1);
    }
}
