package cs3500.animator.util;

import java.util.ArrayList;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.movable.AbstractMovableImpl;
import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.keyframe.MovableKeyFrameImpl;

import static cs3500.animator.util.KeyFrameAdapterMotion.filterOverlaps;

/**
 * A class that serves to build an AnimationModel.
 */

public class AnimationModelBuilder implements AnimationBuilder<AnimationModel> {
  private int minX;
  private int minY;
  private int width;
  private int height;
  private ArrayList<Movable> movables;

  /**
   * Constructs a new AnimationModel builder class with all 0 or empty values.
   */
  public AnimationModelBuilder() {
    this(0, 0, 0, 0);
  }

  /**
   * Constructs a new AnimationModel builder class with specified values.
   *
   * @param x      the minimum X
   * @param y      the minimum Y
   * @param width  the width of the animation
   * @param height the height of the animation
   */

  private AnimationModelBuilder(int x, int y, int width, int height) {
    this.minX = x;
    this.minY = y;
    this.width = width;
    this.height = height;
    this.movables = new ArrayList<>();
  }

  @Override
  public AnimationModel build() {
    return new AnimationModelImpl(
            this.minX, this.minY, this.width, this.height, this.movables, 0);
  }

  @Override
  public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
    this.minX = x;
    this.minY = y;
    this.width = width;
    this.height = height;
    return this;
  }

  @Override
  public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
    Movable newMovable = new AbstractMovableImpl.MovableBuilder().buildMovable(name, type);
    this.movables.add(newMovable);
    return this;
  }

  @Override
  public AnimationBuilder<AnimationModel> addMotion(
          String name,
          int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
          int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    this.addKeyframe(name, t1, x1, y1, w1, h1, r1, g1, b1);
    this.addKeyframe(name, t2, x2, y2, w2, h2, r2, g2, b2);
    return this;
  }

  @Override
  public AnimationBuilder<AnimationModel> addKeyframe(
          String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    KeyFrame newKeyFrame = new MovableKeyFrameImpl(t, x, y, w, h, r, g, b);
    for (Movable movable : this.movables) {
      if (movable.getMovableName().equals(name)) {
        movable.addKeyFrames(newKeyFrame);
        movable.setKeyFrames(filterOverlaps(movable.getKeyFrames()));
      }
    }
    return this;
  }
}
