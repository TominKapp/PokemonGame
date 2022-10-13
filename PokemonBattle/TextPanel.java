import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;

public class TextPanel extends JPanel
{
  private JTextField text;
  public TextPanel(int x, int y, int width, int height, String str) {
    this.setBounds(x, y, width, height);
    this.setLayout(null);
    text = new JTextField(str);
    text.setBounds(0, 0, width, height);
  }

  public void changeText(String str) {
    text.setText(str);
  }
}