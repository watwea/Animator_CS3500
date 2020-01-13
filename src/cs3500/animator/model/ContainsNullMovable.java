package cs3500.animator.model;

import cs3500.animator.model.movable.Movable;
import java.util.function.Predicate;

/**
 * Represents a Predicate class that tests whether any Motions in an iterable list of {@link
 * Movable}s are null.
 */
public class ContainsNullMovable implements Predicate<Iterable<Movable>> {

  @Override
  public boolean test(Iterable<Movable> movables) {
    boolean hasNull = false;
    for (Movable m : movables) {
      hasNull = hasNull || (m == null);
    }
    return hasNull;
  }
}
