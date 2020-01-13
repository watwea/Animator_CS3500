package animator;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.AnimationControllerImpl;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.movable.AbstractMovableImpl;
import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.keyframe.MovableKeyFrameImpl;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.textual.SVGAnimationView;
import cs3500.animator.view.textual.TextAnimationView;
import cs3500.animator.view.visual.VisualAnimationView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Tests for methods in the {@link cs3500.animator.controller.AnimationControllerImpl} class.
 */

public class AnimationControllerImplTest {

  private AnimationView testViewSVG =
          new SVGAnimationView(1, System.out);
  private AnimationView testViewText =
          new TextAnimationView(System.out);
  private AnimationView testViewVisual =
          new VisualAnimationView(1, 0, 0);
  private AnimationView testViewVisual2 =
          new VisualAnimationView(5, 10, 15);
  private AnimationModel testModel =
          new AnimationModelImpl();
  private AnimationModel testModel2 =
          new AnimationModelImpl(5, 5, 5, 5, new ArrayList<>(), 5);

  @Test
  public void testConstructorSVG() {
    AnimationController testController = new AnimationControllerImpl(testModel, testViewSVG);
    assertEquals(testController.getHeight(), 0, .001);
    assertEquals(testController.getWidth(), 0, .001);
    assertEquals(testController.getMinX(), 0, .001);
    assertEquals(testController.getMinY(), 0, .001);
    assertEquals(testController.getMovables(), testModel.getMovables());
  }

  @Test
  public void testConstructorText() {
    AnimationController testController = new AnimationControllerImpl(testModel, testViewText);
    assertEquals(testController.getHeight(), 0, .001);
    assertEquals(testController.getWidth(), 0, .001);
    assertEquals(testController.getMinX(), 0, .001);
    assertEquals(testController.getMinY(), 0, .001);
    assertEquals(testController.getMovables(), testModel.getMovables());
  }

  @Test
  public void testConstructorVisual() {
    AnimationController testController = new AnimationControllerImpl(testModel, testViewVisual);
    assertEquals(testController.getHeight(), 0, .001);
    assertEquals(testController.getWidth(), 0, .001);
    assertEquals(testController.getMinX(), 0, .001);
    assertEquals(testController.getMinY(), 0, .001);
    assertEquals(testController.getMovables(), testModel.getMovables());
  }

  @Test
  public void testConstructorVisual2() {
    AnimationController testController = new AnimationControllerImpl(testModel2, testViewVisual2);
    assertEquals(testController.getHeight(), 5, .001);
    assertEquals(testController.getWidth(), 5, .001);
    assertEquals(testController.getMinX(), 5, .001);
    assertEquals(testController.getMinY(), 5, .001);
    assertEquals(testController.getMovables(), testModel.getMovables());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    new AnimationControllerImpl(null, testViewText);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    new AnimationControllerImpl(testModel2, null);
  }

  @Test
  public void toggleRewindandLoopTest() {
    AnimationControllerImpl tester = new AnimationControllerImpl(testModel2, testViewText);
    tester.toggleRewind();
    assertTrue(tester.getLoop());
    tester.toggleLoop();
    assertFalse(tester.getLoop());
    assertTrue(tester.getReverse());
    tester.toggleRewind();
    assertTrue(tester.getReverse());
  }


  @Test
  public void addShapeTest() {
    testModel2.addMovables(
            new AbstractMovableImpl.MovableBuilder().buildMovable("Bi", "Ellipse"));
    assertNotNull(testModel2.getMovables().get(0));
  }

  @Test
  public void removeShapeTest() {
    testModel2.addMovables(
            new AbstractMovableImpl.MovableBuilder().buildMovable("Bi", "Ellipse"));
    testModel2.removeMovable("Bi");
    assertEquals(testModel2.getMovables(), new ArrayList<Movable>());
  }


  @Test
  public void addKeyFrameTest() {
    testModel2.addMovables(
            new AbstractMovableImpl.MovableBuilder().buildMovable("Bi", "Ellipse"));
    KeyFrame keyFrame =
            new MovableKeyFrameImpl(1, 1, 1, 1, 1, 1, 1, 1);
    assertEquals(((ArrayList<KeyFrame>)
            (testModel2.getMovables().get(0).getKeyFrames())).size(), 0);
    testModel2.addKeyFrame("Bi", keyFrame);
    assertEquals(((ArrayList<KeyFrame>)
                    (testModel2.getMovables().get(0).getKeyFrames())).get(0).getY1(),
            1, .001);
  }

  @Test
  public void removeKeyFrameTest() {
    KeyFrame keyFrame =
            new MovableKeyFrameImpl(1, 1, 1, 1, 1, 1, 1, 1);
    testModel2.addMovables(
            new AbstractMovableImpl.MovableBuilder().buildMovable("Bi", "Ellipse"));
    testModel2.addKeyFrame("Bi", keyFrame);
    assertEquals(((ArrayList<KeyFrame>)
                    (testModel2.getMovables().get(0).getKeyFrames())).get(0).getY1(),
            1, .001);
    testModel2.removeKeyFrame("Bi", 0);
    assertEquals(testModel2.getMovables().get(0).getKeyFrames(), new ArrayList<KeyFrame>());
  }

  @Test
  public void editKeyFrameTest() {
    KeyFrame keyFrame = new MovableKeyFrameImpl(1, 1, 1, 1, 1, 1, 1, 1);
    testModel2.addMovables(
            new AbstractMovableImpl.MovableBuilder().buildMovable("Bi", "Ellipse"));
    testModel2.addKeyFrame("Bi", keyFrame);
    assertEquals(((ArrayList<KeyFrame>)
            (testModel2.getMovables().get(0).getKeyFrames())).get(0).getY1(), 1, .001);
    testModel2.editKeyFrame("Bi", 0,
            new MovableKeyFrameImpl(1, 1, 0, 1, 1, 1, 1, 0));
    assertEquals(((ArrayList<KeyFrame>)
            (testModel2.getMovables().get(0).getKeyFrames())).get(0).getY1(), 0, .001);

  }

}
