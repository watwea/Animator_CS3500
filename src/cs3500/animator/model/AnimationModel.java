package cs3500.animator.model;

import java.util.ArrayList;
import java.util.function.Predicate;

import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.keyframe.KeyFrame;

/**
 * This interface represents the operations offered by the the animation. One object of the model
 * represents one run of the animation.
 */
public interface AnimationModel {

  /**
   * A method that returns the last tick of this {@link AnimationModel}.
   *
   * @return an int representing the last tick
   */
  int getLastTick();

  /**
   * A method to get the set of Movables in this Model.
   *
   * @return an iterable list of {@link Movable} objects.
   */
  ArrayList<Movable> getMovables();

  /**
   * A method to retrieve the minimum x coordinate from this {@link AnimationModel}.
   *
   * @return a double representing the minimum x coordinate
   */
  double getMinX();

  /**
   * A method to retrieve the minimum y coordinate from this {@link AnimationModel}.
   *
   * @return a double representing the minimum y coordinate
   */
  double getMinY();

  /**
   * A method to retrieve the canvas width from this {@link AnimationModel}.
   *
   * @return a double representing the width
   */
  double getWidth();

  /**
   * A method to retrieve the canvas height from this {@link AnimationModel}.
   *
   * @return a double representing the height
   */
  double getHeight();

  /**
   * A method to add one or multiple {@link Movable}s to this {@link AnimationModel}'s set of
   * motions.
   *
   * @param motions the {@link Movable}s to add to this {@link AnimationModel}'s set of motions.
   */
  void addMovables(Movable... motions);

  /**
   * A method to clear the frame of all of its {@link Movable}s.
   */
  void clearMovables();

  /**
   * A method to clear the frame of all of its {@link Movable}s that pass a given {@link
   * Predicate}.
   */
  void clearMovablesIf(Predicate<Movable> predicate);

  /**
   * A method to remove the {@link Movable} with the given name from the frame.
   */
  void removeMovable(String name);

  /**
   * Moves the movables.
   */
  void moveAll();

  /**
   * Gets a list of the names of the movables.
   */
  String[] getMovableNames();

  /**
   * A method to add a {@link KeyFrame} to a {@link Movable} in this model's list of shapes.
   *
   * @param name     the name of the {@link Movable} which the {@link KeyFrame} will be added to
   * @param keyFrame the frame to be added to the shape
   * @throws IllegalArgumentException when either parameter is null, if there is no such shape with
   *                                  the given shape, or if the given frame is illegal (i.e. if one
   *                                  already exists at that tick)
   */
  void addKeyFrame(String name, KeyFrame keyFrame) throws IllegalArgumentException;

  /**
   * A method to delete the {@link KeyFrame} at a given tick from a {@link Movable} in this model's
   * list of shapes.
   *
   * @param name  the name of the {@link Movable} from which the {@link KeyFrame} will be removed
   * @param index the index of the frame to be removed
   * @throws IllegalArgumentException when the given name is null, if there is no such shape with
   *                                  the given shape, or if there is no frame in the shape with the
   *                                  given index
   */
  void removeKeyFrame(String name, int index) throws IllegalArgumentException;

  /**
   * A method to edit/replace a {@link KeyFrame} at a given tick from a {@link Movable} in this
   * model's list of shapes.
   *
   * @param name     the name of the {@link Movable} from which the {@link KeyFrame} will be
   *                 removed
   * @param index     the index of the frame to be edited/replaced
   * @param keyFrame the new frame that will replace the old frame
   * @throws IllegalArgumentException when the given name or KeyFrame is null, if there is no such
   *                                  shape with the given shape, if there is no frame in the shape
   *                                  with the given index, or if the given frame is illegal
   */
  void editKeyFrame(String name, int index, KeyFrame keyFrame) throws IllegalArgumentException;

  /**
   * Gets a string representation of the keyFrames of a movable.
   */
  String[] getKeyFrames(String movableName);
}
