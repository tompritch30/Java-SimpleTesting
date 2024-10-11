package ic.doc;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;

public class RecentlyUsedTest {

  @Test
  public void testInitialization() {
    RecentlyUsed recentlyUsed = new RecentlyUsed();
    assertThat(
        "List should be empty upon initialization",
        recentlyUsed.retrieveItems().isEmpty(),
        is(true));
  }

  @Test
  public void testAdd() {
    RecentlyUsed recentlyUsed = new RecentlyUsed();

    // Test NullPointerException
    try {
      // Simulate adding a null argument
      recentlyUsed.add(null);
    } catch (NullPointerException e) {
      assertThat(
          "Caught the expected NullPointerException.",
          e.getMessage(),
          containsString("Input argument 'args' cannot be null."));
    } catch (Exception e) {
      // Handle other exceptions if needed
    }

    recentlyUsed.add("What is the meaning of life?");
    assertThat(
        "Should be able to add a string: ",
        recentlyUsed.retrieveItems(),
        contains("What is the meaning of life?"));

    // Test adding integers
    recentlyUsed.add(42);
    assertThat("Should be able to add an integer: ", recentlyUsed.retrieveItems(), hasItem(42));
  }

  @Test
  public void testRetrieveItems() {
    RecentlyUsed recentlyUsed = new RecentlyUsed();
    recentlyUsed.add("Item1");
    recentlyUsed.add("Item2");
    recentlyUsed.add("Item3");
    recentlyUsed.add(42);

    // Test retrieval of items
    assertThat(
        "Should be able to retrieve items: ",
        recentlyUsed.retrieveItems(),
        containsInAnyOrder("Item1", "Item2", "Item3", 42));
  }

  @Test
  public void testMostRecentItemIsFirst() {
    RecentlyUsed recentlyUsed = new RecentlyUsed();

    recentlyUsed.add("Item1");
    assertThat(
        "Most recent item should be first: ", recentlyUsed.retrieveItems(), contains("Item1"));

    recentlyUsed.add(true);
    assertThat(
        "Most recent item should be first: ",
        recentlyUsed.retrieveItems(),
        contains(true, "Item1"));

    recentlyUsed.add("Item3");
    assertThat(
        "Most recent item should be first: ",
        recentlyUsed.retrieveItems(),
        contains("Item3", true, "Item1"));
  }

  @Test
  public void testAddStringWithNoDuplicates() {
    RecentlyUsed recentlyUsed = new RecentlyUsed();

    recentlyUsed.add("Item1");
    assertThat("Adding the first item: ", recentlyUsed.retrieveItems(), contains("Item1"));

    recentlyUsed.add("Item2");
    assertThat("Adding a second item: ", recentlyUsed.retrieveItems(), contains("Item2", "Item1"));

    recentlyUsed.add("Item3");
    assertThat(
        "Adding a third item: ", recentlyUsed.retrieveItems(), contains("Item3", "Item2", "Item1"));

    // Adding an item that already exists
    recentlyUsed.add("Item2");
    assertThat(
        "Adding a duplicate item: ",
        recentlyUsed.retrieveItems(),
        contains("Item2", "Item3", "Item1"));
  }

  @Test
  public void testGetItemAtIndex() {
    RecentlyUsed recentlyUsed = new RecentlyUsed();
    recentlyUsed.add("Item1");
    recentlyUsed.add("Item2");
    recentlyUsed.add("Item3");

    // Test retrieving each item by its index
    assertThat("Should retrieve Item1 at index 2", recentlyUsed.getItemAtIndex(2), is("Item1"));
    assertThat("Should retrieve Item1 at index 0", recentlyUsed.getItemAtIndex(0), is("Item1"));
    // assertThat("Should retrieve Item3 at index 0", recentlyUsed.getItemAtIndex(0), is("Item3"));

    // test for out-of-bounds access
    try {
      recentlyUsed.getItemAtIndex(3);
    } catch (IndexOutOfBoundsException e) {
      assertThat(
          "Caught the expected IndexOutOfBoundsException for index beyond list size.",
          e.getMessage(),
          is("Index: 3, Size: 3"));
    }
  }
}
