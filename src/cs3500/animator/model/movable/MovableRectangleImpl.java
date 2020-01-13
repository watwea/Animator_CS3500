package cs3500.animator.model.movable;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.model.movable.motion.Motion;

/**
 * A concrete implementation of {@link Movable} that represents a rectangular shape. Its x and y
 * fields represent the coordinated of the top left corner, the width and height represent tradition
 * width and height, and the color represents an rgb color
 */
public class MovableRectangleImpl extends AbstractMovableImpl {

  /**
   * A constructor for a {@link Movable} rectangle with an empty set of motions, top-left corner at
   * (0, 0), black color, and height and width of 0.
   */
  public MovableRectangleImpl(String name) {
    super(name, movableType.RECTANGLE, new ArrayList<>(), 0, 0,
            new Color(0, 0, 0), 0, 0);
  }

  @Override
  protected String typeSVG() {
    return "rect";
  }

  @Override
  protected String getSizeStrSVG() {
    double leftX = this.x - (this.width / 2);
    double topY = this.y - (this.height / 2);

    return String.format("x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\"",
            leftX, topY, this.width, this.height);
  }

  @Override
  protected String getXMotionStrSVG(Motion motion, String animateFormat) {
    double fromX = motion.getX1();
    double toX = motion.getX2();
    return String.format(animateFormat, "x", fromX, toX);
  }

  @Override
  protected String getYMotionStrSVG(Motion motion, String animateFormat) {
    double fromY = motion.getY1();
    double toY = motion.getY2();
    return String.format(animateFormat, "y", fromY, toY);
  }

  @Override
  protected String getWMotionStrSVG(Motion motion, String animateFormat) {
    double fromW = motion.getW1();
    double toW = motion.getW2();
    return String.format(animateFormat, "width", fromW, toW);
  }

  @Override
  protected String getHMotionStrSVG(Motion motion, String animateFormat) {
    double fromH = motion.getH1();
    double toH = motion.getH2();
    return String.format(animateFormat, "height", fromH, toH);
  }

}
