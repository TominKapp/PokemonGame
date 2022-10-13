public class Ball extends Item
{
  private double catch_rate;

  public Ball(String name, int price, String description, double catch_rate) {
    super(name, price, description);
    this.catch_rate = catch_rate;
  }

}