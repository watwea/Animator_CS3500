package cs3500.animator.view.visual;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs3500.animator.controller.AnimationController;

import cs3500.animator.view.AnimationView;



/**
 * The Visual style view of an animation.
 */

public class VisualAnimationView extends JFrame implements AnimationView {
  private final int ticksPerSecond;
  private final DrawingPanel panel;
  private AnimationController controller;

  /**
   * A constructor for a visual animation view.
   *
   * @param ticksPerSecond The ticks per second of the animation
   * @param width          the width of the animation
   * @param height         the height of the animation
   */

  public VisualAnimationView(int ticksPerSecond, int width, int height) {
    super("Visual Animation View");
    this.ticksPerSecond = ticksPerSecond;
    this.panel = new DrawingPanel();
    this.panel.setMinimumSize(new Dimension(width, height));
    this.panel.setPreferredSize(new Dimension(width * 2, height * 2));
    this.panel.setBackground(Color.WHITE);
    this.setSize(width * 2, height * 2);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(0, 0);
    this.add(new JScrollPane(this.panel));
    this.setVisible(true);
  }

  @Override
  public String render() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(
            "the visual view does not support a string representation");
  }

  @Override
  public void output() {
    controller.moveAll();
    panel.draw(controller.getMovables());
  }

  @Override
  public int setController(AnimationController controller) {
    this.controller = controller;
    return this.ticksPerSecond;
  }
}
