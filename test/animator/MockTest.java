package animator;

import org.junit.Test;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.AnimationView;

import static org.junit.Assert.assertEquals;

/**
 * Represents a tester class that ensures methods communicating between the model, view, and
 * controller function properly.
 */
public class MockTest {

  private StringBuilder modelOutput = new StringBuilder();
  private AnimationModel mockModel = new MockModel(modelOutput);
  private StringBuilder viewOutput = new StringBuilder();
  private AnimationView mockView = new MockView(1, 0, 0, viewOutput);
  private StringBuilder controllerOutput = new StringBuilder();
  private AnimationController mockController =
          new MockController(mockModel, mockView, controllerOutput);

  @Test
  public void modelTest() {
    assertEquals(modelOutput.toString(), ""
            + "actionsLog set\n"
            + "minX set\n"
            + "minY set\n"
            + "width set\n"
            + "height set\n"
            + "movables set\n"
            + "setLastTick() called\n"
            + "AnimationModelImpl.TICK set\n");
  }

  @Test
  public void viewTest() {
    assertEquals(viewOutput.toString(), ""
            + "actionsLog set\n"
            + "ticksPerSecond set\n"
            + "width: 0\n"
            + "height: 0\n"
            + "setController() called\n");
  }

  @Test
  public void controllerTest() {
    assertEquals(controllerOutput.toString(), ""
            + "actionsLog set\n"
            + "model set\n"
            + "view set\n"
            + "reverse set\n"
            + "loop set\n"
            + "ticksPerSecond set\n"
            + "timer set\n");
  }

  @Test
  public void viewToModelTest() {
    assertEquals(modelOutput.toString(), ""
            + "actionsLog set\n"
            + "minX set\n"
            + "minY set\n"
            + "width set\n"
            + "height set\n"
            + "movables set\n"
            + "setLastTick() called\n"
            + "AnimationModelImpl.TICK set\n");
    assertEquals(viewOutput.toString(), ""
            + "actionsLog set\n"
            + "ticksPerSecond set\n"
            + "width: 0\n"
            + "height: 0\n"
            + "setController() called\n");
    assertEquals(controllerOutput.toString(), ""
            + "actionsLog set\n"
            + "model set\n"
            + "view set\n"
            + "reverse set\n"
            + "loop set\n"
            + "ticksPerSecond set\n"
            + "timer set\n");
    mockController.removeKeyFrame("name", 0);
    mockController.run();
    mockController.getMovables();
    mockController.getMinX();
    mockController.getMinY();
    mockController.getWidth();
    mockController.getHeight();
    mockController.getReverse();
    mockController.getLoop();
    mockController.play();
    mockController.pause();
    mockController.toggleRewind();
    mockController.setTicksPerSecond(10);
    mockController.addShape("name", "rectangle");
    mockController.addShape("name", "ellipse");
    mockController.removeShape("name");
    mockController.toggleLoop();
    mockController.addKeyFrame("name", 1, 1, 1, 1, 1, 1, 1, 1);
    mockController.removeKeyFrame("name", 0);
    mockController.editKeyFrame("name", 1, 1, 1, 1, 1, 1, 1, 1);
    mockController.moveAll();
    mockController.getMovableNames();
    mockController.getKeyFrames("movableName");
    assertEquals(modelOutput.toString(), ""
            + "actionsLog set\n"
            + "minX set\n"
            + "minY set\n"
            + "width set\n"
            + "height set\n"
            + "movables set\n"
            + "setLastTick() called\n"
            + "AnimationModelImpl.TICK set\n"
            + "removeKeyFrame(name, 0) called\n"
            + "getMovables() called\n"
            + "getMinX() called\n"
            + "getMinY() called\n"
            + "getWidth() called\n"
            + "getHeight() called\n"
            + "addMovables(class [Lcs3500.animator.model.movable.Movable;) called\n"
            + "addMovables(class [Lcs3500.animator.model.movable.Movable;) called\n"
            + "removeMovable(name) called\n"
            + "addKeyFrame(name, 1 1.0 1.0 1.0 1.0 1 1 1  1 1.0 1.0 1.0 1.0 1 1 1) called\n"
            + "removeKeyFrame(name, 0) called\n"
            + "editKeyFrame(name, 1, 1 1.0 1.0 1.0 1.0 1 1 1  1 1.0 1.0 1.0 1.0 1 1 1) called\n"
            + "moveAll() called\n");
    assertEquals(viewOutput.toString(), ""
            + "actionsLog set\n"
            + "ticksPerSecond set\n"
            + "width: 0\n"
            + "height: 0\n"
            + "setController() called\n"
            + "output() called\n");
    assertEquals(controllerOutput.toString(), ""
            + "actionsLog set\n"
            + "model set\n"
            + "view set\n"
            + "reverse set\n"
            + "loop set\n"
            + "ticksPerSecond set\n"
            + "timer set\n"
            + "removeKeyFrame(name, 0) called\n"
            + "run() called\n"
            + "getMovables() called\n"
            + "getMinX() called\n"
            + "getMinY() called\n"
            + "getWidth() called\n"
            + "getHeight() called\n"
            + "getReverse() called\n"
            + "getLoop() called\n"
            + "play() called\n"
            + "pause() called\n"
            + "toggleRewind() called\n"
            + "setTicksPerSecond(10) called\n"
            + "addShape(name, rectangle) called\n"
            + "addShape(name, ellipse) called\n"
            + "removeShape(name) called\n"
            + "toggleLoop() called\n"
            + "addKeyFrame called\n"
            + "removeKeyFrame(name, 0) called\n"
            + "editKeyFrame(args) called\n"
            + "moveAll() called\n"
            + "getMovableNames() called\n"
            + "getKeyFrames() called\n");
  }
}
