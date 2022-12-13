/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jp.ac.uryukyu.e225717;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Othello othello = new Othello();
        // othello.printBoard();
        // othello.printStatics();

        /**
         * test欄
         */
        int turn = 0;
        try (var stdin = new Scanner(System.in)) {
            while (true) {
                int i = 0, j = 0;
                boolean isExistPuttable = false;
                for (i = 0; i < 8; i++) {
                    for (j = 0; j < 8; j++) {
                        if (!othello.checkPuttable(i, j, turn % 2 == 0).isEmpty()) {
                            isExistPuttable = true;
                            break;
                        }
                    }
                    if (isExistPuttable)
                        break;
                }
                if (!isExistPuttable) {
                    System.err.println("置ける場所がありません。スキップされます");
                    turn++;
                    continue;
                }

                i = 0;
                j = 0;
                while (true) {
                    System.out.println("==========================");
                    System.out.printf("現在の手番は%s\n", turn % 2 == 1 ? "o" : "x");
                    System.out.println("縦の番号 横の番号  の形式で入力");
                    othello.printBoard();
                    othello.printStatics();
                    i = stdin.nextInt();
                    j = stdin.nextInt();
                    if (!othello.checkPuttable(i, j, turn % 2 == 0).isEmpty()) {
                        break;
                    } else {
                        System.err.println("無効な置き場所です.");
                    }
                }
                othello.put(i, j, turn % 2 == 0);
                turn++;

                isExistPuttable = false;
                for (int t = 0; t < 2; t++) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (!othello.checkPuttable(i, j, t == 0).isEmpty()) {
                                isExistPuttable = true;
                                break;
                            }
                        }
                        if (isExistPuttable)
                            break;
                    }
                }

                if (!isExistPuttable) {
                    if (othello.board.blackCount < othello.board.whiteCount) {
                        System.out.println("白:後手番　の勝ちです!");
                    } else if (othello.board.blackCount == othello.board.whiteCount) {
                        System.out.println("引き分けです!");
                    } else {
                        System.out.println("黒:先手番　の勝ちです!");
                    }
                    othello.printBoard();
                    othello.printBoard();
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Faild to open stdinput Stream");
            System.exit(1);
        }

        // ここまで
    }

}
