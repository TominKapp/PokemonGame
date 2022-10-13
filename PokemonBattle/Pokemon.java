import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pokemon
{
  private String name, nickname;
  private int height, width;
  private String gender;
  private Move[] current_moves = new Move[4];
  private ArrayList<Integer> teachable_moves;
  private ArrayList<Integer>[] levelup_moves; 
  //private int catch_rate;
  private int[] base_stats, IV_stats, EV_stats, total_stats, death_EVs;
  private int current_health;
  private Nature nature;
  private double[] nature_boost;
  private BufferedImage back_sprite, front_sprite;
  private int level, exp;
  private int base_xp;
  private String evolves_to;
  private int evo_level;
  private String evo_item;
  private Status status;
  private ArrayList<Type> types;
  private String held_item;

  //Constructor when first creating Pokemon
  //
  public Pokemon(String name, int height, int weight, int base_xp) {
    this.name = name;
    this.height = height;
    this.width = width;
    this.base_xp = base_xp;
    this.base_stats = new int[6];
    this.EV_stats = new int[]{0, 0, 0, 0, 0, 0};
    this.IV_stats = new int[6];
    this.total_stats = new int[6];
    this.types = new ArrayList<>();
    this.teachable_moves = new ArrayList<>();
    this.levelup_moves = new ArrayList[101];
  }

  //Getters and Setters (Used in evolve so far)
  public String getName() {
    return name;
  }
  public Move[] getMoves() {
    return current_moves;
  }
  public void setMoves(Move[] new_moves) {
    this.current_moves = new_moves;
  }
  public ArrayList<Integer>[] getLevelupMoves() {
    return levelup_moves;
  }
  public int getMaxHP() {
    return total_stats[0];
  }
  public int getCurrentHP() {
    return current_health;
  }
  public void setCurrentHP(int current_HP) {
    this.current_health = current_HP;
    if (this.current_health < 0) this.current_health = 0;
  }
  public int[] getStats() {
    return base_stats;
  }
  public int[] getTotalStats() {
    return total_stats;
  }
  public int getLevel() {
    return level;
  }
  public ArrayList<Type> getTypes() {
    return types;
  }
  public BufferedImage getBackSprite() {
    return back_sprite;
  }
  public void setBackSprite(BufferedImage img) {
    back_sprite = img;
  }
  public BufferedImage getFrontSprite() {
    return front_sprite;
  }
  public void setFrontSprite(BufferedImage img) {
    front_sprite = img;
  }

  public void levelUp() {
    level++;
    calcTotalStats();
  }
  public void evolve(Pokemon evolution) {
    /*
    this.name = evolution,getName();
    this.type1 = evolution.getType1();
    this.type2 = evolution,getType2();
    this.current_moves = evolution.getCurentMoves();
    this.battle_pic = evolution.getBattlePic();
    this.bag_pic = evolution.getBagPic();
    this.dex_pic = evolution.getDexPic();
    this.evolves_to = evolution.getEvolvesTo();
    this.evo_item = evolution.getEvoItem();
    this.evo_level = evolution.getEvoLevel();
    this.base_stats = evolution.getBaseStats();
    */
  }
  //Only done at beginning and at levelUps (Equations are from online)
  private void calcTotalStats() {
    
    total_stats[0] = (int)Math.floor((2.0 * base_stats[0] + IV_stats[0] + EV_stats[0]) * level / 100.0 + level + 10.0);
    for (int i = 1; i < total_stats.length; i++) {
      double statTrack = ((2.0 * base_stats[i] + IV_stats[i] + EV_stats[i]) * level / 100.0) + 5.0;
      statTrack = Math.floor(statTrack) * nature_boost[i];
      total_stats[i] = (int)Math.floor(statTrack);
      
    }
  }

  //Setup Methods(only use once)
  public void newPokeSetup(int level) {
    if (level < 1) this.level = 1;
    else if (level > 100) this.level = 100;
    else this.level = level;
    if (Math.random()*2 > 1) this.gender = "Male";
    else this.gender = "Female";
    setupIVs();
    setupNature();
    calcTotalStats();
    current_health = total_stats[0];
  }
  public void addLevelupMove(int level, Integer levelup_move) {
    if (this.levelup_moves[level-1] == null) this.levelup_moves[level-1] = new ArrayList<>();
    this.levelup_moves[level-1].add(levelup_move);
  }
  public void addTeachableMove(Integer teachable_move) {
    this.teachable_moves.add(teachable_move);
  }
  public void setBaseStats(int[] base_stats) {
    this.base_stats = base_stats;
  }
  public void setDeathEvs(int[] death_EVs) {
    this.death_EVs = death_EVs;
  }
  public void addType(Type t) {
    types.add(t);
  }
  private void setupIVs() {
    for (int i = 0; i < IV_stats.length; i++) {
      IV_stats[i] = (int)( Math.random()*31 ) + 1;
    }
  }
  private void setupNature() {
    nature_boost = new double[]{1,1,1,1,1,1}; 
    switch((int)Math.random()*25) {
      case(0): 
        this.nature = Nature.Hardy;
        break;
      case(1):
        this.nature = Nature.Lonely;
        nature_boost[1] = 1.1;
        nature_boost[2] = 0.9;
        break;
      case(2):
        this.nature = Nature.Brave;
        nature_boost[1] = 1.1;
        nature_boost[5] = 0.9;
        break;
      case(3):
        this.nature = Nature.Adament;
        nature_boost[1] = 1.1;
        nature_boost[3] = 0.9;
        break;
      case(4):
        this.nature = Nature.Naughty;
        nature_boost[1] = 1.1;
        nature_boost[4] = 0.9;
        break;
      case(5):
        this.nature = Nature.Bold;
        nature_boost[2] = 1.1;
        nature_boost[1] = 0.9;
        break;
      case(6):
        this.nature = Nature.Docile;
        break;
      case(7):
        this.nature = Nature.Relaxed;
        nature_boost[2] = 1.1;
        nature_boost[5] = 0.9;
        break;
      case(8):
        this.nature = Nature.Impish;
        nature_boost[2] = 1.1;
        nature_boost[3] = 0.9;
        break;
      case(9):
        this.nature = Nature.Lax;
        nature_boost[2] = 1.1;
        nature_boost[4] = 0.9;
        break;
      case(10):
        this.nature = Nature.Timid;
        nature_boost[5] = 1.1;
        nature_boost[1] = 0.9;
        break;
      case(11):
        this.nature = Nature.Hasty;
        nature_boost[5] = 1.1;
        nature_boost[2] = 0.9;
        break;
      case(12):
        this.nature = Nature.Serious;
        break;
      case(13):
        this.nature = Nature.Jolly;
        nature_boost[5] = 1.1;
        nature_boost[3] = 0.9;
        break;
      case(14):
        this.nature = Nature.Naive;
        nature_boost[5] = 1.1;
        nature_boost[4] = 0.9;
        break;
      case(15):
        this.nature = Nature.Modest;
        nature_boost[3] = 1.1;
        nature_boost[1] = 0.9;
        break;
      case(16):
        this.nature = Nature.Mild;
        nature_boost[3] = 1.1;
        nature_boost[2] = 0.9;
        break;
      case(17):
        this.nature = Nature.Quiet;
        nature_boost[3] = 1.1;
        nature_boost[5] = 0.9;
        break;
      case(18):
        this.nature = Nature.Bashful;
        break;
      case(19):
        this.nature = Nature.Rash;
        nature_boost[3] = 1.1;
        nature_boost[4] = 0.9;
        break;
      case(20):
        this.nature = Nature.Calm;
        nature_boost[4] = 1.1;
        nature_boost[1] = 0.9;
        break;
      case(21):
        this.nature = Nature.Gentle;
        nature_boost[4] = 1.1;
        nature_boost[2] = 0.9;
        break;
      case(22):
        this.nature = Nature.Sassy;
        nature_boost[4] = 1.1;
        nature_boost[5] = 0.9;
        break;
      case(23):
        this.nature = Nature.Careful;
        nature_boost[4] = 1.1;
        nature_boost[3] = 0.9;
        break;
      case(24):
        this.nature = Nature.Quirky;
        break;
    }
  }
}