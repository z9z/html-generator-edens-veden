/**
 *
 */
package neu.ccs.edu.cs5004.seattle.assignment8.contents;

/**
 * @author joshuaveden
 *
 */
public abstract class AbstractContent implements Content {
  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return 42;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    return true;
  }

}
