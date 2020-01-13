package cs3500.animator.view.visual;

/**
 * The features that are to be used in the editor view.
 */

public interface AnimationFeatures {

  /**
   * Plays the animation.
   */
  void play();

  /**
   * Pauses the animation.
   */
  void pause();

  /**
   * Toggles whether or not the animation is going forwards or backwards.
   */
  void toggleRewind();

  /**
   * Sets the tickRate of the animation.
   *
   * @param newDelay the tickrate to which the animation will be set.
   */
  void setTicksPerSecond(int newDelay);

  /**
   * Adds a shape to the animation.
   *
   * @param name the name of the shape to be added.
   * @param type the type of the shape to be added.
   */
  void addShape(String name, String type);

  /**
   * Removes a shape from an animation.
   *
   * @param name the name of the shape to be removed.
   */
  void removeShape(String name);

  /**
   * Toggles whether or not the animation is looping.
   */
  void toggleLoop();

  /**
   * Adds a keyFrame to a movable in an animation.
   *
   * @param name the name of the movable which is getting a keyFrame added to it.
   * @param t1   the time of the keyFrame
   * @param x1   the x coordinate of the keyFrame
   * @param y1   the  coordinate of the keyFrame
   * @param w1   the width of the keyFrame
   * @param h1   the height of the keyFrame
   * @param r1   the red value of the keyFrame
   * @param g1   the green value of the keyFrame
   * @param b1   the blue value of the keyFrame
   */
  void addKeyFrame(String name, int t1, double x1, double y1,
                   double w1, double h1, int r1, int g1, int b1);

  /**
   * Removes a keyFrame from a movable in an animation.
   *
   * @param name  the name of the movable which is having the keyFrame removed.
   * @param index the index of the keyFrame that will be removed.
   */
  void removeKeyFrame(String name, int index);

  /**
   * Edits a keyframe of a movable in a animation.
   *
   * @param name the name of the movable which is getting a keyFrame edited.
   * @param t1   the time of the new keyFrame
   * @param x1   the x coordinate of the new keyFrame
   * @param y1   the  coordinate of the new keyFrame
   * @param w1   the width of the new keyFrame
   * @param h1   the height of the new keyFrame
   * @param r1   the red value of the new keyFrame
   * @param g1   the green value of the new keyFrame
   * @param b1   the blue value of the new keyFrame
   */
  void editKeyFrame(String name, int t1, double x1, double y1,
                    double w1, double h1, int r1, int g1, int b1);
}
