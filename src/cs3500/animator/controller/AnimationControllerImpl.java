package cs3500.animator.controller;

import javax.swing.Timer;

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
 * Represents a controller of the animator that runs the animation forwards, and loops by default.
 */

public class AnimationControllerImpl implements AnimationController {

  private final AnimationModel model;
  private final AnimationView view;
  private boolean reverse;
  private boolean loop;
  private Timer timer;

  /**
   * Creates an AnimationControllerImpl.
   *
   * @param model the model from which to run the animation.
   * @param view  the view in which the output is viewed.
   */

  public AnimationControllerImpl(AnimationModel model, AnimationView view) {
    if (model == null) {
      throw new IllegalArgumentException("the model may not be null");
    } else if (view == null) {
      throw new IllegalArgumentException("the view may not be null");
    } else {
      this.model = model;
      this.view = view;
      this.reverse = false;
      this.loop = true;
      int ticksPerSecond = this.view.setController(this);
      this.timer = new Timer(1000 / ticksPerSecond, e -> {
        this.ticker();
        this.view.output();
      });
    }
  }

  @Override
  public void run() {
    this.timer.start();
    this.view.output();
  }

  @Override
  public Iterable<Movable> getMovables() {
    return this.model.getMovables();
  }

  @Override
  public double getMinX() {
    return this.model.getMinX();
  }

  @Override
  public double getMinY() {
    return this.model.getMinY();
  }

  @Override
  public double getWidth() {
    return this.model.getWidth();
  }

  @Override
  public double getHeight() {
    return this.model.getHeight();
  }

  @Override
  public boolean getReverse() {
    return this.reverse;
  }

  @Override
  public boolean getLoop() {
    return this.loop;
  }

  @Override
  public void play() {
    timer.start();
  }

  @Override
  public void pause() {
    timer.stop();
  }

  @Override
  public void toggleRewind() {
    this.reverse = true;
  }

  @Override
  public void setTicksPerSecond(int newDelay) {
    this.timer.setDelay(newDelay);
  }

  @Override
  public void addShape(String name, String type) {
    this.model.addMovables(new AbstractMovableImpl.MovableBuilder().buildMovable(name, type));
  }

  @Override
  public void removeShape(String name) {
    this.model.removeMovable(name);
  }

  @Override
  public void toggleLoop() {
    this.loop = !loop;
  }

  @Override
  public void addKeyFrame(String name, int t1, double x1, double y1,
                          double w1, double h1, int r1, int g1, int b1) {
    KeyFrame keyFrame = new MovableKeyFrameImpl(t1, x1, y1, w1, h1, r1, g1, b1);
    this.model.addKeyFrame(name, keyFrame);
  }

  @Override
  public void removeKeyFrame(String name, int index) {
    this.model.removeKeyFrame(name, index);
  }

  @Override
  public void editKeyFrame(String name, int t1, double x1, double y1,
                           double w1, double h1, int r1, int g1, int b1) {
    KeyFrame keyFrame = new MovableKeyFrameImpl(t1, x1, y1, w1, h1, r1, g1, b1);
    this.model.editKeyFrame(name, t1, keyFrame);

  }

  /**
   * Pushes the tick forward/backward depending on this.reverse, and sets the tick to the last tick
   * or 0, depending on the current tick and this.loop.
   */
  private void ticker() {
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

  public void moveAll() {  //akjsbdkjabsdkjabsdkjbadjkbasd
    model.moveAll();
  }

  public String[] getMovableNames() {
    return this.model.getMovableNames();
  }

  public String[] getKeyFrames(String movableName) {
    return model.getKeyFrames(movableName);
  }
}
