package movable;

import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.MovableRectangleImpl;
import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.keyframe.MovableKeyFrameImpl;

import static cs3500.animator.model.AnimationModelImpl.TICK;
import static org.junit.Assert.assertEquals;


/**
 * Represents a tester class for {@link MovableKeyFrameImpl} and its methods.
 */
public class MovableKeyFrameImplTest {

  @Test
  public void testCorrectConstructor() {
    KeyFrame tester = new MovableKeyFrameImpl(1, 1, 1, 1, 1, 1, 1, 1);
    assertEquals(tester.getT1(), 1);
    assertEquals(tester.getX1(), 1, .001);
    assertEquals(tester.getY1(), 1, .001);
    assertEquals(tester.getW1(), 1, .001);
    assertEquals(tester.getH1(), 1, .001);
    assertEquals(tester.getR1(), 1, .001);
    assertEquals(tester.getG1(), 1, .001);
    assertEquals(tester.getB1(), 1, .001);
    assertEquals(tester.write(), "1 1.0 1.0 1.0 1.0 1 1 1  1 1.0 1.0 1.0 1.0 1 1 1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectConstructorNegativeTick() {
    KeyFrame tester = new MovableKeyFrameImpl(-1, 1, 1, 1, 1, 1, 1, 1);
    assertEquals(tester.getT1(), 1);
    assertEquals(tester.getX1(), 1, .001);
    assertEquals(tester.getY1(), 1, .001);
    assertEquals(tester.getW1(), 1, .001);
    assertEquals(tester.getH1(), 1, .001);
    assertEquals(tester.getR1(), 1, .001);
    assertEquals(tester.getG1(), 1, .001);
    assertEquals(tester.getB1(), 1, .001);
    assertEquals(tester.write(), "1 1.0 1.0 1.0 1.0 1 1 1  1 1.0 1.0 1.0 1.0 1 1 1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectConstructorNegativeHeight() {
    KeyFrame tester = new MovableKeyFrameImpl(1, 1, 1, 1, -1, 1, 1, 1);
    assertEquals(tester.getT1(), 1);
    assertEquals(tester.getX1(), 1, .001);
    assertEquals(tester.getY1(), 1, .001);
    assertEquals(tester.getW1(), 1, .001);
    assertEquals(tester.getH1(), 1, .001);
    assertEquals(tester.getR1(), 1, .001);
    assertEquals(tester.getG1(), 1, .001);
    assertEquals(tester.getB1(), 1, .001);
    assertEquals(tester.write(), "1 1.0 1.0 1.0 1.0 1 1 1  1 1.0 1.0 1.0 1.0 1 1 1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectConstructorNegativeWidth() {
    KeyFrame tester = new MovableKeyFrameImpl(1, 1, 1, -1, 1, 1, 1, 1);
    assertEquals(tester.getT1(), 1);
    assertEquals(tester.getX1(), 1, .001);
    assertEquals(tester.getY1(), 1, .001);
    assertEquals(tester.getW1(), 1, .001);
    assertEquals(tester.getH1(), 1, .001);
    assertEquals(tester.getR1(), 1, .001);
    assertEquals(tester.getG1(), 1, .001);
    assertEquals(tester.getB1(), 1, .001);
    assertEquals(tester.write(), "1 1.0 1.0 1.0 1.0 1 1 1  1 1.0 1.0 1.0 1.0 1 1 1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectConstructorNegativeRed() {
    KeyFrame tester = new MovableKeyFrameImpl(1, 1, 1, 1, 1, -1, 1, 1);
    assertEquals(tester.getT1(), 1);
    assertEquals(tester.getX1(), 1, .001);
    assertEquals(tester.getY1(), 1, .001);
    assertEquals(tester.getW1(), 1, .001);
    assertEquals(tester.getH1(), 1, .001);
    assertEquals(tester.getR1(), 1, .001);
    assertEquals(tester.getG1(), 1, .001);
    assertEquals(tester.getB1(), 1, .001);
    assertEquals(tester.write(), "1 1.0 1.0 1.0 1.0 1 1 1  1 1.0 1.0 1.0 1.0 1 1 1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectConstructorNegativeGreen() {
    KeyFrame tester = new MovableKeyFrameImpl(1, 1, 1, 1, 1, 1, -1, 1);
    assertEquals(tester.getT1(), 1);
    assertEquals(tester.getX1(), 1, .001);
    assertEquals(tester.getY1(), 1, .001);
    assertEquals(tester.getW1(), 1, .001);
    assertEquals(tester.getH1(), 1, .001);
    assertEquals(tester.getR1(), 1, .001);
    assertEquals(tester.getG1(), 1, .001);
    assertEquals(tester.getB1(), 1, .001);
    assertEquals(tester.write(), "1 1.0 1.0 1.0 1.0 1 1 1  1 1.0 1.0 1.0 1.0 1 1 1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectConstructorNegativeBlue() {
    KeyFrame tester = new MovableKeyFrameImpl(1, 1, 1, 1, 1, 1, 1, -1);
    assertEquals(tester.getT1(), 1);
    assertEquals(tester.getX1(), 1, .001);
    assertEquals(tester.getY1(), 1, .001);
    assertEquals(tester.getW1(), 1, .001);
    assertEquals(tester.getH1(), 1, .001);
    assertEquals(tester.getR1(), 1, .001);
    assertEquals(tester.getG1(), 1, .001);
    assertEquals(tester.getB1(), 1, .001);
    assertEquals(tester.write(), "1 1.0 1.0 1.0 1.0 1 1 1  1 1.0 1.0 1.0 1.0 1 1 1");
  }

  @Test
  public void testCorrectConstructorAndMove() {
    KeyFrame tester = new MovableKeyFrameImpl(1, 1, 1, 1, 1, 1, 1, 1);
    KeyFrame tester2 = new MovableKeyFrameImpl(5, 100, 100, 50, 10, 14, 16, 197);
    assertEquals(tester.getT1(), 1);
    assertEquals(tester.getX1(), 1, .001);
    assertEquals(tester.getY1(), 1, .001);
    assertEquals(tester.getW1(), 1, .001);
    assertEquals(tester.getH1(), 1, .001);
    assertEquals(tester.getR1(), 1, .001);
    assertEquals(tester.getG1(), 1, .001);
    assertEquals(tester.getB1(), 1, .001);
    assertEquals(tester.write(), "1 1.0 1.0 1.0 1.0 1 1 1  1 1.0 1.0 1.0 1.0 1 1 1");
    Movable movable = new MovableRectangleImpl("Hi");
    TICK = 2;
    tester.move(movable, tester2);
    assertEquals(movable.getX(), 25.75, .001);
    assertEquals(movable.getY(), 25.75, .001);
    assertEquals(movable.getWidth(), 13.25, .001);
    assertEquals(movable.getHeight(), 3.25, .001);
    assertEquals(movable.getColor(), new Color(4, 4, 50));

  }

}
