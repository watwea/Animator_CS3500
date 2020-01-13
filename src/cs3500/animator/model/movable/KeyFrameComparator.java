package cs3500.animator.model.movable;

import java.util.Comparator;

import cs3500.animator.model.movable.keyframe.KeyFrame;

/**
 * Represents a comparator of KeyFrames by their tick (lower tick is earlier in the order).
 */
public class KeyFrameComparator implements Comparator<KeyFrame> {

  @Override
  public int compare(KeyFrame o1, KeyFrame o2) {
    return o1.getT1() - o2.getT1();
  }
}
