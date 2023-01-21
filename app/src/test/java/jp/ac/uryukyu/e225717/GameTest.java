package jp.ac.uryukyu.e225717;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ゲームセッションの作成、及びランダムプレイヤー同士による対戦が正常に終了することを確認する
 */
public class GameTest {
    @Test
    void SessionTest() {
        GameManager gameManager = new GameManager();
        Player pl1 = new RandomWalk();
        Player pl2 = new RandomWalk();
        int result = gameManager.sessionStart(pl1, pl2);
        assertTrue(-1 <= result && result <= 1);
    }
}
