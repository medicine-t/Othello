package jp.ac.uryukyu.e225717;

import org.apache.commons.lang3.tuple.MutablePair;

public interface Player {
    MutablePair<Integer, Integer> play(Othello othello); // -> [i,j]
}
