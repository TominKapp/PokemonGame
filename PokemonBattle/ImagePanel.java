import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel
{
  private Pokemon p;
  private BufferedImage img;
  private int width, height;
  
  public ImagePanel(int x, int y, int width, int height, Pokemon p) {
    this.setBounds(x, y, width, height);
    this.setBackground(Color.WHITE);
    this.setLayout(null);
    this.p = p;
    this.width = width;
    this.height = height;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 0, 0, null);
  }
  public void useBackSprite() {
    img = BackendReader.resize(p.getBackSprite(), width, height);
    repaint();
  }
  public void useFrontSprite() {
    img = BackendReader.resize(p.getFrontSprite(), width, height);
    repaint();
  }
  
}