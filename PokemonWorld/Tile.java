import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Tile
{
  private static final int WIDTH = 16, HEIGHT = 16;
  private Rectangle hitbox;
  private int x, y;
  private boolean waterTile, wallTile, grassTile, teleportTile, interactTile;
  private String info, teleportLocation;

  public Tile(int x, int y) {
    hitbox = new Rectangle(x,y,WIDTH,HEIGHT);
    this.x = x;
    this.y = y;
    wallTile = false;
    waterTile = false;
  }

  public void setWaterTile() {
    waterTile = true;
  }
  public void setWallTile() {
    wallTile = true;
  }
  public void setGrassTile() {
    grassTile = true;
  }
  public void setTeleportTile() {
    teleportTile = true;
  }
  public void setTeleportLocation(String teleportLocation) {
    this.teleportLocation = teleportLocation;
  }
  public void setInteractTile() {
    interactTile = true;
  }
  public void setInfo(String info) {
    this.info = info;
  }
  public boolean isWaterTile() { return waterTile;  }
  public boolean isWallTile() { return wallTile; }
  public boolean isGrassTile() { return grassTile; }
  public boolean isTeleportTile() { return teleportTile; }
  public boolean isInteractable() { return interactTile; }
  public String getInfo() { return info; }
  
  public Rectangle hitbox() {
    return hitbox;
  }
  public void draw(Graphics g) {
    /*
    if (interactTile) {
      g.setColor(Color.RED);
      g.fillRect(x,y,16,16);
    }
    else if (wallTile) {
      g.setColor(Color.BLACK);
      g.fillRect(x,y,16,16);
    }
    else if (waterTile) {
      g.setColor(Color.BLUE);
      g.fillRect(x,y,16,16);
    }
    else if (grassTile) {
      g.setColor(Color.GREEN);
      g.fillRect(x,y,16,16);
    }
    else if (teleportTile) {
      g.setColor(Color.ORANGE);
      g.fillRect(x,y,16,16);
    }
    */
  }

  public int getX() {return x;}
  public int getY() {return y;}
}