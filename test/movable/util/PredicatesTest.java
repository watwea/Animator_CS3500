package movable.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.keyframe.MovableKeyFrameImpl;
import cs3500.animator.model.movable.predicates.ContainsOverlappingKeyFrames;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for all {@link Predicate}s.
 */
public class PredicatesTest {
  private ContainsOverlappingKeyFrames containsOverlappingKeyFrames =
          new ContainsOverlappingKeyFrames();


  private KeyFrame frame1 =
          new MovableKeyFrameImpl(1, 0, 0, 0, 0, 0, 0, 0);
  private KeyFrame frame2 =
          new MovableKeyFrameImpl(5, 100, 50, 0, 0, 255, 0, 0);
  private KeyFrame frame3 =
          new MovableKeyFrameImpl(5, 100, 50, 0, 0, 255, 0, 0);

  private ArrayList<KeyFrame> klist1 = new ArrayList<>(Arrays.asList(frame1, frame2));
  private ArrayList<KeyFrame> klist2 = new ArrayList<>(Arrays.asList(frame2, frame3));
  private ArrayList<KeyFrame> klist3 = new ArrayList<>(Arrays.asList(frame1, frame2, frame3));


  @Test
  public void testPred() {
    assertFalse(containsOverlappingKeyFrames.test(new ArrayList<>()));
    assertFalse(containsOverlappingKeyFrames.test(klist1));
    assertTrue(containsOverlappingKeyFrames.test(klist2));
    assertTrue(containsOverlappingKeyFrames.test(klist3));
  }
}