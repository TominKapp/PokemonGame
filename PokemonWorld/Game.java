import java.awt.Graphics;
import java.awt.Color;

public class Game
{
  private Player p;
  private int height, width;
  private Map m;

  public Game(int width, int height) {
    p = new Player(16*8, 16*8, width, height);
    this.height = height;
    this.width = width;
    m = new Map();
  }

  public void draw(Graphics g) {
    m.draw(g);
    p.move();
    p.draw(g);
  }

  public void setPlayerSpeed(int dx, int dy) {
    p.setSpeed(dx, dy);
  }

  public void setPlayerDirection(int d) {
    p.setDirection(d);
  }
  public int getPlayerDirection() {
    return p.getDirection();
  }

  public void setPlayerMoving(boolean m) {
    p.setMoving(m);
  }
  public boolean getPlayerMoving() {
    return p.getMoving();
  }

  public Tile getTile(int x, int y) {
    return m.getTile(p.getX() + x, p.getY() + y);
  }
  public boolean checkCollision(int dx, int dy) {
    if (p.getX() + 2*dx >= width || p.getY() + 2*dy >= height || p.getX() + 2*dx < 0 || p.getY() + 2*dy < 0) return true;
    Tile t = getTile(2*dx, 2*dy);
    if (t.isWallTile()) return true;
    if (t.isWaterTile() && !p.getSwimming()) return true;
    return false;
  }
}