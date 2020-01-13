package cs3500.animator.model.movable.keyframe;

import cs3500.animator.model.movable.Movable;

/**
 * Represents a key frame, or an "end-point" in an animation. The shape with this key frame will be
 * at this point in space, size and color, at the time of this {@link KeyFrame}.
 */
public interface KeyFrame {

  /**
   * A method to move a {@link Movable} shape to the appropriate position given this {@link
   * KeyFrame}, a {@link Movable}, and another frame. It will interpolate between the frames and
   * change the shape's values to the correct state.
   *
   * @param movable the {@link Movable} shape to be moved
   * @param kF2 the second {@link KeyFrame} with which to interpolate the motion
   * @throws IllegalArgumentException when either of the arguments are null, and when the time of
   *      the given second frame is less than this frame
   */
  void move(Movable movable, KeyFrame kF2) throws IllegalArgumentException;

  /**
   * A method to retrieve the textual representation of this {@link KeyFrame} in the following
   * format.
   *
   * <p>
   * <code>t1 x1 y1 w1 h1 r1 g1 b1  t1 x1 y1 w1 h1 r1 g1 b1</code>
   * </p>
   *
   * <p>
   * For example:
   * </p>
   * <p>
   * <code> 1 0 0 100 50 0 0 0  1 0 0 100 50 0 0 0</code>
   * </p>
   *
   * @return a {@link String} representing this {@link KeyFrame}
   */
  String write();

  /**
   * A method to retrieve this {@link KeyFrame}'s startTick (starting tick).
   *
   * @return an int representing this {@link KeyFrame}'s starting tick
   */
  int getT1();

  /**
   * A method to retrieve this {@link KeyFrame}'s startX (starting x coordinate).
   *
   * @return a double representing this {@link KeyFrame}'s starting x coordinate
   */
  double getX1();

  /**
   * A method to retrieve this {@link KeyFrame}'s startY (starting y coordinate).
   *
   * @return a double representing this {@link KeyFrame}'s starting y coordinate
   */
  double getY1();

  /**
   * A method to retrieve this {@link KeyFrame}'s starting width.
   *
   * @return a double representing this {@link KeyFrame}'s starting width
   */
  double getW1();

  /**
   * A method to retrieve this {@link KeyFrame}'s starting height.
   *
   * @return a double representing this {@link KeyFrame}'s starting height
   */
  double getH1();

  /**
   * A method to retrieve this {@link KeyFrame}'s starting red color component.
   *
   * @return an int in the range [0, 255] representing this {@link KeyFrame}'s starting red color
   *      component
   */
  int getR1();

  /**
   * A method to retrieve this {@link KeyFrame}'s starting green color component.
   *
   * @return an int in the range [0, 255] representing this {@link KeyFrame}'s starting green color
   *      component
   */
  int getG1();

  /**
   * A method to retrieve this {@link KeyFrame}'s starting blue color component.
   *
   * @return an int in the range [0, 255] representing this {@link KeyFrame}'s starting blue color
   *      component
   */
  int getB1();

}
