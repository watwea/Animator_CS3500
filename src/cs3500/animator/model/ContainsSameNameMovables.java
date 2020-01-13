package cs3500.animator.model;

import cs3500.animator.model.movable.Movable;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Represents a Predicate class that tests whether any {@link Movable}s in an iterable list of
 * {@link Movable}s have the same name.
 */
public class ContainsSameNameMovables implements Predicate<Iterable<Movable>> {

  @Override
  public boolean test(Iterable<Movable> movables) {
    boolean hasSameName = false;

    ArrayList<String> names = new ArrayList<>();
    for (Movable m : movables) {
      names.add(m.getMovableName());
    }

    for (Movable m : movables) {
      String mName = m.getMovableName();
      names.remove(mName);
      hasSameName = names.contains(mName) || hasSameName;
    }

    return hasSameName;
  }
}
