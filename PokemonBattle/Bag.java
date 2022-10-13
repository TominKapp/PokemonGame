import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;

public class Bag
{
  private Map<String, Item> items = new HashMap<String, Item>();
  private ArrayList<Item> displayable_items = new ArrayList<>();

  public Bag() {
    this.items = BackendReader.createItemList();
    calculateDisplayableItems();
  }
  public Item getItem(String name) {
    for (Item i : displayable_items) {
      if (i.getName().equals(name)) return i;
    }
    return null;
  }
  public String listItems() {
    calculateDisplayableItems();
    String ball_list = "Balls:\n", keyitem_list = "Key Items:\n", mainitem_list = "Main Items:\n", moveitem_list = "Move Items:\n";
    for (Item i : displayable_items) {
      if (i instanceof MainItem) {
        mainitem_list += "\t" + i.toString() + "\n";
      }
      else if (i instanceof Ball) {
        ball_list += "\t" + i.toString() + "\n";
      }
      else if (i instanceof KeyItem) {
        keyitem_list += "\t" + i.toString() + "\n";
      }
    }
    return mainitem_list + ball_list + keyitem_list;
  }

  public void changeItemQuantity(String name, int count) {
    Item i = items.get(name);
    if (i.equals(null)) return;
    int total = i.getQuantity() + count;
    if (total > i.getMaxQuantity()) i.setQuantity(i.getMaxQuantity());
    else if (total < 0) i.setQuantity(0);
    else i.setQuantity(count);
    items.replace(name, i);
    calculateDisplayableItems();
  }
  private void calculateDisplayableItems() {
    displayable_items.clear();
    for(Entry<String, Item> entry: items.entrySet()) {
      if (entry.getValue().getQuantity() > 0) displayable_items.add(entry.getValue());
    }
  }
}