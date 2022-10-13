import javax.swing.JFrame;
import java.awt.EventQueue;

public class MyFrame extends JFrame 
{
  private MyPanel mp;
  public MyFrame() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Movement");
    setResizable(false);

    mp = new MyPanel(16*20, 16*20);

    getContentPane().add(mp);
    pack();
  }

  public void display() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        setVisible(true);
      }
    });
  }
}