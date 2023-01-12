package jp.ac.uryukyu.e225717;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.tuple.MutablePair;

public class RandomWalk implements Player {
    @Override
    public MutablePair<Integer, Integer> play(Othello othello) {
        ArrayList<MutablePair<Integer, Integer>> candicate = new ArrayList<>();
        for (Integer i = 0; i < othello.board.BOARD_HEIGHT; i++) {
            for (Integer j = 0; j < othello.board.BOARD_WIDTH; j++) {
                if (!othello.checkPuttable(i, j, othello.getTurn() % 2 == 0).isEmpty()) {
                    candicate.add(MutablePair.of(i, j));
                }
            }
        }
        Random rand = new Random();
        var selectedIndex = rand.nextInt(candicate.size());
        System.out.printf("[Random Walk] put %d, %d\n", candicate.get(selectedIndex).left,
                candicate.get(selectedIndex).right);
        return candicate.get(selectedIndex);
    }
}
