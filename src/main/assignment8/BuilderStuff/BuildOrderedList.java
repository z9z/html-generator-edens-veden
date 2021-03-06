/**
 *
 */
package neu.ccs.edu.cs5004.seattle.assignment8.BuilderStuff;

import java.util.LinkedList;
import java.util.ListIterator;

import neu.ccs.edu.cs5004.seattle.assignment8.contents.ListTuple;
import neu.ccs.edu.cs5004.seattle.assignment8.contents.OrderedDocuList;
import neu.ccs.edu.cs5004.seattle.assignment8.contents.OrderedListItem;
import neu.ccs.edu.cs5004.seattle.assignment8.lineAndText.Line;
import neu.ccs.edu.cs5004.seattle.assignment8.lineAndText.Marks;

/**
 * @author susannaedens
 *
 */
public class BuildOrderedList {

  private Line line;
  private ListIterator<Line> itr;

  /**
   * Given an iterator and a line, create a new BuildOrderedList. The iterator and the line are all
   * that's needed to build an OrderedList.
   *
   * @param line the line representing the first OrderedListItem in the OrderedList
   * @param itr the iterator for the list of lines representing the document
   */
  public BuildOrderedList(Line line, ListIterator<Line> itr) {
    super();
    this.line = line;
    this.itr = itr;
  }

  /**
   * Using this line and an iterator, create a new OrderedDocuList. The first tuple will capture
   * this line and it's subsequent sublist. Then, using the iterator, additional listTuples will be
   * added to the OrderedDocuList as needed.
   *
   * @return an OrderedDocuList capturing this line and it's fellow ordered list items
   */
  public OrderedDocuList build() {
    // create a new tupleList and a listItem (using this line) for the next tuple
    LinkedList<ListTuple> tupleList = new LinkedList<ListTuple>();
    OrderedListItem olitem = new OrderedListItem(this.line);
    // get the level of nesting of this line
    Integer level = Marks.getOrderedListLevel(this.line.getMark());
    BuildListTuple oltuple = new BuildListTuple(olitem, this.itr, level);
    // build the tuple using this listItem and use the iterator to generate it's sublist
    tupleList.add(oltuple.build());
    // while the iterator is not empty, if you encounter lines that belong in this list (same mark),
    // generate a tuple using the line and add it to the list.
    while (this.itr.hasNext()) {
      Line nextLine = this.itr.next();
      if (this.line.getMark().equals(nextLine.getMark())) {
        OrderedListItem olitem2 = new OrderedListItem(nextLine);
        BuildListTuple oltuple2 = new BuildListTuple(olitem2, this.itr, level);
        tupleList.add(oltuple2.build());
      } else {
        // if you didn't find what you were looking for you better set the iterator back and break
        // out of this loop
        this.itr.previous();
        break;
      }
    }
    return new OrderedDocuList(tupleList);
  }

}
