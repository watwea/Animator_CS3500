package cs3500.animator.view.visual;

import cs3500.animator.model.movable.Movable;

import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * The Panel that will show all of the currently rendered movables in the animation.
 */

public class DrawingPanel extends JPanel {
  private ArrayList<Movable> shapes = null;

  public DrawingPanel() {
    super();
  }

  @Override
  public void paintComponent(Graphics g) throws IllegalArgumentException {
    if (g == null) {
      throw new IllegalArgumentException("g can't b null dawg");
    }
    Graphics2D g2 = (Graphics2D) g;
    RenderingHints rh1 = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHints(rh1);
    RenderingHints rh2 = new RenderingHints(
            RenderingHints.KEY_FRACTIONALMETRICS,
            RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    g2.addRenderingHints(rh2);
    super.paintComponent(g2);
    if (shapes != null) {
      for (Movable shape : shapes) {
        g2.setColor(shape.getColor());
        switch (shape.getType()) {
          case RECTANGLE:
            g2.fill(new Rectangle2D.Double(
                    shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight()));
            continue;
          case ELLIPSE:
            g2.fill(new Ellipse2D.Double(
                    shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight()));
            continue;
          default:
            throw new IllegalArgumentException("the given shape type is unsupported");
        }
      }
    }
  }

  void draw(Iterable<Movable> movable) {
    this.shapes = (ArrayList<Movable>) movable;
    repaint();
  }
}
