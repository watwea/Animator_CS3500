package cs3500.animator.model.movable;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.model.movable.motion.Motion;

/**
 * A concrete implementation of {@link Movable} that represents an oval shape. The x and y in an
 * ellipse represent the coordinated of the center, the height and width represent the ellipse's
 * diameters (vertical and horizontal), and the color is an rgb color.
 */
public class MovableEllipseImpl extends AbstractMovableImpl {

  /**
   * Constructs a {@link Movable} oval with an empty set of motions, its center at (0, 0), a black
   * color, and no size.
   */
  public MovableEllipseImpl(String name) {
    super(name, AbstractMovableImpl.movableType.ELLIPSE, new ArrayList<>(), 0, 0,
            new Color(0, 0, 0), 0, 0);
  }

  @Override
  protected String typeSVG() {
    return "ellipse";
  }

  @Override
  protected String getSizeStrSVG() {
    double radX = this.width / 2;
    double radY = this.height / 2;

    System.out.println(this.x + " ... " + this.y + " ... " + radX + " ... " + radY);
    return String.format("cx=\"%s\" cy=\"%s\" rx=\"%s\" ry=\"%s\"",
            this.x, this.y, radX, radY);
  }

  @Override
  protected String getXMotionStrSVG(Motion motion, String animateFormat) {
    double fromCX = motion.getX1();
    double toCX = motion.getX2();
    return String.format(animateFormat, "cx", fromCX, toCX);
  }

  @Override
  protected String getYMotionStrSVG(Motion motion, String animateFormat) {
    double fromCY = motion.getY1();
    double toCY = motion.getY2();
    return String.format(animateFormat, "cy", fromCY, toCY);
  }

  @Override
  protected String getWMotionStrSVG(Motion motion, String animateFormat) {
    double fromRX = motion.getW1() / 2;
    double toRX = motion.getW2() / 2;
    return String.format(animateFormat, "rx", fromRX, toRX);
  }

  @Override
  protected String getHMotionStrSVG(Motion motion, String animateFormat) {
    double fromRY = motion.getH1() / 2;
    double toRY = motion.getH2() / 2;
    return String.format(animateFormat, "ry", fromRY, toRY);
  }

}
