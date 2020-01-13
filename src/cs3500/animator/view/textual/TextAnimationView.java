package cs3500.animator.view.textual;

import static cs3500.animator.util.KeyFrameAdapterMotion.keyFramesToMotions;

import java.util.ArrayList;
import java.util.Collection;

import cs3500.animator.model.movable.Movable;
import cs3500.animator.model.movable.keyframe.KeyFrame;
import cs3500.animator.model.movable.motion.Motion;

/**
 * The Text style view of an animation.
 */

public class TextAnimationView extends AbstractTextAnimationView {

  /**
   * A constructor for {@link TextAnimationView} with a {@link Appendable} output.
   *
   * @param output the custom output for this view
   * @throws IllegalArgumentException when the given output is null
   */
  public TextAnimationView(Appendable output) throws IllegalArgumentException {
    super(1, output);
  }

  @Override
  public String render() {
    int minX = (int) this.controller.getMinX();
    int minY = (int) this.controller.getMinY();
    int width = (int) this.controller.getWidth();
    int height = (int) this.controller.getHeight();
    ArrayList<Movable> movables = new ArrayList<>(
            (Collection<? extends Movable>) this.controller.getMovables());
    StringBuilder animationText = new StringBuilder();

    StringBuilder canvasLine = new StringBuilder("canvas ");
    canvasLine.append(minX).append(" ");
    canvasLine.append(minY).append(" ");
    canvasLine.append(width).append(" ");
    canvasLine.append(height).append("\n");
    animationText.append(canvasLine);

    StringBuilder movableLines = new StringBuilder();
    StringBuilder motionLines = new StringBuilder();
    for (Movable mov : movables) {
      StringBuilder movableLine = new StringBuilder("shape ");
      movableLine.append(mov.getMovableName()).append(" ");
      movableLine.append(mov.getType().toString().toLowerCase()).append("\n");
      movableLines.append(movableLine);
      Iterable<KeyFrame> keyFrames = mov.getKeyFrames();
      Iterable<Motion> motions = keyFramesToMotions(keyFrames);
      for (Motion motion : motions) {
        StringBuilder motionLine = new StringBuilder("motion ");
        motionLine.append(mov.getMovableName()).append(" ");
        motionLine.append(motion.write()).append("\n");
        motionLines.append(motionLine);
      }
    }
    animationText.append(movableLines);
    animationText.append(motionLines);

    return animationText.substring(0, animationText.length() - 1);
  }

}
