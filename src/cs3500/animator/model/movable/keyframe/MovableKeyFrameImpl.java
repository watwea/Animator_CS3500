package cs3500.animator.model.movable.keyframe;

import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.motion.Motion;
import cs3500.animator.model.movable.motion.MovableMotionImpl;


/**
 * Represents an implementation of {@link KeyFrame} that has values for the associated Movable's
 * center, color, and size, as well as a the tick at which the shapw will be in this state.
 */
public class MovableKeyFrameImpl implements KeyFrame {

  private final int t1;
  private final double x1;
  private final double y1;
  private final double w1;
  private final double h1;
  private final int r1;
  private final int g1;
  private final int b1;

  /**
   * Constructs a KeyFrame from given tick, position, size, and color values.
   *
   * @param t1 the starting tick
   * @param x1 the starting x coordinate
   * @param y1 the starting y coordinate
   * @param w1 the starting width
   * @param h1 the starting height
   * @param r1 the starting red value
   * @param g1 the starting green value
   * @param b1 the starting blue value
   * @throws IllegalArgumentException when the tick is less than 1, when the height and width are
   *      negative, or when the rgb values are not between 0 and 255
   */
  public MovableKeyFrameImpl(int t1, double x1, double y1,
                             double w1, double h1, int r1, int g1, int b1)
          throws IllegalArgumentException {
    if (t1 < 0) {
      throw new IllegalArgumentException(
              "the tick may not be less than 0");
    } else if (w1 < 0 || h1 < 0) {
      throw new IllegalArgumentException(
              "the given width and height must be positive");
    } else if (r1 < 0 || g1 < 0 || b1 < 0
            || r1 > 255 || g1 > 255 || b1 > 255) {
      throw new IllegalArgumentException(
              "the given RGB components must be in the range [0, 255]");
    } else {
      this.t1 = t1;
      this.x1 = x1;
      this.y1 = y1;
      this.w1 = w1;
      this.h1 = h1;
      this.r1 = r1;
      this.g1 = g1;
      this.b1 = b1;
    }
  }

  @Override
  public void move(Movable movable, KeyFrame kF2) {
    if (movable == null) {
      throw new IllegalArgumentException("the given movable may not be null");
    } else if (kF2 == null) {
      throw new IllegalArgumentException("the given second frame may not be null");
    } else {
      Motion motion = new MovableMotionImpl(this, kF2);
      motion.move(movable);
    }
  }

  @Override
  public String write() {
    Motion motion = new MovableMotionImpl(this, this);
    return motion.write();
  }

  @Override
  public int getT1() {
    return this.t1;
  }

  @Override
  public double getX1() {
    return this.x1;
  }

  @Override
  public double getY1() {
    return this.y1;
  }

  @Override
  public double getW1() {
    return this.w1;
  }

  @Override
  public double getH1() {
    return this.h1;
  }

  @Override
  public int getR1() {
    return this.r1;
  }

  @Override
  public int getG1() {
    return this.g1;
  }

  @Override
  public int getB1() {
    return this.b1;
  }
}
