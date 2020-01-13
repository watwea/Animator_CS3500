package animator;

import cs3500.animator.view.visual.DrawingPanel;
import org.junit.Test;

/**
 * A class for testing DrawingPanels.
 */

public class DrawingPanelTest {

  @Test (expected = IllegalArgumentException.class)
  public void testNull() {
    DrawingPanel panel = new DrawingPanel();
    panel.paintComponent(null);

  }

}
