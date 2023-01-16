package jp.ac.uryukyu.e225717;

import org.apache.commons.lang3.tuple.MutablePair;

public interface Player {
    /**
     * 次に実施する手の情報を返す。
     * 
     * @param othello
     * @return org.apache.commons.lang3.tuple.MutablePair
     */
    MutablePair<Integer, Integer> play(Othello othello); // -> [i,j]
}
