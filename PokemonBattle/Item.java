public class Item
{
  protected String name;
  protected int price;
  protected String description;
  protected int current_quantity;
  protected final int max_quantity;

  public Item(String name, int price, String description) {
    this.name = name;
    this.price = price;
    this.description = description;
    this.current_quantity = 0;
    this.max_quantity = 99;
  }
  
  public String getName() {return name;}
  public int getPrice() {return price;}
  public String getDescription() {return description;}
  public int getQuantity() {return current_quantity;}
  public int getMaxQuantity() {return max_quantity;}
  public void setName(String name) {this.name = name;}
  public void setPrice(int price) {this.price = price;}
  public void setDescription(String description) {this.description = description;}
  public void setQuantity(int quantity) {this.current_quantity = quantity;}

  public String toString() {
    return(this.current_quantity + "\t" + this.name + "\t" + this.description);
  }
}