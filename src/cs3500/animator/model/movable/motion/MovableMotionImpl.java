package cs3500.animator.model.movable.motion;

import java.awt.Color;

import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.keyframe.KeyFrame;

import static cs3500.animator.model.AnimationModelImpl.TICK;

/**
 * Represents an implementation of {@link Motion} that has starting and ending values for the
 * associated Movable's center, color, and size, as well as a range of ticks to complete the
 * movement (in the form of a smooth transition of even steps over the ticks).
 */
public class MovableMotionImpl implements Motion {
  private final int t1;
  private final double x1;
  private final double y1;
  private final double w1;
  private final double h1;
  private final int r1;
  private final int g1;
  private final int b1;
  private final int t2;
  private final double x2;
  private final double y2;
  private final double w2;
  private final double h2;
  private final int r2;
  private final int g2;
  private final int b2;

  /**
   * Constructs a {@link Motion} from two given {@link KeyFrame}s.
   *
   * @param kF1 the frame representing the start of this {@link Motion}
   * @param kF2 the frame representing the end of this {@link Motion}
   * @throws IllegalArgumentException when either given frame is null, or if the second frame occurs
   *                                  before the first frame
   */
  public MovableMotionImpl(KeyFrame kF1, KeyFrame kF2) throws IllegalArgumentException {
    this(
            checkKeyFrame(kF1).getT1(), checkKeyFrame(kF1).getX1(), checkKeyFrame(kF1).getY1(),
            checkKeyFrame(kF1).getW1(), checkKeyFrame(kF1).getH1(),
            checkKeyFrame(kF1).getR1(), checkKeyFrame(kF1).getG1(), checkKeyFrame(kF1).getB1(),
            checkKeyFrame(kF2).getT1(), checkKeyFrame(kF2).getX1(), checkKeyFrame(kF2).getY1(),
            checkKeyFrame(kF2).getW1(), checkKeyFrame(kF2).getH1(),
            checkKeyFrame(kF2).getR1(), checkKeyFrame(kF2).getG1(), checkKeyFrame(kF2).getB1());
  }

  private static KeyFrame checkKeyFrame(KeyFrame keyFrame) {
    if (keyFrame == null) {
      throw new IllegalArgumentException("the given KeyFrame may not be null");
    } else {
      return keyFrame;
    }
  }

  /**
   * Constructs a {@link MovableMotionImpl} with the given starting and ending values.
   *
   * @param t1 the starting tick
   * @param x1 the starting x coordinate
   * @param y1 the starting y coordinate
   * @param w1 the starting width
   * @param h1 the starting height
   * @param r1 the starting red value
   * @param g1 the starting green value
   * @param b1 the starting blue value
   * @param t2 the ending tick
   * @param x2 the ending x coordinate
   * @param y2 the ending y coordinate
   * @param w2 the ending width
   * @param h2 the ending height
   * @param r2 the ending red value
   * @param g2 the ending green value
   * @param b2 the ending blue value
   * @throws IllegalArgumentException when the ticks are less than 1, when the ending tick is less
   *                                  than the starting tick, the heights and widths are negative,
   *                                  or when the rgb values are negative
   */
  public MovableMotionImpl(int t1, double x1, double y1, double w1, double h1,
                           int r1, int g1, int b1,
                           int t2, double x2, double y2, double w2, double h2,
                           int r2, int g2, int b2)
          throws IllegalArgumentException {
    if (t1 < 0 || t2 < 0) {
      throw new IllegalArgumentException(
              "the ticks may not be less than 0");
    } else if (t1 > t2) {
      throw new IllegalArgumentException(
              "the starting tick may not be greater than the ending tick");
    } else if (w1 < 0 || h1 < 0 || w2 < 0 || h2 < 0) {
      throw new IllegalArgumentException(
              "the given widths and heights must be positive");
    } else if (r1 < 0 || g1 < 0 || b1 < 0 || r2 < 0 || g2 < 0 || b2 < 0
            || r1 > 255 || g1 > 255 || b1 > 255 || r2 > 255 || g2 > 255 || b2 > 255) {
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
      this.t2 = t2;
      this.x2 = x2;
      this.y2 = y2;
      this.w2 = w2;
      this.h2 = h2;
      this.r2 = r2;
      this.g2 = g2;
      this.b2 = b2;
    }
  }

  @Override
  public void move(Movable movable) {
    if (movable == null) {
      throw new IllegalArgumentException("the movable may not be null");
    }
    if (t1 <= TICK && TICK <= t2) {
      double dX = x2 - x1;
      double dY = y2 - y1;
      int dT = Math.max(t2 - t1, 1);
      int dR = r2 - r1;
      int dG = g2 - g1;
      int dB = b2 - b1;
      double dW = w2 - w1;
      double dH = h2 - h1;
      double wPerTick = dW / dT;
      double hPerTick = dH / dT;
      double xPerTick = dX / dT;
      double yPerTick = dY / dT;
      int rPerTick = (dR / dT);
      int gPerTick = (dG / dT);
      int bPerTick = (dB / dT);
      int partialTick = Math.max(TICK - t1,1);

      if (TICK != t2) {
        movable.setColor(new Color(
                r1 + (rPerTick * partialTick),
                g1 + (gPerTick * partialTick),
                b1 + (bPerTick * partialTick)));
        movable.setX(x1 + (xPerTick * partialTick));
        movable.setY(y1 + (yPerTick * partialTick));
        movable.setHeight(h1 + (hPerTick * partialTick));
        movable.setWidth(w1 + (wPerTick * partialTick));
      } else {
        movable.setColor(new Color(r2, g2, b2));
        movable.setX(x2);
        movable.setY(y2);
        movable.setWidth(w2);
        movable.setHeight(h2);
      }
    }
  }

  @Override
  public String write() {
    StringBuilder builder = new StringBuilder();
    builder.append(this.t1).append(" ");
    builder.append(this.x1).append(" ");
    builder.append(this.y1).append(" ");
    builder.append(this.w1).append(" ");
    builder.append(this.h1).append(" ");
    builder.append(this.r1).append(" ");
    builder.append(this.g1).append(" ");
    builder.append(this.b1).append("  ");
    builder.append(this.t2).append(" ");
    builder.append(this.x2).append(" ");
    builder.append(this.y2).append(" ");
    builder.append(this.w2).append(" ");
    builder.append(this.h2).append(" ");
    builder.append(this.r2).append(" ");
    builder.append(this.g2).append(" ");
    builder.append(this.b2);
    return builder.toString();
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

  @Override
  public int getT2() {
    return this.t2;
  }

  @Override
  public double getX2() {
    return this.x2;
  }

  @Override
  public double getY2() {
    return this.y2;
  }

  @Override
  public double getW2() {
    return this.w2;
  }

  @Override
  public double getH2() {
    return this.h2;
  }

  @Override
  public int getR2() {
    return this.r2;
  }

  @Override
  public int getG2() {
    return this.g2;
  }

  @Override
  public int getB2() {
    return this.b2;
  }

}
