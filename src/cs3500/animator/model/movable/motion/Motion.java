package cs3500.animator.model.movable.motion;

import cs3500.animator.model.movable.Movable;

/**
 * Represents the change in state of a Movable at a certain point of time.
 */
public interface Motion {

  /**
   * Applies this {@link Motion} to the given {@link Movable} object based on the current {@code
   * AnimationModelImpl.tick}. If the 'tick' falls within the range of this Motion, the Movable is
   * shifted in the proper direction, scaled by the proper factor, and color-shifted by the correct
   * amount. The amount of change in one tick is determined by the absolute change from start to
   * finish of this Motion divided by the total number of ticks in this Motion.
   *
   * @param movable the Movable that will receive this Motion
   * @throws IllegalArgumentException when the given {@link Movable} is null
   */
  void move(Movable movable) throws IllegalArgumentException;

  /**
   * A method to retrieve the textual representation of this {@link Motion} in the following
   * format.
   *
   * <p>
   * <code>t1 x1 y1 w1 h1 r1 g1 b1  t2 x2 y2 w2 h2 r2 g2 b2</code>
   * </p>
   *
   * <p>
   * For example:
   * </p>
   * <p>
   * <code> 1 0 0 100 50 0 0 0  100 0 0 50 100 255 255 255</code>
   * </p>
   *
   * @return a {@link String} representing this {@link Motion}
   */
  String write();

  /**
   * A method to retrieve this {@link Motion}'s startX (starting x coordinate).
   *
   * @return a double representing this {@link Motion}'s starting x coordinate
   */
  double getX1();

  /**
   * A method to retrieve this {@link Motion}'s startY (starting y coordinate).
   *
   * @return a double representing this {@link Motion}'s starting y coordinate
   */
  double getY1();

  /**
   * A method to retrieve this {@link Motion}'s endX (ending x coordinate).
   *
   * @return a double representing this {@link Motion}'s ending x coordinate
   */
  double getX2();

  /**
   * A method to retrieve this {@link Motion}'s endY (ending y coordinate).
   *
   * @return a double representing this {@link Motion}'s ending y coordinate
   */
  double getY2();

  /**
   * A method to retrieve this {@link Motion}'s startTick (starting tick).
   *
   * @return an int representing this {@link Motion}'s starting tick
   */
  int getT1();

  /**
   * A method to retrieve this {@link Motion}'s endTick (ending tick).
   *
   * @return an int representing this {@link Motion}'s ending tick
   */
  int getT2();

  /**
   * A method to retrieve this {@link Motion}'s starting width.
   *
   * @return a double representing this {@link Motion}'s starting width
   */
  double getW1();

  /**
   * A method to retrieve this {@link Motion}'s starting height.
   *
   * @return a double representing this {@link Motion}'s starting height
   */
  double getH1();

  /**
   * A method to retrieve this {@link Motion}'s ending width.
   *
   * @return a double representing this {@link Motion}'s ending width
   */
  double getW2();

  /**
   * A method to retrieve this {@link Motion}'s ending height.
   *
   * @return a double representing this {@link Motion}'s ending height
   */
  double getH2();

  /**
   * A method to retrieve this {@link Motion}'s starting red color component.
   *
   * @return an int in the range [0, 255] representing this {@link Motion}'s starting red color
   *      component
   */
  int getR1();

  /**
   * A method to retrieve this {@link Motion}'s starting green color component.
   *
   * @return an int in the range [0, 255] representing this {@link Motion}'s starting green color
   *      component
   */
  int getG1();

  /**
   * A method to retrieve this {@link Motion}'s starting blue color component.
   *
   * @return an int in the range [0, 255] representing this {@link Motion}'s starting blue color
   *      component
   */
  int getB1();

  /**
   * A method to retrieve this {@link Motion}'s starting red color component.
   *
   * @return an int in the range [0, 255] representing this {@link Motion}'s starting red color
   *      component
   */
  int getR2();

  /**
   * A method to retrieve this {@link Motion}'s starting green color component.
   *
   * @return an int in the range [0, 255] representing this {@link Motion}'s starting green color
   *      component
   */
  int getG2();

  /**
   * A method to retrieve this {@link Motion}'s starting blue color component.
   *
   * @return an int in the range [0, 255] representing this {@link Motion}'s starting blue color
   *      component
   */
  int getB2();

}
