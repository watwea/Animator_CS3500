package animator;

import javax.swing.Timer;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.movable.AbstractMovableImpl;
import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.keyframe.MovableKeyFrameImpl;
import cs3500.animator.view.AnimationView;

import static cs3500.animator.model.AnimationModelImpl.currentTick;
import static cs3500.animator.model.AnimationModelImpl.nextTick;
import static cs3500.animator.model.AnimationModelImpl.prevTick;

/**
 * Represents a mock controller of the animator that runs the animation forwards, and loops by
 * default.
 */

class MockController implements AnimationController {

  private StringBuilder actionsLog;
  private final AnimationModel model;
  private final AnimationView view;
  private boolean reverse;
  private boolean loop;
  Timer timer;

  /**
   * Creates a mock model.
   *
   * @param model the model from which to run the animation.
   * @param view  the view in which the output is viewed.
   */

  MockController(AnimationModel model, AnimationView view, StringBuilder actionsLog) {
    if (model == null) {
      throw new IllegalArgumentException("the model may not be null");
    } else if (view == null) {
      throw new IllegalArgumentException("the view may not be null");
    } else {
      this.actionsLog = actionsLog;
      this.actionsLog.append("actionsLog set\n");
      this.model = model;
      this.actionsLog.append("model set\n");
      this.view = view;
      this.actionsLog.append("view set\n");
      this.reverse = false;
      this.actionsLog.append("reverse set\n");
      this.loop = true;
      this.actionsLog.append("loop set\n");
      int ticksPerSecond = this.view.setController(this);
      this.actionsLog.append("ticksPerSecond set\n");
      this.timer = new Timer(1000 / ticksPerSecond, e -> {
        this.ticker();
        this.view.output();
      });
      this.actionsLog.append("timer set\n");
    }
  }

  @Override
  public void run() {
    this.actionsLog.append("run() called\n");
    this.timer.start();
    this.view.output();
  }

  @Override
  public Iterable<Movable> getMovables() {
    this.actionsLog.append("getMovables() called\n");
    return this.model.getMovables();
  }

  @Override
  public double getMinX() {
    this.actionsLog.append("getMinX() called\n");
    return this.model.getMinX();
  }

  @Override
  public double getMinY() {
    this.actionsLog.append("getMinY() called\n");
    return this.model.getMinY();
  }

  @Override
  public double getWidth() {
    this.actionsLog.append("getWidth() called\n");
    return this.model.getWidth();
  }

  @Override
  public double getHeight() {
    this.actionsLog.append("getHeight() called\n");
    return this.model.getHeight();
  }

  @Override
  public boolean getReverse() {
    this.actionsLog.append("getReverse() called\n");
    return this.reverse;
  }

  @Override
  public boolean getLoop() {
    this.actionsLog.append("getLoop() called\n");
    return this.loop;
  }

  @Override
  public void play() {
    this.actionsLog.append("play() called\n");
    timer.start();
  }

  @Override
  public void pause() {
    this.actionsLog.append("pause() called\n");
    timer.stop();
  }

  @Override
  public void toggleRewind() {
    this.actionsLog.append("toggleRewind() called\n");
    this.reverse = true;
  }

  @Override
  public void setTicksPerSecond(int newDelay) {
    this.actionsLog.append("setTicksPerSecond(").append(newDelay).append(") called\n");
    this.timer.setDelay(newDelay);
  }

  @Override
  public void addShape(String name, String type) {
    this.actionsLog.append(
            "addShape(").append(name).append(", ").append(type).append(") called\n");
    this.model.addMovables(new AbstractMovableImpl.MovableBuilder().buildMovable(name, type));
  }

  @Override
  public void removeShape(String name) {
    this.actionsLog.append("removeShape(").append(name).append(") called\n");
    this.model.removeMovable(name);
  }

  @Override
  public void toggleLoop() {
    this.actionsLog.append("toggleLoop() called\n");
    this.loop = !loop;
  }

  @Override
  public void addKeyFrame(String name, int t1, double x1, double y1,
                          double w1, double h1, int r1, int g1, int b1) {
    this.actionsLog.append("addKeyFrame called\n");
    KeyFrame keyFrame = new MovableKeyFrameImpl(t1, x1, y1, w1, h1, r1, g1, b1);
    this.model.addKeyFrame(name, keyFrame);
  }

  @Override
  public void removeKeyFrame(String name, int index) {
    this.actionsLog.append(
            "removeKeyFrame(").append(name).append(", ").append(index).append(") called\n");
    this.model.removeKeyFrame(name, index);
  }

  @Override
  public void editKeyFrame(String name, int t1, double x1, double y1,
                           double w1, double h1, int r1, int g1, int b1) {
    this.actionsLog.append("editKeyFrame(args) called\n");
    KeyFrame keyFrame = new MovableKeyFrameImpl(t1, x1, y1, w1, h1, r1, g1, b1);
    this.model.editKeyFrame(name, t1, keyFrame);

  }

  /**
   * Pushes the tick forward/backward depending on this.reverse, and sets the tick to the last tick
   * or 0, depending on the current tick and this.loop.
   */
  private void ticker() {
    this.actionsLog.append("ticker() called\n");
    if (!this.reverse) {
      if (this.loop && currentTick() > this.model.getLastTick()) {
        AnimationModelImpl.TICK = 0;
      }
      nextTick();
    } else {
      if (this.loop && currentTick() <= 0) {
        AnimationModelImpl.TICK = this.model.getLastTick();
      }
      try {
        prevTick();
      } catch (IllegalStateException ignored) {
      }
    }
  }

  public void moveAll() {
    this.actionsLog.append("moveAll() called\n");
    model.moveAll();
  }

  public String[] getMovableNames() {
    this.actionsLog.append("getMovableNames() called\n");
    return new String[]{"A bunch of names"};
  }

  public String[] getKeyFrames(String movableName) {
    this.actionsLog.append("getKeyFrames() called\n");
    return new String[]{"A bunch of frames"};
  }
}
