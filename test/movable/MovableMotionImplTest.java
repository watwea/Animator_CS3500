package movable;

import org.junit.Test;


import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.movable.MovableEllipseImpl;
import cs3500.animator.model.movable.MovableRectangleImpl;
import cs3500.animator.model.movable.motion.Motion;
import cs3500.animator.model.movable.motion.MovableMotionImpl;


import static org.junit.Assert.assertEquals;

/**
 * Tests for methods in the {@link MovableMotionImpl} class.
 */
public class MovableMotionImplTest {
  private Motion motion00 = new MovableMotionImpl(
          1, 0, 0, 0, 0, 0, 0, 0,
          5, 100, 50, 0, 0, 255, 0, 0);
  private Motion motion01 = new MovableMotionImpl(
          5, 100, 50, 0, 0, 255, 0, 0,
          10, -100, -50, 50, 20, 0, 0, 255);
  private Motion motion02 = new MovableMotionImpl(
          10, -100, -50, 50, 20, 0, 0, 255,
          15, 100, 50, 5, 10, 255, 255, 255);
  private Motion motion03 = new MovableMotionImpl(
          4, 100, 50, 0, 0, 255, 0, 0,
          10, -100, -50, 50, 20, 0, 0, 255);
  private Motion motion04 = new MovableMotionImpl(
          9, -100, -50, 50, 20, 0, 0, 255,
          15, 100, 50, 5, 10, 255, 255, 255);

