import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Write a description of class MyFrame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MyFrame extends JFrame
{
    private Pokemon mine, opp;
    
    private JButton[] moves;
    private NamePanel enemy, ally;
    private ImagePanel enemy_sprite, ally_sprite;
    
    public MyFrame(Pokemon mine, Pokemon opp) {
        this.mine = mine;
        this.opp = opp;
        setTitle("Pokemon Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        JPanel jp = new JPanel();
        
        jp.setPreferredSize(new Dimension(1000, 1000));
        jp.setBackground(Color.WHITE);
        jp.setLayout(null);
        
        enemy = new NamePanel(50, 0, 450, 250, opp, true);
        enemy_sprite = new ImagePanel(500, 0, 350, 350, opp);
        enemy_sprite.useFrontSprite();
        ally = new NamePanel(500, 400, 450, 250, mine, false);
        ally_sprite = new ImagePanel(50, 330, 350, 350, mine);
        ally_sprite.useBackSprite();
        int width = 1000, height = 333;
        
        
        moves = new JButton[4];
        for (int i =0; i < moves.length; i++) {
            if (mine.getMoves()[i] != null) moves[i] = new JButton(mine.getMoves()[i].getName());
            else moves[i] = new JButton("N/A");
            if (i < 2) moves[i].setBounds((i%2)*width/2, 666+10, width/2, height/2);
            else moves[i].setBounds((i%2)*width/2, height/2 + 10+666, width/2, height/2);
            moves[i].setFont(new Font(Font.MONOSPACED, Font.TRUETYPE_FONT, height/8 - 2));
            moves[i].addActionListener(new ButtonListener());
            jp.add(moves[i]);
        }
        
        jp.add(enemy);
        jp.add(enemy_sprite);
        jp.add(ally);
        jp.add(ally_sprite);
        getContentPane().add(jp);
        pack();
        
    }
    
    public void display() {
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                setVisible(true);
            }
        });
    }
    
    
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int moveIndex = 0;
            for (int i = 0; i < moves.length; i++) {
                if (e.getSource() == moves[i] && !moves[i].getText().equals("N/A")) {
                    moveIndex = i;
                }
            }
            if (moveIndex != -1 && Battle.canContinue(mine) && Battle.canContinue(opp))
            {
                Move m = mine.getMoves()[moveIndex];
                Move oppMove = Battle.useRandomMove(opp);
                int x = Battle.goesFirst(mine, opp, m, oppMove);
                int dmg = 0;
                if (x == 2) {
                    dmg = Battle.attack(opp, mine, oppMove);
                    System.out.println("Enemy did " + dmg + " dmg using " + oppMove.getName());
                    ally.update(dmg);
                    if (Battle.canContinue(mine)) {
                        dmg = Battle.attack(mine, opp, m);
                        System.out.println("You did " + dmg + " dmg using " + m.getName());
                        enemy.update(dmg);
                    }
                    
                }
                else if (x == 1) {
                    dmg = Battle.attack(mine, opp, m);
                    System.out.println("You did " + dmg + " dmg using " + m.getName());
                    enemy.update(dmg);
                    if (Battle.canContinue(opp)) {
                        dmg = Battle.attack(opp, mine, oppMove);
                        System.out.println("Enemy did " + dmg + " dmg using " + oppMove.getName());
                        ally.update(dmg);
                    }
                }
            }
        }
    }
}
