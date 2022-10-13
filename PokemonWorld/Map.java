import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;


public class Map
{
  private BufferedImage visualMap;
  private Tile[][] dataMap;
  private final String MAP_ROOT = "./world/";
  public Map() {
    openNewMap("PalletTown");
  }

  public void openNewMap(String mapName) {
    try {
	  mapName = MAP_ROOT + mapName;
      visualMap = ImageIO.read(new File((mapName + "_v.png")));
      BufferedImage tiles = ImageIO.read(new File((mapName + "_c.png")));
      BufferedReader textReader = new BufferedReader(new FileReader(new File((mapName + "_d.txt"))));

      dataMap = new Tile[tiles.getHeight()][tiles.getWidth()];
      for(int r = 0; r < dataMap.length; r++) {
        for(int c = 0; c < dataMap[0].length; c++) {
          dataMap[r][c] = new Tile(c*16, r*16);
          int color = tiles.getRGB(c,r);
          int red = (color & 0x00ff0000) >> 16;
          int green = (color & 0x0000ff00) >> 8;
          int blue = (color & 0x000000ff);
          if (red == Color.BLUE.getRed() && green == Color.BLUE.getGreen() && blue == Color.BLUE.getBlue()) {
            dataMap[r][c].setWaterTile();
          }
          else if (red == Color.BLACK.getRed() && green == Color.BLACK.getGreen() && blue == Color.BLACK.getBlue()) {
            dataMap[r][c].setWallTile();
          }
          else if (red == Color.GREEN.getRed() && green == Color.GREEN.getGreen() && blue == Color.GREEN.getBlue()) {
            dataMap[r][c].setGrassTile();
          }
          else if (red == Color.RED.getRed() && green == Color.RED.getGreen() && blue == Color.RED.getBlue()) {
            dataMap[r][c].setInteractTile();
            dataMap[r][c].setWallTile();
            dataMap[r][c].setInfo(textReader.readLine());
          }
          else if (red == Color.ORANGE.getRed() && green == Color.ORANGE.getGreen() && blue == Color.ORANGE.getBlue()) {
            dataMap[r][c].setTeleportTile();
            dataMap[r][c].setTeleportLocation(textReader.readLine());
          }
        }
      }
    }
    catch (Exception e) {
      System.out.println("Couldn't load the Map");
    }
  }

  public Tile getTile(int x, int y) {
    x = x / 16;
    y = y / 16;
    return dataMap[y][x];
  }

  public void draw(Graphics g) {
    g.drawImage(visualMap, 0, 0, null);
    for(int r = 0; r < dataMap.length; r++) {
      for(int c = 0; c < dataMap[0].length; c++) {
        dataMap[r][c].draw(g);
      }
    }
  }
}