package cs3500.animator.model.movable.predicates;

import cs3500.animator.model.movable.keyframe.KeyFrame;
import java.util.function.Predicate;

/**
 * Represents a Predicate class that tests whether any {@link KeyFrame} in an iterable list are
 * null.
 */
public class ContainsNull implements Predicate<Iterable<KeyFrame>> {

  @Override
  public boolean test(Iterable<KeyFrame> keyFrames) {
    boolean hasNull = false;
    for (KeyFrame keyFrame : keyFrames) {
      hasNull = hasNull || (keyFrame == null);
    }
    return hasNull;
  }
}
