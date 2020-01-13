package cs3500.animator.view;

import cs3500.animator.controller.AnimationController;

/**
 * This interface represents the view that will be responsible to output the animation window. One
 * object of the view represents one run of an animation model.
 */
public interface AnimationView {

  /**
   * A method to retrieve the {@link String} representation of this animation.
   *
   * @return a {@link String} representing this animation
   */
  String render();

  /**
   * A method to output this animation to the selected output or {@link System}<code>.out</code> by
   * default.
   */
  void output();

  /**
   * Sets the controller of this view to the given {@link AnimationController} and returns the tick
   * rate to the controller.
   */
  int setController(AnimationController controller);

}
