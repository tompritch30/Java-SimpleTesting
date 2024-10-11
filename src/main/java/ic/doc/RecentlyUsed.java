package ic.doc;

import java.util.LinkedList;

public class RecentlyUsed {
  private final LinkedList<Object> recentList;

  public RecentlyUsed() {
    recentList = new LinkedList<>();
  }

  public void add(Object args) {
    if (args == null) {
      throw new NullPointerException("Input argument 'args' cannot be null.");
    }

    // Will either remove argument or not do anything
    recentList.remove(args);

    // Add element to the start of the list
    this.recentList.addFirst(args);
  }

  public LinkedList<Object> retrieveItems() {
    // Return a copy of the list to prevent modification of the original list
    return new LinkedList<>(this.recentList);
  }

  public Object getItemAtIndex(int index) {
    // Check if the index is within the bounds of the list
    if (index < 0 || index >= this.recentList.size()) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.recentList.size());
    }
    // Return the item at the specified index
    Object valueAtPos = this.recentList.get(index);
    // add the item to the start of the linked list (and remove from old position)
    this.add(valueAtPos);
    // return the value that was at that position
    return valueAtPos;
  }
}
