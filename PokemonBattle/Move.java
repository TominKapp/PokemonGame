public class Move
{
  private String name;
  //private String description;
  private Type move_type;
  private int power;
  private int max_pp, current_pp;
  private int accuracy;
  private int priority;
  private int target_id;
  private int damage_class_id;
  private int effect_id;
  private int effect_chance;


  public Move(String name, Type move_type, int power, int max_pp, int accuracy, int priority, int damage_class_id, int effect_id, int effect_chance) {
    this.name = name;
    this.move_type = move_type;
    this.power = power;
    this.max_pp = max_pp;
    this.current_pp = max_pp;
    this.accuracy = accuracy;
    this.priority = priority;
    this.damage_class_id = damage_class_id;
    this.effect_id = effect_id;
    this.effect_chance = effect_chance;
  }

  //Getters and Setters
  public String getName() {
    return name;
  }
  public int getPower() {
    return power;
  }
  public int getPriority() {
    return priority;
  }
  public int getDamageClassID() {
    return damage_class_id;
  }
  public Type getType() {
    return move_type;
  }

  //Methods
  public void useMove() {
    current_pp--;
  }
  public void restorePP(int x) {
    current_pp+=x;
    if (current_pp > max_pp) current_pp = max_pp;
  }
  public void restorePP() {
    this.restorePP(max_pp);
  }
}