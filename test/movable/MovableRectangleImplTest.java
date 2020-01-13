package movable;

import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import cs3500.animator.model.movable.AbstractMovableImpl.movableType;
import cs3500.animator.model.movable.MovableRectangleImpl;
import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.keyframe.MovableKeyFrameImpl;
import cs3500.animator.model.movable.motion.Motion;
import cs3500.animator.model.movable.motion.MovableMotionImpl;

import static cs3500.animator.model.AnimationModelImpl.TICK;
import static org.junit.Assert.assertEquals;

/**
 * Tests for methods in the {@link MovableRectangleImpl} class.
 */

public class MovableRectangleImplTest {


  private MovableRectangleImpl tester = new MovableRectangleImpl("Tester");

  private Motion motion00 = new MovableMotionImpl(1, 0, 0, 0, 0, 0, 0,
          0, 5, 100, 50, 0, 0, 255, 0, 0);


  private KeyFrame keyFrame00 = new MovableKeyFrameImpl(
          1, 0, 0, 0, 0, 0, 0, 0);
  private KeyFrame keyFrame01 = new MovableKeyFrameImpl(
          5, 100, 50, 0, 0, 255, 0, 0);
  private KeyFrame keyFrame03 = new MovableKeyFrameImpl(
          5, 100, 50, 0, 0, 255, 0, 0);
  private KeyFrame keyFrame04 = new MovableKeyFrameImpl(
          10, -100, -50, 50, 20, 0, 0, 255);
  private KeyFrame keyFrame05 = new MovableKeyFrameImpl(
          10, -100, -50, 50, 20, 0, 0, 255);
  private KeyFrame keyFrame06 = new MovableKeyFrameImpl(
          15, 100, 50, 5, 10, 255, 255, 255);
  private KeyFrame keyFrame07 = new MovableKeyFrameImpl(
          4, 100, 50, 0, 0, 255, 0, 0);
  private KeyFrame keyFrame08 = new MovableKeyFrameImpl(
          10, -100, -50, 50, 20, 0, 0, 255);

  private Iterable<KeyFrame> list00 = new ArrayList<>();
  private Iterable<KeyFrame> list02 = new ArrayList<>(
          Arrays.asList(keyFrame00, keyFrame01, keyFrame03, keyFrame04, keyFrame05, keyFrame06));
  private Iterable<KeyFrame> list03 = new ArrayList<>(
          Arrays.asList(keyFrame00, keyFrame01, keyFrame05, keyFrame06));
  private Iterable<KeyFrame> list04 = new ArrayList<>(
          Arrays.asList(keyFrame03, keyFrame04, keyFrame05, keyFrame06, keyFrame00, keyFrame01));
  private Iterable<KeyFrame> list05 = new ArrayList<>(
          Arrays.asList(keyFrame00, keyFrame01, null));
  private Iterable<KeyFrame> list06 = new ArrayList<>(
          Arrays.asList(null, keyFrame00, keyFrame01, keyFrame03, keyFrame04, null));
  private Iterable<KeyFrame> list07 = new ArrayList<>(
          Arrays.asList(null, keyFrame05, keyFrame06));
  private Iterable<KeyFrame> list08 = new ArrayList<>(
          Arrays.asList(keyFrame03, keyFrame04, keyFrame05, keyFrame06, keyFrame00, keyFrame01));
  private Iterable<KeyFrame> list09 = new ArrayList<>(
          Arrays.asList(keyFrame00, keyFrame01, keyFrame05, keyFrame06));
  private Iterable<KeyFrame> list10 = new ArrayList<>(
          Arrays.asList(keyFrame00, keyFrame01, keyFrame05, keyFrame06, keyFrame07, keyFrame08));
  private Iterable<KeyFrame> list12 = new ArrayList<>(
          Arrays.asList(keyFrame04, keyFrame05, keyFrame06, keyFrame07, keyFrame00, keyFrame01));
  private Iterable<KeyFrame> overlaps = new ArrayList<>(
          Arrays.asList(keyFrame03, keyFrame04, keyFrame05, keyFrame06));

