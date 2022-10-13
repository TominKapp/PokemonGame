import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

public class Player
{
  int WIDTH = 16, HEIGHT = 16;
  int panel_width, panel_height;
  int x, y;
  int dx, dy;
  BufferedImage playerSprites;
  //0 = Up, 1 = Down, 2 = Left, 3 = Right
  int direction;
  boolean isMoving, isSwimming, isBiking, isRunning;

  public Player(int x, int y, int p_w, int p_h) {
    this.x = x;
    this.y = y;
    this.panel_width = p_w;
    this.panel_height = p_h;
    try { 
      playerSprites = ImageIO.read( new File("./player/player_sprite.png"));
    } catch(Exception e) {
      System.out.println("Couldn't open image");
    }
    this.direction = 1;
    this.isMoving = false;
  }

  public void setSpeed(int dx, int dy) {
    this.dx = dx;
    this.dy = dy;
  }
  public void move() {
    x+= dx;
    y+= dy;

    if (x < 0) {
      x = 0;
    }
    if (x + WIDTH > panel_width) {
      x = panel_width - WIDTH;
    }

    if (y < 0) {
      y = 0;
    }
    if (y + HEIGHT > panel_height) {
      y = panel_height - HEIGHT;
    }
  }

  public Rectangle getRectangle() {
    return new Rectangle(x, y, WIDTH, HEIGHT);
  }
  public void draw(Graphics g) {
    g.fillRect(x, y, WIDTH, HEIGHT);
    int sheetX = 0, sheetY = 0;
    
    if (isMoving) {
      sheetX = 2;
      if (direction == 0) {
        sheetY = 0;
      }
      else if (direction == 1) {
        sheetY = 2;
      }
      else if (direction == 2) {
        sheetY = 1;
      }
      else if (direction == 3) {
        sheetY = 1;
      }
    }
    else {
      sheetX = 1;
      if (direction == 0) {
        sheetY = 0;
      }
      else if (direction == 1) {
        sheetY = 2;
      }
      else if (direction == 2) {
        sheetY = 1;
      }
      else if (direction == 3) {
        sheetY = 1;
      }
      
    }
    BufferedImage currPlayer = playerSprites.getSubimage(sheetX*16, sheetY*16, WIDTH, HEIGHT);
    if (direction == 3) {
      currPlayer = flip(currPlayer);
    }
    g.drawImage(currPlayer, x, y, null);
  }

  public void setMoving(boolean isMoving) {this.isMoving = isMoving;}
  public void setRunning(boolean isRunning) {this.isRunning = isRunning;}
  public void setBiking(boolean isBiking) {this.isBiking = isBiking;}
  public void setSwimming(boolean isSwimming) {this.isSwimming = isSwimming;}
  public void setDirection(int direction) {this.direction = direction;}

  public boolean getMoving() {return isMoving;}
  public boolean getRunning() {return isRunning;}
  public boolean getBiking() {return isBiking;}
  public boolean getSwimming() {return isSwimming;}
  public int getDirection() {return direction;}
  public int getX() {return x;}
  public int getY() {return y;}


  private static BufferedImage flip(BufferedImage sprite){
    BufferedImage img = new BufferedImage(sprite.getWidth(),sprite.getHeight(),BufferedImage.TYPE_INT_ARGB);
    for(int xx = sprite.getWidth(); xx > 0; xx--){
      for(int yy = 0; yy < sprite.getHeight(); yy++){
        img.setRGB(sprite.getWidth()-xx, yy, sprite.getRGB(xx-1, yy));
      }
    }
    return img;
  }
}