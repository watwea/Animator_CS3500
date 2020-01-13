package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.motion.Motion;

import static cs3500.animator.util.KeyFrameAdapterMotion.keyFramesToMotions;

/**
 * An implementation of {@link AnimationModel} that represents the screen as a frame with a fixed
 * height and width, a list of {@link Movable} shapes, and a universal ticker integer.
 */
public class AnimationModelImpl implements AnimationModel {

  private final int minX;
  private final int minY;
  private final int width;
  private final int height;
  private ArrayList<Movable> movables;
  private int lastTick;

  public static int TICK;

  /**
   * Constructs a model with no size, an empty list of {@link Movable} shapes and tick starting at
   * 0.
   */
  public AnimationModelImpl() {
    this(0, 0, 0, 0, new ArrayList<>(), 0);
  }

  /**
   * Constructs a model with a given height and width, a given list of Movable objects and a given
   * starting tick.
   *
   * @param minX     the frame's left-most x value
   * @param minY     the frame's top-most y value
   * @param width    the frame's width
   * @param height   the frame's height
   * @param movables the list of {@link Movable} shapes on the frame
   * @param tick     the current tick of the animation
   * @throws IllegalArgumentException when the given height, width, or tick are negative, and when
   *                                  the given set of {@link Movable}s is null
   */
  public AnimationModelImpl(
          int minX, int minY, int width, int height, Iterable<Movable> movables, int tick)
          throws IllegalArgumentException {
    if (height < 0 || width < 0) {
      throw new IllegalArgumentException("the height and width may not be negative");
    } else if (tick < 0) {
      throw new IllegalArgumentException("the tick must be positive");
    } else {
      this.minX = minX;
      this.minY = minY;
      this.width = width;
      this.height = height;
      this.movables = new ArrayList<>(
              (Collection<? extends Movable>) AnimationModelImpl.movablesCheck(movables));
      int lastTick = 0;
      this.setLastTick();
      AnimationModelImpl.TICK = tick;
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
    int lastTick = Math.max(0, this.lastTick);
    for (Movable movable : this.movables) {
      lastTick = Math.max(movable.getLastTick(), lastTick);
    }
    this.lastTick = lastTick;
  }

  @Override
  public int getLastTick() {
    return this.lastTick;
  }

  /**
   * A method that returns the current tick of this {@link AnimationModel}.
   *
   * @return an int representing the current tick
   */
  public static int currentTick() {
    return TICK;
  }

  /**
   * A method that advances the tick of this {@link AnimationModel} by 1.
   */
  public static void nextTick() {
    TICK++;
  }

  /**
   * A method that reverses the tick of this {@link AnimationModel} by 1.
   *
   * @throws IllegalStateException when the tick is 1 or lower and this method is called
   */
  public static void prevTick() throws IllegalStateException {
    if (currentTick() < 1) {
      throw new IllegalStateException("the previous tick cannot be called if the tick is not "
              + "1 or greater");
    } else {
      TICK--;
    }
  }

  @Override
  public ArrayList<Movable> getMovables() {
    return this.movables;
  }

  @Override
  public double getMinX() {
    return this.minX;
  }

  @Override
  public double getMinY() {
    return this.minY;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public void addMovables(Movable... movables) {
    this.movables.addAll(
            (Collection<? extends Movable>) AnimationModelImpl.movablesCheck(
                    Arrays.asList(movables)));
    this.setLastTick();
  }

  @Override
  public void clearMovables() {
    this.movables.clear();
    this.setLastTick();
  }

  @Override
  public void clearMovablesIf(Predicate<Movable> predicate) {
    if (predicate == null) {
      throw new IllegalArgumentException("the predicate may not be null");
    }
    ArrayList<Movable> arr = new ArrayList<>(this.movables);
    for (Movable m : arr) {
      if (predicate.test(m)) {
        this.movables.remove(m);
      }
    }
    this.setLastTick();
  }

  @Override
  public void removeMovable(String name) {
    ArrayList<Movable> arr = new ArrayList<>(this.movables);
    for (Movable m : arr) {
      if (m.getMovableName().equals(name)) {
        this.movables.remove(m);
      }
    }
    this.setLastTick();
  }

  @Override
  public void moveAll() { //asldnasldnalkdnlkasndlkandlkasndlkasnd
    for (Movable movable : this.getMovables()) {
      Iterable<KeyFrame> keyFrames = movable.getKeyFrames();
      Iterable<Motion> motions = keyFramesToMotions(keyFrames);
      for (Motion motion : motions) {
        motion.move(movable);
      }
    }
  }

  @Override
  public String[] getMovableNames() {
    String[] acc = new String[(this.getMovables().size())];
    for (int i = 0; i < (this.getMovables()).size(); i++) {
      acc[i] = this.getMovables().get(i).getMovableName();
    }
    return acc;
  }

  @Override
  public void addKeyFrame(String name, KeyFrame keyFrame) throws IllegalArgumentException {
    boolean shapeExists = false;
    for (Movable movable : this.movables) {
      if (movable.getMovableName().equals(name)) {
        shapeExists = true;
        movable.addKeyFrames(keyFrame);
      }
    }
    if (!shapeExists) {
      throw new IllegalArgumentException(
              "there is no shape with the given name in this AnimationModel");
    }
  }

  @Override
  public void removeKeyFrame(String name, int index) throws IllegalArgumentException {
    boolean shapeExists = false;
    for (Movable movable : this.movables) {
      if (movable.getMovableName().equals(name)) {
        shapeExists = true;
        try {
          movable.removeKeyFrameAt(index);
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException(
                  "there was the following error deleting the given KeyFrame: " + e.getMessage());
        }
      }
    }
    if (!shapeExists) {
      throw new IllegalArgumentException(
              "there is no shape with the given name in this AnimationModel");
    }
  }

  @Override
  public void editKeyFrame(String name, int tick, KeyFrame keyFrame)
          throws IllegalArgumentException {
    boolean shapeExists = false;
    for (Movable movable : this.movables) {
      if (movable.getMovableName().equals(name)) {
        shapeExists = true;
        try {
          movable.removeKeyFrameAt(tick);
          movable.addKeyFrames(keyFrame);
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException(
                  "there was the following error editing the given KeyFrame: " + e.getMessage());
        }
      }
    }
    if (!shapeExists) {
      throw new IllegalArgumentException(
              "there is no shape with the given name in this AnimationModel");
    }

  }

  @Override
  public String[] getKeyFrames(String movableName) throws IllegalArgumentException {
    for (Movable movable : this.getMovables()) {
      if (movable.getMovableName().equals(movableName)) {
        String[] frames = new String[((ArrayList) (movable.getKeyFrames())).size()];
        int i = 0;
        for (KeyFrame frame : movable.getKeyFrames()) {
          StringBuilder frameStr = new StringBuilder();
          frameStr.append("T: ").append(frame.getT1()).append(" ");
          frameStr.append("X: ").append(frame.getX1()).append(" ");
          frameStr.append("Y: ").append(frame.getY1()).append(" ");
          frameStr.append("W: ").append(frame.getW1()).append(" ");
          frameStr.append("H: ").append(frame.getH1()).append(" ");
          frameStr.append("R: ").append(frame.getR1()).append(" ");
          frameStr.append("G: ").append(frame.getG1()).append(" ");
          frameStr.append("B: ").append(frame.getB1());
          frames[i] = frameStr.toString();
          i++;
        }
        return frames;
      }
    }
    throw new IllegalArgumentException(
            "there is no shape with the given name in this AnimationModel");
  }

}

