import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Write a description of class PokemonPlate here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NamePanel extends JPanel
{
    private int width, height;
    private Pokemon poke;
    private JLabel pokeName, pokeLevel, pokeHealth;
    private JLabel hpTag;
    private javax.swing.Timer timer;
    private int damageTaken = 0;
    
    public NamePanel(int x, int y, int width, int height, Pokemon poke, boolean enemy) {
        this.width = width;
        this.height = height;
        this.poke = poke;
        this.setBounds(x,y,width,height);
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        timer = new javax.swing.Timer(10, new TimerListener());
        
        pokeName = new JLabel(poke.getName().toUpperCase());
        pokeName.setBounds(0,0,width, height/4);
        pokeName.setFont(new Font(Font.MONOSPACED, Font.TRUETYPE_FONT, height/4 - 2));
        this.add(pokeName);
        
        pokeLevel = new JLabel(":L " + poke.getLevel());
        pokeLevel.setBounds(width*2/5, height/4, width*4/5, height/4);
        pokeLevel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, height/5 - 2));
        this.add(pokeLevel);
        
        hpTag = new JLabel("HP: ");
        hpTag.setBounds(width/11, 48*height/100, width/5, height/14);
        hpTag.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, height/10 - 2));
        this.add(hpTag);
       
        pokeHealth = new JLabel("" + poke.getCurrentHP() + "/ " + poke.getMaxHP());
        pokeHealth.setBounds(23*width/100, 56*height/100, 4*width/5, 1*height/5);
        pokeHealth.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, height/5 - 2));
        this.add(pokeHealth);
        if (enemy) pokeHealth.setVisible(false);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        double health_ratio = poke.getCurrentHP()*1.0 / poke.getMaxHP();
        g.fillRoundRect(width*1/5, height/2, (int)((health_ratio)*(width*4/5-5)), height/23, 20, 5);
        g.setColor(Color.BLACK);
        g.drawRoundRect(width*1/5, height/2, width*4/5-5, height/23, 20, 5);
    }
    
    public void update(int x) {
        if (!timer.isRunning()) {
            damageTaken = x;
            timer.start();
        }
        
    }
    
    private class TimerListener implements ActionListener {
        int totalDmg;
        public void actionPerformed(ActionEvent e) {
            if (totalDmg < damageTaken) {
                totalDmg++;
                
                poke.setCurrentHP(poke.getCurrentHP()-1);
                pokeHealth.setText("" + poke.getCurrentHP() + "/ " + poke.getMaxHP());
                repaint();
            }
            else {
                totalDmg = 0;
                timer.stop();
            }
        }
    }
}
