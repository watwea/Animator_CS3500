package animator;

import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.keyframe.MovableKeyFrameImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.MovableEllipseImpl;
import cs3500.animator.model.movable.MovableRectangleImpl;

import static cs3500.animator.model.AnimationModelImpl.TICK;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for methods in the {@link AnimationModelImpl} class.
 */
public class AnimationModelImplTest {

  /*
   * Tests for constructors
   */
  @Test
  public void constructorTest1() {
    AnimationModel model = new AnimationModelImpl();
    assertEquals(AnimationModelImpl.currentTick(), 0);

  }

  @Test
  public void constructorTest2() {
    AnimationModel model = new AnimationModelImpl(
        0, 0, 1, 1, new ArrayList<>(), 1);
    assertEquals(AnimationModelImpl.currentTick(), 1);
  }

  @Test
  public void constructorTest3() {
    AnimationModel model = new AnimationModelImpl(
        0, 0, 1, 1, new ArrayList<>(), 5);
    assertEquals(AnimationModelImpl.currentTick(), 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorFailTest1() {
    new AnimationModelImpl(0, 0, -1, 0, new ArrayList<>(), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorFailTest2() {
    new AnimationModelImpl(0, 0, 0, -1, new ArrayList<>(), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorFailTest3() {
    new AnimationModelImpl(0, 0, 0, 0, null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorFailTest4() {
    new AnimationModelImpl(0, 0, 0, 0, new ArrayList<>(), -1);
  }

  /*
   * Tests for the currentTick() method
   */
  @Test
  public void currentTickTest() {
    AnimationModel model1 = new AnimationModelImpl();
    assertEquals(AnimationModelImpl.currentTick(), 0);
    TICK = 5;
    assertEquals(AnimationModelImpl.currentTick(), 5);
    TICK = -3;
    assertEquals(AnimationModelImpl.currentTick(), -3);
    TICK = 16;
    assertEquals(AnimationModelImpl.currentTick(), 16);
    AnimationModel model2 = new AnimationModelImpl(
        0, 0, 0, 0, new ArrayList<>(), 4);
    assertEquals(AnimationModelImpl.currentTick(), 4);
  }

  @Test
  public void nextTickTest() {
    AnimationModel model1 = new AnimationModelImpl();
    assertEquals(TICK, 0);
    assertEquals(AnimationModelImpl.currentTick(), 0);
    AnimationModelImpl.nextTick();
    assertEquals(TICK, 1);
    assertEquals(AnimationModelImpl.currentTick(), 1);
    AnimationModel model2 = new AnimationModelImpl(
        0, 0, 0, 0, new ArrayList<>(), 4);
    assertEquals(TICK, 4);
    assertEquals(AnimationModelImpl.currentTick(), 4);
    assertEquals(AnimationModelImpl.currentTick(), 4);
    AnimationModelImpl.nextTick();
    assertEquals(TICK, 5);
    assertEquals(AnimationModelImpl.currentTick(), 5);
    assertEquals(AnimationModelImpl.currentTick(), 5);
    AnimationModelImpl.nextTick();
    assertEquals(TICK, 6);
    assertEquals(AnimationModelImpl.currentTick(), 6);
    assertEquals(AnimationModelImpl.currentTick(), 6);
  }

  @Test
  public void prevTickTest() {
    AnimationModel model1 = new AnimationModelImpl();
    assertEquals(TICK, 0);
    assertEquals(AnimationModelImpl.currentTick(), 0);
    AnimationModel model2 = new AnimationModelImpl(
        0, 0, 0, 0, new ArrayList<>(), 4);
    assertEquals(TICK, 4);
    assertEquals(AnimationModelImpl.currentTick(), 4);
    assertEquals(AnimationModelImpl.currentTick(), 4);
    AnimationModelImpl.prevTick();
    assertEquals(TICK, 3);
    assertEquals(AnimationModelImpl.currentTick(), 3);
    assertEquals(AnimationModelImpl.currentTick(), 3);
    AnimationModelImpl.prevTick();
    assertEquals(TICK, 2);
    assertEquals(AnimationModelImpl.currentTick(), 2);
    assertEquals(AnimationModelImpl.currentTick(), 2);
  }

  @Test(expected = IllegalStateException.class)
  public void prevTickFailTest1() {
    AnimationModel model1 = new AnimationModelImpl();
    assertEquals(TICK, 0);
    assertEquals(AnimationModelImpl.currentTick(), 0);

    AnimationModelImpl.prevTick();
  }

  @Test(expected = IllegalStateException.class)
  public void prevTickFailTest2() {
    AnimationModel model1 = new AnimationModelImpl();
    assertEquals(TICK, 0);
    assertEquals(AnimationModelImpl.currentTick(), 0);

    TICK = -2;

    assertEquals(TICK, -2);
    assertEquals(AnimationModelImpl.currentTick(), -2);

    AnimationModelImpl.prevTick();
  }

  @Test
  public void getMovablesTest() {
    Movable movR = new MovableRectangleImpl("R1");
    Movable movE = new MovableEllipseImpl("E1");
    ArrayList<Movable> list1 = new ArrayList<>(Arrays.asList(movR));
    ArrayList<Movable> list2 = new ArrayList<>(Arrays.asList(movR, movE));
    AnimationModel model1 = new AnimationModelImpl();
    AnimationModel model2 = new AnimationModelImpl(
        0, 0, 0, 0, list1, 1);
    AnimationModel model3 = new AnimationModelImpl(
        0, 0, 0, 0, list2, 1);

    assertEquals(model1.getMovables(), new ArrayList<>());
    assertEquals(model2.getMovables(), list1);
    assertEquals(model3.getMovables(), list2);
  }

  @Test
  public void addMovablesTest() {
    AnimationModel model = new AnimationModelImpl();
    Movable movR = new MovableRectangleImpl("R1");
    Movable movE = new MovableEllipseImpl("E1");

    Iterator<Movable> iterator1 = model.getMovables().iterator();

    assertFalse(iterator1.hasNext());

    model.addMovables(movR);
    Iterator<Movable> iterator2 = model.getMovables().iterator();

    assertTrue(iterator2.hasNext());
    assertEquals(iterator2.next(), movR);

    model.addMovables(movE);
    Iterator<Movable> iterator3 = model.getMovables().iterator();

    assertTrue(iterator3.hasNext());
    assertEquals(iterator3.next(), movR);
    assertTrue(iterator3.hasNext());
    assertEquals(iterator3.next(), movE);
  }

  @Test
  public void clearMovablesTest() {
    Movable movR = new MovableRectangleImpl("R1");
    Movable movE = new MovableEllipseImpl("E1");
    ArrayList<Movable> list1 = new ArrayList<>(Arrays.asList(movR));
    ArrayList<Movable> list2 = new ArrayList<>(Arrays.asList(movR, movE));
    AnimationModel model1 = new AnimationModelImpl();
    AnimationModel model2 = new AnimationModelImpl(
        0, 0, 0, 0, list1, 1);
    AnimationModel model3 = new AnimationModelImpl(
        0, 0, 0, 0, list2, 1);

    assertEquals(model1.getMovables(), new ArrayList<>());
    model1.clearMovables();
    assertEquals(model1.getMovables(), new ArrayList<>());

    assertEquals(model2.getMovables(), list1);
    model2.clearMovables();
    assertEquals(model2.getMovables(), new ArrayList<>());

    assertEquals(model3.getMovables(), list2);
    model3.clearMovables();
    assertEquals(model3.getMovables(), new ArrayList<>());
  }

  /**
   * A Predicate that checks if a Movable is an oval.
   */
  private class IsOval implements Predicate<Movable> {

    @Override
    public boolean test(Movable movable) {
      return movable instanceof MovableEllipseImpl;
    }
  }

  @Test
  public void testIsOvalTest() {
    Movable movR = new MovableRectangleImpl("R1");
    Movable movE = new MovableEllipseImpl("E1");

    assertFalse(new IsOval().test(movR));
    assertFalse(new IsOval().test(null));
    assertTrue(new IsOval().test(movE));
  }

  @Test
  public void clearMovablesIfTest() {
    Movable movR = new MovableRectangleImpl("R1");
    Movable movE = new MovableEllipseImpl("E1");
    ArrayList<Movable> list1 = new ArrayList<>(Arrays.asList(movR));
    ArrayList<Movable> list2 = new ArrayList<>(Arrays.asList(movR, movE));
    AnimationModel model1 = new AnimationModelImpl();
    AnimationModel model2 = new AnimationModelImpl(
        0, 0, 0, 0, list1, 1);
    AnimationModel model3 = new AnimationModelImpl(
        0, 0, 0, 0, list2, 1);

    assertEquals(model1.getMovables(), new ArrayList<>());
    model1.clearMovablesIf(new IsOval());
    assertEquals(model1.getMovables(), new ArrayList<>());

    assertEquals(model2.getMovables(), list1);
    model2.clearMovablesIf(new IsOval());
    assertEquals(model2.getMovables(), list1);

    assertEquals(model3.getMovables(), list2);
    model3.clearMovablesIf(new IsOval());
    assertEquals(model3.getMovables(), list1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void clearMovablesIfNullTest1() {
    AnimationModel model1 = new AnimationModelImpl();

    assertEquals(model1.getMovables(), new ArrayList<>());
    model1.clearMovablesIf(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void clearMovablesIfNullTest2() {
    Movable movR = new MovableRectangleImpl("R1");
    ArrayList<Movable> list1 = new ArrayList<>(Arrays.asList(movR));
    AnimationModel model2 = new AnimationModelImpl(
        0, 0, 0, 0, list1, 0);

    assertEquals(model2.getMovables(), list1);
    model2.clearMovablesIf(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void clearMovablesIfNullTest3() {
    Movable movR = new MovableRectangleImpl("R1");
    Movable movE = new MovableEllipseImpl("E1");
    ArrayList<Movable> list2 = new ArrayList<>(Arrays.asList(movR, movE));
    AnimationModel model3 = new AnimationModelImpl(
        0, 0, 0, 0, list2, 0);

    assertEquals(model3.getMovables(), list2);
    model3.clearMovablesIf(null);
  }

  @Test
  public void testGetLastTick() {
    AnimationModel model = new AnimationModelImpl();
    Movable movR = new MovableRectangleImpl("R1");
    assertEquals(model.getLastTick(), 0);
    movR.addKeyFrames(new MovableKeyFrameImpl(5, 1, 1, 1, 1, 1, 1, 1));
    model.addMovables(movR);
    assertEquals(model.getLastTick(), 5);
  }

  @Test
  public void testGetMovableNames() {
    AnimationModel model = new AnimationModelImpl();
    Movable movR = new MovableRectangleImpl("R1");
    assertEquals(model.getMovableNames().length, 0);
    movR.addKeyFrames(new MovableKeyFrameImpl(5, 1, 1, 1, 1, 1, 1, 1));
    model.addMovables(movR);
    assertEquals(model.getMovableNames()[0], "R1");
  }

  @Test
  public void testAddAndRemoveKeyFrame() {
    AnimationModel model = new AnimationModelImpl();
    Movable movR = new MovableRectangleImpl("R1");
    assertEquals(model.getMovableNames().length, 0);
    movR.addKeyFrames(new MovableKeyFrameImpl(5, 1, 1, 1, 1, 1, 1, 1));
    model.addMovables(movR);
    assertEquals(model.getMovableNames()[0], "R1");
    model.removeKeyFrame("R1", 0);
    assertFalse(movR.getKeyFrames().iterator().hasNext());
  }

  @Test
  public void testEditKeyFrame() {
    AnimationModel model = new AnimationModelImpl();
    Movable movR = new MovableRectangleImpl("R1");
    assertEquals(model.getMovableNames().length, 0);
    movR.addKeyFrames(new MovableKeyFrameImpl(5, 1, 1, 1, 1, 1, 1, 1));
    model.addMovables(movR);
    Iterator<KeyFrame> iter1 = movR.getKeyFrames().iterator();
    assertTrue(iter1.hasNext());
    iter1.next();
    assertFalse(iter1.hasNext());
  }
}
