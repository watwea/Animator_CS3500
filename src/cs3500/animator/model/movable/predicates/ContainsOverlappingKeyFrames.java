package cs3500.animator.model.movable.predicates;

import cs3500.animator.model.movable.keyframe.KeyFrame;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Represents a Predicate class that tests whether key frames in an iterable list of {@link
 * KeyFrame}s are overlapping.
 */
public class ContainsOverlappingKeyFrames implements Predicate<Iterable<KeyFrame>> {

  @Override
  public boolean test(Iterable<KeyFrame> keyFrames) {
    boolean overlap = false;

    ArrayList<Integer> ticks = new ArrayList<>();
    for (KeyFrame frame : keyFrames) {
      ticks.add(frame.getT1());
    }

    for (KeyFrame frame : keyFrames) {
      Integer tick = frame.getT1();
      ticks.remove(tick);
      overlap = ticks.contains(tick) || overlap;
    }

    return overlap;
  }
}
