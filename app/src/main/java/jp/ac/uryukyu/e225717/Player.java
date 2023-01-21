package jp.ac.uryukyu.e225717;

import org.apache.commons.lang3.tuple.MutablePair;

/**
 * オセロのプレイヤーとして振る舞うためのinterface
 */
public interface Player {
    /**
     * 次に実施する手の情報を返す。
     * 
     * @param othello Othelloを現在の状態として受け取る
     * @return org.apache.commons.lang3.tuple.MutablePair 更新可能なPair
     */
    MutablePair<Integer, Integer> play(Othello othello); // -> [i,j]
}
