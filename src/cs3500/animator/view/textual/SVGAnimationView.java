package cs3500.animator.view.textual;

import cs3500.animator.model.movable.Movable;

/**
 * The SVG style view of an animation.
 */

public class SVGAnimationView extends AbstractTextAnimationView {

  /**
   * A constructor for {@link SVGAnimationView} with a custom integer tick rate and {@link
   * Appendable} output.
   *
   * @param ticksPerSecond the custom tick rate in integer ticks per second
   * @param output         the custom output for this view
   * @throws IllegalArgumentException when the given output is null or if the tick rate is not
   *                                  greater than 0
   */
  public SVGAnimationView(int ticksPerSecond, Appendable output) throws IllegalArgumentException {
    super(ticksPerSecond, output);
  }

  @Override
  public String render() {
    double width = controller.getWidth();
    double height = controller.getHeight();
    Iterable<Movable> movables = controller.getMovables();

    StringBuilder svgText = new StringBuilder();
    String svgHeader = String.format(""
            + "<svg width=\"%s\" height=\"%s\" version=\"1.1\"\n"
            + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n", width * 2, height * 2);
    String svgFooter = "</svg>\n\n";
    svgText.append(svgHeader);

    // for each shape create the header and footer with the private method
    for (Movable movable : movables) {
      svgText.append(movable.writeSVG(ticksPerSecond));
    }

    svgText.append(svgFooter);

    return svgText.substring(0, svgText.length() - 1);
  }

}
