/*
  Reads from the CSV files and creates stuffz (Pokemon & Moves)
*/
import net.coobird.thumbnailator.Thumbnails;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;



public class BackendReader
{
  private static String CSV_ROOT = "./csv/";
  private static String IMG_ROOT = "./img/";
  public static Pokemon[] createPokedex() {
    Pokemon[] pokedex = new Pokemon[151];
    try {
      String file_1 = CSV_ROOT + "pokemon.csv";
      String file_2 = CSV_ROOT + "pokemon_types.csv";
      String file_3 = CSV_ROOT + "pokemon_stats.csv";
      String file_4 = CSV_ROOT + "pokemon_moves.csv";
      String file_5 = IMG_ROOT + "back_sprites.png";
      String file_6 = IMG_ROOT + "front_sprites.png";

      BufferedReader file_reader = new BufferedReader(new FileReader(new File(file_1)));
      String[] currentLine = file_reader.readLine().split(",");
      for (int i = 0; i < pokedex.length; i++) { 
        currentLine = file_reader.readLine().split(",");
        String currPoke = currentLine[2];
        int height = Integer.parseInt(currentLine[3]);
        int weight = Integer.parseInt(currentLine[4]);
        int base_xp = Integer.parseInt(currentLine[5]);
        pokedex[Integer.parseInt(currPoke) - 1] = new Pokemon(currentLine[1], height, weight, base_xp);
      }
      file_reader.close();

      //Type
      file_reader = new BufferedReader(new FileReader(new File(file_2)));
      String str = file_reader.readLine();
      currentLine = str.split(",");
      while ((str = file_reader.readLine()) != null) { 
        currentLine = str.split(",");
        pokedex[Integer.parseInt(currentLine[0]) - 1].addType(getType(currentLine[1]));
      }
      file_reader.close();

      //Base Stats + EVs gained from killing
      file_reader = new BufferedReader(new FileReader(new File(file_3)));
      currentLine = file_reader.readLine().split(",");
      for (int i = 0; i < 151; i++) {
        int[] stats = new int[6];
        int[] death_EVs = new int[6];
        for (int j = 0; j < 6; j++) {
          currentLine = file_reader.readLine().split(",");
          stats[j] = Integer.parseInt(currentLine[2]);
          death_EVs[j] = Integer.parseInt(currentLine[3]);
        }
        pokedex[i].setBaseStats(stats);
        pokedex[i].setDeathEvs(death_EVs);
      }
      file_reader.close();

      //Pokemon Moves
      file_reader = new BufferedReader(new FileReader(new File(file_4)));
      currentLine = file_reader.readLine().split(",");
      while ((str = file_reader.readLine()) != null) {
        currentLine = str.split(",");
        if (currentLine[1].equals("1")) {
          Integer move = Integer.valueOf(currentLine[2]);
          if (currentLine[3].equals("1")) {
            pokedex[Integer.parseInt(currentLine[0]) - 1].addLevelupMove(Integer.valueOf(currentLine[4]), move);
          }
          else {
            pokedex[Integer.parseInt(currentLine[0]) - 1].addTeachableMove(move);
          }
        }
      }
      file_reader.close();
      
      //Pokemon Sprites
      BufferedImage back_sprites = ImageIO.read(new File((file_5)));
      int y = -1;
      for (int i = 0; i < 151; i++) {
        if (i%11 == 0) y++;
        int height = 29, width = 30;
        int sheetX = (i%11)*width;
        int sheetY = y*height;
        pokedex[i].setBackSprite(back_sprites.getSubimage(sheetX, sheetY, width, height));
      }
      BufferedImage front_sprites = ImageIO.read(new File((file_6)));
      y = 0;
      for (int i = 0; i < 151; i++) {
        if (i%15 == 0) y++;
        int height = 56, width = 56;
        int sheetX = 58*(i%15 + 1) + width*((i%15));
        int sheetY = 22*y + height*(y-1);
        pokedex[i].setFrontSprite(front_sprites.getSubimage(sheetX, sheetY, width, height));
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    finally {
      return pokedex;
    }
  }
  public static Move[] createMovePool() {
    Move[] movepool = new Move[165];

    try {
      String file_name = CSV_ROOT + "moves.csv";
      BufferedReader file_reader = new BufferedReader(new FileReader(new File(file_name)));

      String[] currentLine = file_reader.readLine().split(",");

      for (int i = 0; i < movepool.length; i++) {
        currentLine = file_reader.readLine().split(",");
        Type moveType = getType(currentLine[3]);
        int power = Integer.parseInt(currentLine[4]);
        int max_pp = Integer.parseInt(currentLine[5]);
        int accuracy = Integer.parseInt(currentLine[6]);
        int priority = Integer.parseInt(currentLine[7]);
        int target_id = Integer.parseInt(currentLine[8]);
        int damage_class_id = Integer.parseInt(currentLine[9]);
        int effect_id = Integer.parseInt(currentLine[10]);
        int effect_chance = Integer.parseInt(currentLine[11]);

        movepool[Integer.parseInt(currentLine[0]) - 1] = new Move(currentLine[1], moveType, power, max_pp, accuracy, priority, damage_class_id, effect_id, effect_chance);
      }
      file_reader.close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    finally {
      return movepool;
    }
  }
  public static Map<String, Item> createItemList() {
    Map<String, Item> items = new HashMap<>();
    try {
      String file_name = CSV_ROOT + "allTheItems.csv";
      BufferedReader file_reader = new BufferedReader(new FileReader(new File(file_name)));

      String currentLine = "";
      String item_type = "Main Item";
      while ((currentLine = file_reader.readLine()) != null) { 
        if (currentLine.equals("/")) {
          item_type = "Ball";
        }
        else if (currentLine.equals("//")) {
          item_type = "Key Item";
        }
        else {
          String[] parts = currentLine.split(":");
          if (item_type.equals("Main Item")) {
            items.put(parts[0], new MainItem(parts[0], Integer.parseInt(parts[1]), parts[2]));
          }
          else if (item_type.equals("Ball")) {
            items.put(parts[0], new Ball(parts[0], Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3])));
          }
          else if (item_type.equals("Key Item")) {
            items.put(parts[0], new KeyItem(parts[0], parts[1]));
          }
        }
      }
      file_reader.close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    finally {
      return items;
    }
  }
  
  
  public static BufferedImage resize(BufferedImage img, int newW, int newH) {
    BufferedImage newImg = null;
    try {
      newImg = Thumbnails.of(img).size(newW, newH).asBufferedImage();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    return newImg;
  }
  
  
  private static Type getType(String s) {
    Type moveType;
    switch(Integer.parseInt(s)) {
      case(1) : moveType = Type.Normal;
      case(2) : moveType = Type.Fighting;
      case(3) : moveType = Type.Flying;
      case(4) : moveType = Type.Poison;
      case(5) : moveType = Type.Ground;
      case(6) : moveType = Type.Rock;
      case(7) : moveType = Type.Bug;
      case(8) : moveType = Type.Ghost;
      case(9) : moveType = Type.Steel;
      case(10) : moveType = Type.Fire;
      case(11) : moveType = Type.Water;
      case(12) : moveType = Type.Grass;
      case(13) : moveType = Type.Electric;
      case(14) : moveType = Type.Psychic;
      case(15) : moveType = Type.Ice;
      case(16) : moveType = Type.Dragon;
      case(17) : moveType = Type.Dark;
      case(18) : moveType = Type.Fairy;
      default : moveType = Type.Normal;
    }
    return moveType;
  }
}