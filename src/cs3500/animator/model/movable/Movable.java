package cs3500.animator.model.movable;

import java.awt.Color;

import cs3500.animator.model.movable.keyframe.KeyFrame;

/**
 * Represents a movable object which has a cartesian coordinate position, a size, and an rgba color.
 * It also contains a set of associated {@link KeyFrame}s.
 */
public interface Movable {

  /**
   * A method to write this {@link Movable}'s data as a String in the following format.
   * <p>
   * <code>shape movableName type</code>
   * </p>
   *
   * <p>
   * For example:
   * </p>
   * <p>
   * <code>shape R1 rectangle</code>
   * </p>
   *
   * @return a {@link String} representing this {@link Movable}
   */
  String write();

  /**
   * A method to write this {@link Movable}'s data as a SVG format String based on the given tick
   * rate.
   *
   * @param ticksPerSec the tick rate of this animation
   * @return a {@link String} representing this {@link Movable} and its {@link KeyFrame}s in proper
   *      SVG syntax
   */
  String writeSVG(double ticksPerSec);

  /**
   * A method to retrieve this {@link Movable}'s name.
   *
   * @return a {@link String} representing the name
   */
  String getMovableName();

  /**
   * A method to retrieve this {@link Movable}'s type.
   *
   * @return a {@link AbstractMovableImpl.movableType} representing the shape type
   */
  AbstractMovableImpl.movableType getType();

  /**
   * A method to retrieve this {@link Movable} object's key frames.
   *
   * @return an iterable set of {@link KeyFrame}s that represents this {@link Movable}'s movements
   */
  Iterable<KeyFrame> getKeyFrames();

  /**
   * A method to retrieve this {@link Movable} object's cartesian coordinate x position.
   *
   * @return a double that represents this {@link Movable} object's x-coordinate
   */
  double getX();

  /**
   * A method to retrieve this {@link Movable} object's cartesian coordinate y position.
   *
   * @return a double that represents this {@link Movable} object's y-coordinate
   */
  double getY();

  /**
   * A method to retrieve this {@link Movable} object's width.
   *
   * @return a double that represents this {@link Movable} object's width
   */
  double getWidth();

  /**
   * A method to retrieve this {@link Movable} object's height.
   *
   * @return a double that represents this {@link Movable} object's height
   */
  double getHeight();

  /**
   * A method to retrieve this {@link Movable} object's rgba color.
   *
   * @return a {@link Color} that represents this {@link Movable} object's color
   */
  Color getColor();

  /**
   * A method to retrieve the last tick of any {@link KeyFrame}s in this shape's list of frames.
   */
  int getLastTick();

  /**
   * A method to set this {@link Movable} object's x coordinate to the given value.
   *
   * @param x the x-coordinate to place this {@link Movable} at
   */
  void setX(double x);

  /**
   * A method to set this {@link Movable} object's y coordinate to the given value.
   *
   * @param y the y-coordinate to place this {@link Movable} at
   */
  void setY(double y);

  /**
   * A method to set this {@link Movable} object's height to the given value.
   *
   * @param height the height to give this {@link Movable}
   */
  void setHeight(double height);

  /**
   * A method to set this {@link Movable} object's width to the given value.
   *
   * @param width the width to give this {@link Movable}
   */
  void setWidth(double width);

  /**
   * A method to set this {@link Movable} object's rgba color to the given value.
   *
   * @param color the {@link Color} to give this {@link Movable}
   */
  void setColor(Color color);

  /**
   * A method to set this {@link Movable} object's set of {@link KeyFrame}s to the given value.
   *
   * @param keyFrames the {@link KeyFrame}s to give this {@link Movable}
   */
  void setKeyFrames(KeyFrame... keyFrames);

  /**
   * A method to set this {@link Movable} object's set of {@link KeyFrame}s to the given value.
   *
   * @param keyFrames the {@link KeyFrame}s to give this {@link Movable}
   */
  void setKeyFrames(Iterable<KeyFrame> keyFrames);

  /**
   * A method to add one or multiple {@link KeyFrame}s to this {@link Movable}'s set of key frames.
   *
   * @param keyFrames the {@link KeyFrame}s to give this {@link Movable}
   */
  void addKeyFrames(KeyFrame... keyFrames);

  /**
   * A method to remove the {@link KeyFrame} at the given index from this {@link Movable}'s set of
   * key frames.
   *
   * @param index the index at which the frame to be removed exists
   * @throws IllegalArgumentException when there is no {@link KeyFrame} at the given index
   */
  void removeKeyFrameAt(int index) throws IllegalArgumentException;

}
