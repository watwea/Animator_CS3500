package animator;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.view.AnimationView;

class MockView implements AnimationView {
  private StringBuilder actionsLog;
  private final int ticksPerSecond;

  /**
   * A constructor for a mock animation view.
   *
   * @param ticksPerSecond The ticks per second of the animation
   * @param width          the width of the animation
   * @param height         the height of the animation
   * @param actionsLog     the StringBuilder that will be read to check that actions are processed
   */

  MockView(int ticksPerSecond, int width, int height, StringBuilder actionsLog) {
    this.actionsLog = actionsLog;
    actionsLog.append("actionsLog set\n");
    this.ticksPerSecond = ticksPerSecond;
    actionsLog.append("ticksPerSecond set\n");
    actionsLog.append("width: ").append(width).append("\n");
    actionsLog.append("height: ").append(height).append("\n");
  }

  @Override
  public String render() {
    this.actionsLog.append("render() called\n");
    return "";
  }

  @Override
  public void output() {
    this.actionsLog.append("output() called\n");
  }

  @Override
  public int setController(AnimationController controller) {
    this.actionsLog.append("setController() called\n");
    return this.ticksPerSecond;
  }
}
