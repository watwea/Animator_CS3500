package cs3500.animator.controller;

import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.motion.Motion;
import cs3500.animator.view.visual.AnimationFeatures;

/**
 * This interface represents the controller that will be responsible for handling inputs and outputs
 * of the animation window. One object of the controller represents one run of an animation model.
 */
public interface AnimationController extends AnimationFeatures {

  /**
   * Represents a method to run the animation, and take in user input as it comes. It will run the
   * animation with the {@link Movable}s and {@link Motion}s or {@link KeyFrame}s in the board until
   * there are no more {@link Motion}s to run, or until the user enters a quit command.
   *
   * @throws IllegalArgumentException if the given model is null
   * @throws IllegalStateException    if the controller is unable to successfully read input or
   *                                  transmit output
   */

  void run();

  /**
   * Relays the movables from the model to the view.
   *
   * @return an iterable list of {@link Movable} objects.
   */
  Iterable<Movable> getMovables();

  /**
   * Relays the minimum x coordinate from the model to the view.
   *
   * @return a double representing the minimum x coordinate
   */
  double getMinX();

  /**
   * Relays the minimum y coordinate from the model to the view.
   *
   * @return a double representing the minimum y coordinate
   */
  double getMinY();

  /**
   * Relays the width from the model to the view.
   *
   * @return a double representing the width
   */
  double getWidth();

  /**
   * Relays the height from the model to the view.
   *
   * @return a double representing the height
   */
  double getHeight();

  /**
   * Moves the objects. //alskdnalksnflkansdlkasndlkandlkandlansdlknaslkdnalndalsknd
   */
  void moveAll();

  /**
   * Gets a list of the names of the movables.
   */
  String[] getMovableNames();


  /**
   * Gets the boolean that represents whether or not the animation is in reverse or not.
   *
   * @return boolean reverse
   */
  boolean getReverse();

  /**
   * Gets the boolean that represents whether or not the animation is looping or not.
   *
   * @return boolean loop
   */
  boolean getLoop();

  /**
   * Gets a string representation of the keyFrames of a movable.
   *
   * @return
   */
  String[] getKeyFrames(String movableName);
}

