package jp.ac.uryukyu.e225717;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.tuple.MutablePair;

public class RandomWalk implements Player {
    @Override
    public MutablePair<Integer, Integer> play(Othello othello) {
        ArrayList<MutablePair<Integer, Integer>> candicate = othello.listUpPuttable();
        Random rand = new Random();
        var selectedIndex = rand.nextInt(candicate.size());
        System.out.printf("[Random Walk] put %d, %d\n", candicate.get(selectedIndex).left,
                candicate.get(selectedIndex).right);
        return candicate.get(selectedIndex);
    }
}
