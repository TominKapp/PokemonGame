import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MyPanel extends JPanel
{
  private JTextField textLbl;
  private Game game;
  private boolean[] keys = new boolean[]{false, false, false, false};
  private javax.swing.Timer timer;

  public MyPanel(int width, int height) {
    setBackground(Color.WHITE);
    setLayout(null);
    setFocusable(true);
    setPreferredSize(new Dimension(width,height));
    addKeyListener(new MovementListener());
    textLbl = new JTextField();
    textLbl.setText("Filler Text");
    textLbl.setForeground(Color.BLUE);
    textLbl.setBounds(0, height - 32, width, 32);
    textLbl.setVisible(true);
    game = new Game(width, height - 16*2);
    timer = new javax.swing.Timer(50, new TimerListener());
    timer.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    game.draw(g);
  }

  private class TimerListener implements ActionListener {
    private boolean walking = false;
    private int dx, dy;
    //0 = Up, 1 = Down, 2 = Left, 3 = Right
    private int currDirection;

    public void actionPerformed(ActionEvent e) {

      if (walking) {
        walking = false;
      }
      else if((keys[0] || keys[1] || keys[2] || keys[3]) && !game.getPlayerMoving()) {
        if (keys[0]) {
          game.setPlayerDirection(0);
          dy = -8;
          dx = 0;
        }
        if (keys[1]) {
          game.setPlayerDirection(1);
          dy = 8;
          dx = 0;
        }
        if (keys[2]) {
          game.setPlayerDirection(2);
          dx = -8;
          dy = 0;
        }
        if (keys[3]) {
          game.setPlayerDirection(3);
          dx = 8;
          dy = 0;
        }
        //Collision
        
        if (game.checkCollision(dx, dy)) {
          dx = 0;
          dy = 0;
        }
        
        walking = true;
        game.setPlayerMoving(true);
      }
      else {
        dx = 0;
        dy = 0;
        game.setPlayerMoving(false);
      }
      game.setPlayerSpeed(dx, dy);
      repaint();
    }
  }
  private class MovementListener implements KeyListener {
    public void keyPressed(KeyEvent e) {
      //Talking
      if (e.getKeyCode() == KeyEvent.VK_Z) {
        if (textLbl.isVisible()) {
          textLbl.setVisible(false);
        }
        else {
          textLbl.setVisible(true);
          int dx = 0, dy = 0;
          switch (game.getPlayerDirection()) {
            case(0) :
              dy = -16;
              dx = 0;
              break;
            case(1) :
              dy = 16;
              dx = 0;
              break;
            case(2) :
              dx = -16;
              dy = 0;
              break;
            case(3) :
              dx = 16;
              dy = 0;
              break;
          }
          Tile t = game.getTile(dx,dy);
          if (t.isInteractable()) { textLbl.setText(t.getInfo()); System.out.println(t.getInfo());}
          else { textLbl.setVisible(false); }
          

        }
        
        
      }
      //Running
      if (e.getKeyCode() == KeyEvent.VK_X) {
        
      }
      
      //Movement
      if (e.getKeyCode() == KeyEvent.VK_UP) {
        keys[0] = true;
      }
      else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        keys[1] = true;
      }
      else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        keys[2] = true;
      }
      else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        keys[3] = true;
      }
    }
    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_UP) {
      keys[0] = false;
      }
      else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        keys[1] = false;
      }
      else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        keys[2] = false;
      }
      else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        keys[3] = false;
      }
    }
    public void keyTyped(KeyEvent e) {
    }
  }

  
}