  @Test
  public void moveTest0() {
    AnimationModel model = new AnimationModelImpl();
    MovableEllipseImpl movO = new MovableEllipseImpl("E1");
    MovableRectangleImpl movR = new MovableRectangleImpl("R1");

    assertEquals(AnimationModelImpl.currentTick(), 0);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 0");
    assertEquals(movR.getData(), "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 0");

    AnimationModelImpl.nextTick();
    motion00.move(movO);
    motion00.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 1);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 25.0 y-cord is: 12.5 color is:"
        + " java.awt.Color[r=63,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 1");
    assertEquals(movR.getData(), "RECTANGLE - x-cord is: 25.0 y-cord is: 12.5 color is: "
        + "java.awt.Color[r=63,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 1");

    AnimationModelImpl.nextTick();
    motion00.move(movO);
    motion00.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 2);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 25.0 y-cord is: 12.5 color is:"
        + " java.awt.Color[r=63,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 2");
    assertEquals(movR.getData(), ""
            + "RECTANGLE - x-cord is: 25.0 y-cord is: 12.5 "
            + "color is: java.awt.Color[r=63,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 2");

    AnimationModelImpl.nextTick();
    motion00.move(movO);
    motion00.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 3);
    assertEquals(movO.getData(), ""
            + "ELLIPSE - x-cord is: 50.0 y-cord is: 25.0 "
            + "color is: java.awt.Color[r=126,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 3");
    assertEquals(movR.getData(), ""
            + "RECTANGLE - x-cord is: 50.0 y-cord is: 25.0 "
            + "color is: java.awt.Color[r=126,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 3");

    AnimationModelImpl.nextTick();
    motion00.move(movO);
    motion00.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 4);
    assertEquals(movO.getData(), ""
            + "ELLIPSE - x-cord is: 75.0 y-cord is: 37.5 "
            + "color is: java.awt.Color[r=189,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 4");
    assertEquals(movR.getData(), ""
            + "RECTANGLE - x-cord is: 75.0 y-cord is: 37.5 "
            + "color is: java.awt.Color[r=189,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 4");

    AnimationModelImpl.nextTick();
    motion00.move(movO);
    motion00.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 5);
    assertEquals(movO.getData(), ""
            + "ELLIPSE - x-cord is: 100.0 y-cord is: 50.0 "
            + "color is: java.awt.Color[r=255,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 5");
    assertEquals(movR.getData(), ""
            + "RECTANGLE - x-cord is: 100.0 y-cord is: 50.0 "
            + "color is: java.awt.Color[r=255,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 5");

    AnimationModelImpl.nextTick();
    motion00.move(movO);
    motion00.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 6);
    assertEquals(movO.getData(), ""
            + "ELLIPSE - x-cord is: 100.0 y-cord is: 50.0 "
            + "color is: java.awt.Color[r=255,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 6");
    assertEquals(movR.getData(), ""
            + "RECTANGLE - x-cord is: 100.0 y-cord is: 50.0 "
            + "color is: java.awt.Color[r=255,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 6");
  }

  @Test
  public void moveTest1() {
    AnimationModel model = new AnimationModelImpl();
    MovableEllipseImpl movO = new MovableEllipseImpl("E1");
    MovableRectangleImpl movR = new MovableRectangleImpl("E1");

    assertEquals(AnimationModelImpl.currentTick(), 0);
    assertEquals(movO.getData(), ""
            + "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 "
            + "color is: java.awt.Color[r=0,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 0");
    assertEquals(movR.getData(), ""
            + "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 "
            + "color is: java.awt.Color[r=0,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 0");

    AnimationModelImpl.nextTick();
    motion01.move(movO);
    motion01.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 1);
    assertEquals(movO.getData(), ""
            + "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 "
            + "color is: java.awt.Color[r=0,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 1");
    assertEquals(movR.getData(), ""
            + "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 "
            + "color is: java.awt.Color[r=0,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 1");

    AnimationModelImpl.nextTick();
    motion01.move(movO);
    motion01.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 2);
    assertEquals(movO.getData(), ""
            + "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 "
            + "color is: java.awt.Color[r=0,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 2");
    assertEquals(movR.getData(), ""
            + "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 "
            + "color is: java.awt.Color[r=0,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 2");

    AnimationModelImpl.nextTick();
    motion01.move(movO);
    motion01.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 3);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 3");
    assertEquals(movR.getData(), "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 color is: "
        + "java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 3");

    AnimationModelImpl.nextTick();
    motion01.move(movO);
    motion01.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 4);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 4");
    assertEquals(movR.getData(), "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 4");

    AnimationModelImpl.nextTick();
    motion01.move(movO);
    motion01.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 5);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 60.0 y-cord is: 30.0 color is: "
        + "java.awt.Color[r=204,g=0,b=51] height is: 10.0 width is: 4.0 tick is: 5");
    assertEquals(movR.getData(), "RECTANGLE - x-cord is: 60.0 y-cord is: 30.0 color is:"
        + " java.awt.Color[r=204,g=0,b=51] height is: 10.0 width is: 4.0 tick is: 5");

    AnimationModelImpl.nextTick();
    motion01.move(movO);
    motion01.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 6);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 60.0 y-cord is: 30.0 color is: "
        + "java.awt.Color[r=204,g=0,b=51] height is: 10.0 width is: 4.0 tick is: 6");
    assertEquals(movR.getData(), "RECTANGLE - x-cord is: 60.0 y-cord is: 30.0 color is:"
        + " java.awt.Color[r=204,g=0,b=51] height is: 10.0 width is: 4.0 tick is: 6");
  }

  @Test
  public void moveTest2() {
    AnimationModel model = new AnimationModelImpl();
    MovableEllipseImpl movO = new MovableEllipseImpl("E1");
    MovableRectangleImpl movR = new MovableRectangleImpl("E1");

    assertEquals(AnimationModelImpl.currentTick(), 0);
    assertEquals(movO.getData(), ""
            + "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 "
            + "color is: java.awt.Color[r=0,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 0");
    assertEquals(movR.getData(), ""
            + "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 "
            + "color is: java.awt.Color[r=0,g=0,b=0] "
            + "height is: 0.0 width is: 0.0 tick is: 0");

    AnimationModelImpl.nextTick();
    motion02.move(movO);
    motion02.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 1);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 color is: "
        + "java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 1");
    assertEquals(movR.getData(), "RECTANGLE - x-cord is: 0.0 y-cord is:"
        + " 0.0 color is: java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 1");

    AnimationModelImpl.nextTick();
    motion02.move(movO);
    motion02.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 2);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 2");
    assertEquals(movR.getData(), ""
            + "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 2");

    AnimationModelImpl.nextTick();
    motion02.move(movO);
    motion02.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 3);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 3");
    assertEquals(movR.getData(), "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 3");

    AnimationModelImpl.nextTick();
    motion02.move(movO);
    motion02.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 4);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 4");
    assertEquals(movR.getData(), "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 4");

    AnimationModelImpl.nextTick();
    motion02.move(movO);
    motion02.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 5);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 color is: "
        + "java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 5");
    assertEquals(movR.getData(), "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 color is:"
        + " java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 5");

    AnimationModelImpl.nextTick();
    motion02.move(movO);
    motion02.move(movR);

    assertEquals(AnimationModelImpl.currentTick(), 6);
    assertEquals(movO.getData(), "ELLIPSE - x-cord is: 0.0 y-cord is: 0.0 color is: "
        + "java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 6");
    assertEquals(movR.getData(), "RECTANGLE - x-cord is: 0.0 y-cord is: 0.0 color is: "
        + "java.awt.Color[r=0,g=0,b=0] height is: 0.0 width is: 0.0 tick is: 6");
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveTestFail0() {
    motion00.move(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveTestFail1() {
    motion01.move(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveTestFail2() {
    motion02.move(null);
  }


  @Test
  public void testGetStartX() {
    assertEquals(motion01.getX1(),100,0.01);
  }

  @Test
  public void testGetStartY() {
    assertEquals(motion01.getY1(),50,0.01);
  }

  @Test
  public void testGetEndX() {
    assertEquals(motion01.getX2(),-100.0,0.01);
  }

  @Test
  public void testGetEndY() {
    assertEquals(motion01.getY2(),-50.0,0.01);
  }

  @Test
  public void testGetStartTick() {
    assertEquals(motion01.getT1(),5);
  }

  @Test
  public void testGetEndTick() {
    assertEquals(motion01.getT2(),10);
  }

  @Test
  public void testGetStartWidth() {
    assertEquals(motion01.getW1(),0,0.01);
  }

  @Test
  public void testGetStartHeight() {
    assertEquals(motion01.getH1(),0.0,0.01);
  }

  @Test
  public void testGetEndWidth() {
    assertEquals(motion01.getW2(),50.0,0.01);
  }

  @Test
  public void testGetEndHeight() {
    assertEquals(motion01.getH2(),20.0,0.01);
  }
}
