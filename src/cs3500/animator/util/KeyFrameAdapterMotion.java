package cs3500.animator.util;

import java.util.ArrayList;
import java.util.Collection;

import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.keyframe.MovableKeyFrameImpl;
import cs3500.animator.model.movable.motion.Motion;
import cs3500.animator.model.movable.motion.MovableMotionImpl;

/**
 * An adapter designed to convert motions to keyframes and visa versa.
 */

public class KeyFrameAdapterMotion {

  /**
   * Turns Keyframes into motions.
   *
   * @param keyFrames the Keyframes to be converted to motions.
   * @return The motions that represent the same information as the keyframes.
   */

  public static Iterable<Motion> keyFramesToMotions(Iterable<KeyFrame> keyFrames) {
    ArrayList<Motion> motions = new ArrayList<>();
    ArrayList<KeyFrame> keyFramesArray = new ArrayList<>((Collection<KeyFrame>) keyFrames);
    for (int i = 0; i < keyFramesArray.size(); i += 1) {
      KeyFrame kF1 = keyFramesArray.get(i);
      KeyFrame kF2 = keyFramesArray.get(Math.min(i + 1, keyFramesArray.size() - 1));
      if (kF1.getT1() < kF2.getT1()) {
        motions.add(new MovableMotionImpl(kF1, kF2));
      }
    }
    return motions;
  }

  /**
   * Turns Motions into motions.
   *
   * @param motions the Keyframes to be converted to motions.
   * @return The keyframes that represent the same information as the motions.
   */

  public static Iterable<KeyFrame> motionsToKeyFrames(Iterable<Motion> motions) {

    ArrayList<KeyFrame> keyFrames = new ArrayList<>();
    ArrayList<Motion> motionsArray = new ArrayList<>((Collection<Motion>) motions);
    for (int i = 0; i < motionsArray.size(); i++) {

      Motion motion = motionsArray.get(i);
      KeyFrame kF1 = new MovableKeyFrameImpl(motion.getT1(), motion.getX1(), motion.getY1(),
              motion.getW1(), motion.getH1(), motion.getR1(), motion.getG1(), motion.getB1());
      KeyFrame kF2 = new MovableKeyFrameImpl(motion.getT2(), motion.getX2(), motion.getY2(),
              motion.getW2(), motion.getH2(), motion.getR2(), motion.getG2(), motion.getB2());

      keyFrames.add(kF1);
      if (kF1.getT1() < kF2.getT1()) {
        keyFrames.add(kF2);
      }

    }
    return keyFrames;
  }

  /**
   * A method to remove any overlapping KeyFrames from the given list.
   *
   * @param keyFrames the frames to filter.
   * @return an arraylist of KeyFrames without overlaps
   */
  public static ArrayList<KeyFrame> filterOverlaps(Iterable<KeyFrame> keyFrames) {
    ArrayList<KeyFrame> filteredFrames = new ArrayList<>();
    ArrayList<Integer> ticks = new ArrayList<>();

    for (KeyFrame keyFrame : keyFrames) {
      if (!(ticks.contains(keyFrame.getT1()))) {
        filteredFrames.add(keyFrame);
      }
      ticks.add(keyFrame.getT1());
    }

    return filteredFrames;
  }
}
