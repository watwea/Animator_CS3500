package cs3500.animator.view.textual;

import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.view.AnimationView;

/**
 * Represents an abstract implementation of text-based views of the animator. These views share a
 * render method that outputs a String representing the animation and can write these rendered
 * strings to Appendable objects.
 */
public abstract class AbstractTextAnimationView implements AnimationView {
  protected final int ticksPerSecond;
  protected final Appendable output;
  protected AnimationController controller;

  protected AbstractTextAnimationView(int ticksPerSecond, Appendable output) {
    if (ticksPerSecond <= 0) {
      throw new IllegalArgumentException("the tick rate must be greater than 0");
    } else if (output == null) {
      throw new IllegalArgumentException("the output may not be null");
    } else {
      this.ticksPerSecond = ticksPerSecond;
      this.output = output;
    }
  }

  @Override
  public abstract String render();

  @Override
  public void output() throws IllegalStateException {
    this.writeOutput();
  }

  private void writeOutput() throws IllegalStateException {
    try {
      String strRepresentation = this.render();
      if (this.output instanceof FileWriter) {
        ((FileWriter) this.output).write(strRepresentation);
        ((FileWriter) this.output).close();
      } else {
        this.output.append(strRepresentation);
      }
    } catch (IOException e) {
      throw new IllegalStateException("failed to output the animation: " + e.getMessage());
    }
  }

  @Override
  public int setController(AnimationController controller) {
    this.controller = controller;
    return this.ticksPerSecond;
  }

}
