package cs3500.animator.model.movable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.motion.Motion;
import cs3500.animator.model.movable.predicates.ContainsNull;
import cs3500.animator.model.movable.predicates.ContainsOverlappingKeyFrames;

import static cs3500.animator.model.AnimationModelImpl.TICK;
import static cs3500.animator.util.KeyFrameAdapterMotion.filterOverlaps;
import static cs3500.animator.util.KeyFrameAdapterMotion.keyFramesToMotions;

/**
 * Represents a strict abstract implementation of {@link Movable} which is of a certain type, and
 * has cartesian coordinates of its center, a positive height and width, a NON-OVERLAPPING,
 * CONTINUOUS list of motions IN CHRONOLOGICAL ORDER it will go through, and a color.
 */
public abstract class AbstractMovableImpl implements Movable {

  private final String movableName;
  private final movableType type;
  private ArrayList<KeyFrame> keyFrames;
  protected double x;
  protected double y;
  protected Color color;
  protected double height;
  protected double width;

  /**
   * An enumeration of possible shapes supported by this abstract {@link Movable} implementation.
   */
  public enum movableType {
    RECTANGLE, ELLIPSE
  }

  /**
   * Constructs an abstract {@link Movable} with all of its parameters as specified, ensuring the
   * continuity and lack of overlap in its key frames.
   *
   * @param movableName the unique name of this {@link Movable}
   * @param type        the shape type of this {@link Movable}
   * @param keyFrames   the key frames this {@link Movable} should follow
   * @param x           the x-coordinate of this {@link Movable}'s center
   * @param y           the y-coordinate of this {@link Movable}'s center
   * @param color       this {@link Movable}'s color
   * @param height      this {@link Movable}'s height
   * @param width       this {@link Movable}'s width
   * @throws IllegalArgumentException when the given movableName is null, when the given list of
   *                                  motions are null, contain a null or are overlapping, when
   *                                  color is null, or when the given height or width is negative
   */
  AbstractMovableImpl(String movableName, movableType type, Iterable<KeyFrame> keyFrames,
                      double x, double y, Color color, double height, double width)
          throws IllegalArgumentException {
    this.movableName = movableNameCheck(movableName);
    this.type = typeCheck(type);
    this.keyFrames = new ArrayList<>((Collection<? extends KeyFrame>) keyFramesCheck(keyFrames));
    this.keyFrames.sort(new KeyFrameComparator());
    this.color = colorCheck(color);
    this.x = x;
    this.y = y;
    this.height = heightCheck(height);
    this.width = widthCheck(width);
  }

  /**
   * Validates the movableName param in the constructor.
   *
   * @param movableName the unique name of this {@link Movable}
   * @return the name this movable should have
   * @throws IllegalArgumentException when the name is null
   */
  private static String movableNameCheck(String movableName) throws IllegalArgumentException {
    if (movableName == null) {
      throw new IllegalArgumentException("the given name may not be null");
    }
    return movableName;
  }

