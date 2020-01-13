package animator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.ContainsNullMovable;
import cs3500.animator.model.ContainsSameNameMovables;
import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.keyframe.KeyFrame;

/**
 * An implementation of {@link AnimationModel} that represents the screen as a frame with a fixed
 * height and width, a list of {@link Movable} shapes, and a universal ticker integer.
 */
class MockModel implements AnimationModel {

  private StringBuilder actionsLog;
  private final int minX;
  private final int minY;
  private final int width;
  private final int height;
  private ArrayList<Movable> movables;
  private int lastTick;

  /**
   * Constructs a mock model with no size, an empty list of {@link Movable} shapes and tick starting
   * at 0.
   *
   * @param actionsLog the log that record all actions of this model.
   */
  MockModel(StringBuilder actionsLog) {
    this(actionsLog, 0, 0, 0, 0, new ArrayList<>(), 0);
  }

  /**
   * Constructs a mock model with a given height and width, a given list of Movable objects and a
   * given starting tick.
   *
   * @param actionsLog the log that record all actions of this model.
   * @param minX       the frame's left-most x value
   * @param minY       the frame's top-most y value
   * @param width      the frame's width
   * @param height     the frame's height
   * @param movables   the list of {@link Movable} shapes on the frame
   * @param tick       the current tick of the animation
   * @throws IllegalArgumentException when the given height, width, or tick are negative, and when
   *                                  the given set of {@link Movable}s is null
   */
  private MockModel(StringBuilder actionsLog,
                    int minX, int minY, int width, int height, Iterable<Movable> movables, int tick)
          throws IllegalArgumentException {
    if (height < 0 || width < 0) {
      throw new IllegalArgumentException("the height and width may not be negative");
    } else if (tick < 0) {
      throw new IllegalArgumentException("the tick must be positive");
    } else {
      this.actionsLog = actionsLog;
      this.actionsLog.append("actionsLog set\n");
      this.minX = minX;
      this.actionsLog.append("minX set\n");
      this.minY = minY;
      this.actionsLog.append("minY set\n");
      this.width = width;
      this.actionsLog.append("width set\n");
      this.height = height;
      this.actionsLog.append("height set\n");
      this.movables = new ArrayList<>(
              (Collection<? extends Movable>) MockModel.movablesCheck(movables));
      this.actionsLog.append("movables set\n");
      this.setLastTick();
      AnimationModelImpl.TICK = tick;
      this.actionsLog.append("AnimationModelImpl.TICK set\n");
    }
  }

  /**
   * Validates the movables param in the constructor.
   *
   * @param movables the movables this {@link AnimationModel} should have
   * @return the {@link Movable}s this {@link AnimationModel} should have
   * @throws IllegalArgumentException when the given movables are null, contain a null, or when any
   *                                  two movables have the same name
   */

  private static Iterable<Movable> movablesCheck(Iterable<Movable> movables)
          throws IllegalArgumentException {
    if (movables == null) {
      throw new IllegalArgumentException(
              "the list of movables may not be null");
    } else if (new ContainsNullMovable().test(movables)) {
      throw new IllegalArgumentException(
              "the list of movables may not contain a 'null' value");
    } else if (new ContainsSameNameMovables().test(movables)) {
      throw new IllegalArgumentException(
              "the given list of movables contained movables with the same name");
    } else {
      return movables;
    }
  }

  /**
   * Sets the last tick to the last frame of any shape each time the list of shapes is updated.
   */
  private void setLastTick() {
    this.actionsLog.append("setLastTick() called\n");
    int lastTick = Math.max(0, this.lastTick);
    for (Movable movable : this.movables) {
      lastTick = Math.max(movable.getLastTick(), lastTick);
    }
    this.lastTick = lastTick;
  }

  @Override
  public int getLastTick() {
    this.actionsLog.append("getLastTick() called\n");
    return this.lastTick;
  }

  @Override
  public ArrayList<Movable> getMovables() {
    this.actionsLog.append("getMovables() called\n");
    return this.movables;
  }

  @Override
  public double getMinX() {
    this.actionsLog.append("getMinX() called\n");
    return this.minX;
  }

  @Override
  public double getMinY() {
    this.actionsLog.append("getMinY() called\n");
    return this.minY;
  }

  @Override
  public double getWidth() {
    this.actionsLog.append("getWidth() called\n");
    return this.width;
  }

  @Override
  public double getHeight() {
    this.actionsLog.append("getHeight() called\n");
    return this.height;
  }

  @Override
  public void addMovables(Movable... movables) {
    this.actionsLog.append("addMovables(").append(movables.getClass()).append(") called\n");
  }

  @Override
  public void clearMovables() {
    this.actionsLog.append("clearMovables() called\n");
  }

  @Override
  public void clearMovablesIf(Predicate<Movable> predicate) {
    this.actionsLog.append("clearMovablesIf(").append(predicate.getClass()).append(") called\n");
  }

  @Override
  public void removeMovable(String name) {
    this.actionsLog.append("removeMovable(").append(name).append(") called\n");
  }

  @Override
  public void moveAll() {
    this.actionsLog.append("moveAll() called\n");
  }

  @Override
  public String[] getMovableNames() {
    this.actionsLog.append("getMovableNames() called\n");
    return new String[0];
  }

  @Override
  public void addKeyFrame(String name, KeyFrame keyFrame) throws IllegalArgumentException {
    this.actionsLog.append(
            "addKeyFrame(").append(name).append(
            ", ").append(keyFrame.write()).append(") called\n");
  }

  @Override
  public void removeKeyFrame(String name, int index) throws IllegalArgumentException {
    this.actionsLog.append(
            "removeKeyFrame(").append(name).append(", ").append(index).append(") called\n");
  }

  @Override
  public void editKeyFrame(String name, int tick, KeyFrame keyFrame)
          throws IllegalArgumentException {
    this.actionsLog.append(
            "editKeyFrame(").append(name).append(
            ", ").append(tick).append(", ").append(keyFrame.write()).append(") called\n");
  }

  @Override
  public String[] getKeyFrames(String movableName) throws IllegalArgumentException {
    this.actionsLog.append("getKeyFrames(").append(movableName).append(") called\n");
    return new String[0];
  }
}