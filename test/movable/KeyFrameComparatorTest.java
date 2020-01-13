package movable;

import org.junit.Test;

import cs3500.animator.model.movable.KeyFrameComparator;
import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.keyframe.MovableKeyFrameImpl;

import static org.junit.Assert.assertEquals;


/**
 * A tester class for the comparator of KeyFrames which is used to order frames in ascending tick
 * order.
 */
public class KeyFrameComparatorTest {

  KeyFrame tester = new MovableKeyFrameImpl(1, 1, 1, 1, 1, 1, 1, 1);
  KeyFrame tester2 = new MovableKeyFrameImpl(5, 100, 100, 50, 10, 14, 16, 197);


  @Test
  public void testCompare() {
    KeyFrameComparator comparer = new KeyFrameComparator();
    assertEquals(comparer.compare(tester, tester2), -4);
  }


}