  @Test
  public void testConstructor() {
    assertEquals(tester.getX(), 0.0, 0.001);
    assertEquals(tester.getY(), 0.0, 0.001);
    assertEquals(tester.getX(), 0.0, 0.001);
    assertEquals(tester.getHeight(), 0, .001);
    assertEquals(tester.getWidth(), 0, .001);
    assertEquals(tester.getColor().getGreen(), 0);
    assertEquals(tester.getColor().getRed(), 0);
    assertEquals(tester.getColor().getBlue(), 0);
    assertEquals(tester.getKeyFrames(), new ArrayList<Iterable>());
    assertEquals(tester.getType(), movableType.RECTANGLE);
  }


  @Test
  public void getX() {
    assertEquals(tester.getX(), 0.0, .001);
  }

  @Test
  public void getY() {
    assertEquals(tester.getY(), 0.0, .001);
  }

  @Test
  public void getHeight() {
    assertEquals(tester.getHeight(), 0, .001);
  }

  @Test
  public void getWidth() {
    assertEquals(tester.getWidth(), 0, .001);
  }

  @Test
  public void getColor() {
    assertEquals(tester.getColor().getGreen(), 0);
    assertEquals(tester.getColor().getRed(), 0);
    assertEquals(tester.getColor().getBlue(), 0);
  }

  @Test
  public void getKeyFrames() {
    assertEquals(tester.getKeyFrames(), new ArrayList<Iterable>());
  }

  @Test
  public void setX() {
    tester.setX(5);
    assertEquals(tester.getX(), 5, .001);
  }

  @Test
  public void setY() {
    tester.setY(5);
    assertEquals(tester.getY(), 5, .001);
  }

  @Test
  public void setHeight() {
    tester.setHeight(5);
    assertEquals(tester.getHeight(), 5, .001);
  }

  @Test
  public void setWidth() {
    tester.setWidth(5);
    assertEquals(tester.getWidth(), 5, .001);
  }

  @Test
  public void setColor() {
    tester.setColor(Color.red);
    assertEquals(tester.getColor().getGreen(), 0);
    assertEquals(tester.getColor().getRed(), 255);
    assertEquals(tester.getColor().getBlue(), 0);
  }

  @Test
  public void setKeyFramesTest00() {
    tester.setKeyFrames(list00);
    assertEquals(list00, tester.getKeyFrames());
  }

  @Test
  public void setKeyFramesTest01() {
    tester.setKeyFrames(list03);
    assertEquals(list03, tester.getKeyFrames());
  }

  @Test
  public void setKeyFramesTest02() {
    tester.setKeyFrames(list09);
    assertEquals(list09, tester.getKeyFrames());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesTest03() {
    tester.setKeyFrames(list02);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesTest04() {
    tester.setKeyFrames(list04);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesTest05() {
    tester.setKeyFrames(list05);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesTest06() {
    tester.setKeyFrames(list06);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesTest07() {
    tester.setKeyFrames(list07);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesTest08() {
    tester.setKeyFrames(list08);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesTest09() {
    tester.setKeyFrames(list12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesTest10() {
    tester.setKeyFrames(list10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesTest11() {
    tester.setKeyFrames(list07);
  }


  @Test(expected = IllegalArgumentException.class)
  public void setKeyFrames12() {
    tester.setKeyFrames(list12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesOverlap() {
    tester.setKeyFrames(overlaps);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesOverlap1() {
    tester.setKeyFrames(keyFrame01, keyFrame03);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesDiscontinuous() {
    tester.setKeyFrames(list04);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setKeyFramesDiscontinuous1() {
    tester.setKeyFrames(keyFrame04, keyFrame08);
  }


  @Test
  public void write() {
    TICK = 0;
    assertEquals(tester.getData(),
            "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 color is: "
                    + "java.awt.Color[r=0,g=0,b=0] "
                    + "height is: 0.0 width is: 0.0 tick is: 0");
    TICK = 2;
    motion00.move(tester);
    assertEquals(tester.getData(),
            "RECTANGLE - x-cord is: 25.0 y-cord is: 12.5 color is: "
                    + "java.awt.Color[r=63,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 2");

  }

}