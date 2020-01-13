package movable.util;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.keyframe.MovableKeyFrameImpl;
import cs3500.animator.model.movable.predicates.ContainsOverlappingKeyFrames;

import static org.junit.Assert.assertEquals;

/**
 * Represents a tester class for the {@link ContainsOverlappingKeyFrames} predicate.
 */
public class OverlappingKeyFrameTest {

  KeyFrame tester = new MovableKeyFrameImpl(1, 1, 1, 1, 1, 1, 1, 1);
  KeyFrame tester2 = new MovableKeyFrameImpl(5, 100, 100, 50, 10, 14, 16, 197);
  KeyFrame tester3 = new MovableKeyFrameImpl(1, 60, 1, 1, 24, 55, 1, 1);
  ArrayList<KeyFrame> frames = new ArrayList<>();

  @Test
  public void testOverlap() {
    ContainsOverlappingKeyFrames pred = new ContainsOverlappingKeyFrames();
    frames.add(tester);
    assertEquals(pred.test(frames), false);
    frames.add(tester2);
    assertEquals(pred.test(frames), false);
    frames.add(tester3);
    assertEquals(pred.test(frames), true);
  }


}