  /**
   * Validates the type param in the constructor.
   *
   * @param type the shape type of this {@link Movable}
   * @return the shape type of this {@link Movable}
   * @throws IllegalArgumentException when the given type is null
   */
  private static movableType typeCheck(movableType type) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("the given type may not be null");
    }
    return type;
  }

  /**
   * Validates the keyFrames param in the constructor, and returns a validated, sorted (in
   * chronological order), ArrayList of {@link KeyFrame}s.
   *
   * @param keyFrames the key frames this {@link Movable} should follow
   * @return the key frames this {@link Movable} should follow
   * @throws IllegalArgumentException when the given keyFrames are null, contain a null, or when the
   *                                  keyFrames overlap
   */
  public static Iterable<KeyFrame> keyFramesCheck(Iterable<KeyFrame> keyFrames)
          throws IllegalArgumentException {
    if (keyFrames == null) {
      throw new IllegalArgumentException(
              "the list of key frames may not be null");
    } else if (new ContainsNull().test(keyFrames)) {
      throw new IllegalArgumentException(
              "the list of key frames may not contain a 'null' value");
    } else if (new ContainsOverlappingKeyFrames().test(keyFrames)) {
      throw new IllegalArgumentException(
              "the given list of key frames contained overlapping frames");
    } else {
      ArrayList<KeyFrame> keyFramesArray = new ArrayList<>((Collection<KeyFrame>) keyFrames);
      keyFramesArray.sort(new KeyFrameComparator());
      return keyFramesArray;
    }
  }

  /**
   * Validates the color param in the constructor.
   *
   * @param color this {@link Movable}'s color
   * @return this {@link Movable}'s color
   * @throws IllegalArgumentException when the given color is null
   */
  private static Color colorCheck(Color color) throws IllegalArgumentException {
    if (color == null) {
      throw new IllegalArgumentException("the given color may not be null");
    } else {
      return color;
    }
  }

  /**
   * Validates the height param in the constructor.
   *
   * @param height this {@link Movable}'s height
   * @return this {@link Movable}'s height
   * @throws IllegalArgumentException when the given height is negative
   */
  private static double heightCheck(double height) throws IllegalArgumentException {
    if (height < 0) {
      throw new IllegalArgumentException("the height may not be negative");
    } else {
      return height;
    }
  }

  /**
   * Validates the width param in the constructor.
   *
   * @param width this {@link Movable}'s width
   * @return this {@link Movable}'s width
   * @throws IllegalArgumentException when the given width is negative
   */
  private static double widthCheck(double width) throws IllegalArgumentException {
    if (width < 0) {
      throw new IllegalArgumentException("the width may not be negative");
    } else {
      return width;
    }
  }

  @Override
  public String write() {
    StringBuilder builder = new StringBuilder();
    builder.append("shape").append(" ");
    builder.append(this.movableName).append(" ");
    builder.append(this.type.toString().toLowerCase());
    builder.append("\n");
    return builder.toString();
  }

  /**
   * Gets all of the data of a movable in one String, for testing purposes.
   *
   * @return this {@link AbstractMovableImpl}'s data
   */

  public String getData() {
    return "" + this.getType() + " - x-cord is: " + this.getX() + " y-cord is: " + this.getY()
            + " color is: " + this.getColor() + " height is: " + this.getWidth() + " width is: "
            + this.getHeight() + " tick is: " + TICK;

  }

  @Override
  public String writeSVG(double ticksPerSec) {
    StringBuilder shapeText = new StringBuilder();

    String shapeHeader = String.format(""
                    + "<%s id=\"%s\" %s "
                    + "fill=\"rgb(%s,%s,%s)\" visibility=\"visible\" >\n",
            this.typeSVG(), this.movableName, this.getSizeStrSVG(),
            this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    shapeText.append(shapeHeader);

    StringBuilder shapeMotions = new StringBuilder();

    Iterable<KeyFrame> keyFrames = this.getKeyFrames();
    ArrayList<Motion> motions = new ArrayList<>(
            (Collection<Motion>) keyFramesToMotions(keyFrames));
    for (int i = 0; i < motions.size(); i++) {
      Motion motion = motions.get(i);
      double beginTime = motion.getT1() / ticksPerSec;
      double durationTime = (motion.getT2() - motion.getT1()) / ticksPerSec;
      String animateFormat = String.format(""
                      + "<animate attributeName=\"%s\" attributeType=\"XML\" "
                      + "from=\"%s\" to=\"%s\" fill=\"freeze\" "
                      + "begin=\"%ss\" dur=\"%ss\" />\n",
              "%s", "%s", "%s", beginTime, durationTime);

      String motionStr = this.getMotionStrSVG(motion, (i == 0), animateFormat);
      shapeMotions.append(motionStr).append("\n");
    }

    shapeText.append(shapeMotions);

    String shapeFooter = String.format("</%s>\n", this.typeSVG());
    shapeText.append(shapeFooter);

    return shapeText.toString();
  }

  /**
   * A method to retrieve the shape type of this {@link Movable} in terms of SVG shapes.
   *
   * @return a {@link String} representing this {@link Movable}'s shape
   */

  protected abstract String typeSVG();

  /**
   * A method to retrieve the position and size specifications of this {@link Movable} in terms of
   * SVG shapes.
   *
   * @return a {@link String} representing this {@link Movable}'s position and size
   */
  protected abstract String getSizeStrSVG();

  /**
   * A method to retrieve the animate commands of the given {@link Motion} if it were applied to
   * this {@link Movable}. The first motion in the list is always added fully regardless to whether
   * there is any change for each attribute from start to finish.
   *
   * @param motion        the motions for which to produce the block of <code>animate</code>
   *                      commands
   * @param isFirstMotion a boolean representing whether this motion is the first one in its {@link
   *                      Movable}s list of motions
   * @param animateFormat the string format of an animate command in an SVG document
   * @return a {@link String} representing the animate commands in SVG syntax
   */
  private String getMotionStrSVG(Motion motion, boolean isFirstMotion, String animateFormat) {
    StringBuilder motionsBuilder = new StringBuilder();

    if (motion.getX1() != motion.getX2() || isFirstMotion) {
      motionsBuilder.append(this.getXMotionStrSVG(motion, animateFormat));
    }
    if (motion.getY1() != motion.getY2() || isFirstMotion) {
      motionsBuilder.append(this.getYMotionStrSVG(motion, animateFormat));
    }
    if (motion.getW1() != motion.getW2() || isFirstMotion) {
      motionsBuilder.append(this.getWMotionStrSVG(motion, animateFormat));
    }
    if (motion.getH1() != motion.getH2() || isFirstMotion) {
      motionsBuilder.append(this.getHMotionStrSVG(motion, animateFormat));
    }
    if (motion.getR1() != motion.getR2()
            || motion.getG1() != motion.getG2()
            || motion.getB1() != motion.getB2() || isFirstMotion) {
      motionsBuilder.append(this.getColorMotionStrSVG(motion, animateFormat));
    }

    return motionsBuilder.toString();
  }

  protected abstract String getXMotionStrSVG(Motion motion, String animateFormat);

  protected abstract String getYMotionStrSVG(Motion motion, String animateFormat);

  protected abstract String getWMotionStrSVG(Motion motion, String animateFormat);

  protected abstract String getHMotionStrSVG(Motion motion, String animateFormat);

  private String getColorMotionStrSVG(Motion motion, String animateFormat) {
    String fromColor = String.format("rgb(%s,%s,%s)",
            motion.getR1(), motion.getG1(), motion.getB1());
    String toColor = String.format("rgb(%s,%s,%s)",
            motion.getR2(), motion.getG2(), motion.getB2());
    return String.format(animateFormat, "fill", fromColor, toColor);

  }

  @Override
  public String getMovableName() {
    return this.movableName;
  }

  @Override
  public movableType getType() {
    return this.type;
  }

  @Override
  public double getX() {
    return this.x;
  }

  @Override
  public double getY() {
    return this.y;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public Color getColor() {
    return new Color(color.getRed(), color.getGreen(), color.getBlue());
  }

  @Override
  public int getLastTick() {
    int lastTick = 0;
    for (KeyFrame keyFrame : this.keyFrames) {
      lastTick = Math.max(lastTick, keyFrame.getT1());
    }
    return lastTick;
  }

  @Override
  public Iterable<KeyFrame> getKeyFrames() {
    return this.keyFrames;
  }

  @Override
  public void setX(double x) {
    this.x = x;
  }

  @Override
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public void setHeight(double height) {
    this.height = AbstractMovableImpl.heightCheck(height);
  }

  @Override
  public void setWidth(double width) {
    this.width = AbstractMovableImpl.widthCheck(width);
  }

  @Override
  public void setColor(Color color) {
    this.color = AbstractMovableImpl.colorCheck(color);
  }

  @Override
  public void setKeyFrames(KeyFrame... keyFrames) {
    this.setKeyFrames(new ArrayList<>(
            (Collection<KeyFrame>) AbstractMovableImpl.keyFramesCheck(
                    Arrays.asList(keyFrames))));
    this.keyFrames.sort(new KeyFrameComparator());
  }

  @Override
  public void setKeyFrames(Iterable<KeyFrame> keyFrames) {
    this.keyFrames.clear();
    this.keyFrames.addAll((Collection<KeyFrame>) AbstractMovableImpl.keyFramesCheck(keyFrames));
    this.keyFrames.sort(new KeyFrameComparator());
  }

  @Override
  public void addKeyFrames(KeyFrame... keyFrames) throws IllegalArgumentException {
    this.keyFrames.addAll((Collection<KeyFrame>) AbstractMovableImpl.keyFramesCheck(
            Arrays.asList(keyFrames)));
    this.keyFrames = (ArrayList<KeyFrame>) keyFramesCheck(filterOverlaps((this.keyFrames)));
    this.keyFrames.sort(new KeyFrameComparator());
  }

  @Override
  public void removeKeyFrameAt(int index) throws IllegalArgumentException {
    boolean frameExists = false;
    ArrayList<KeyFrame> filteredFrames = new ArrayList<>();
    for (int i = 0; i < this.keyFrames.size(); i++) {
      if (i == index) {
        frameExists = true;
      } else {
        filteredFrames.add(this.keyFrames.get(i));
      }
    }
    if (frameExists) {
      this.setKeyFrames(filteredFrames);
    } else {
      throw new IllegalArgumentException("there is no key frame with the given tick");
    }
  }

  /**
   * A builder class for {@link Movable}.
   */
  public static class MovableBuilder {

    /**
     * A method to build a Movable shape of the given type.
     *
     * @param name the name of the shape
     * @param type the {@link movableType} of this shape
     * @return a Movable of the correct name and type with no size and the x,y positions set to 0
     */
    public Movable buildMovable(String name, String type) {
      String movableType = type.toLowerCase();
      switch (movableType) {
        case "rectangle":
          return new MovableRectangleImpl(name);
        case "ellipse":
          return new MovableEllipseImpl(name);
        default:
          throw new IllegalArgumentException("the given shape type is unsupported");
      }
    }
  }
}
