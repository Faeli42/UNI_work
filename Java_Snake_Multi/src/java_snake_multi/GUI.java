package java_snake_multi;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author mahdal
 */
public class GUI extends JFrame {

    JPanel panel1, panel2; // panely pro samotnou hru a pro info
    JTextArea t1, t2, t3; //textove vypisy na panelu 2
    Snake hadovec;
    MenuBAR menuuu;
    JButton bonusFood; // bonusove jidlo

    public GUI(int proPocetHadu, int setx, int sety, Snake hadous) {
        //nadpis
        super("Snake pro " + proPocetHadu);

        //provazani GUI s hrou
        hadovec = hadous;
        setSize(setx + 25, sety + 88);
        //instance menu (specialni trida)
        menuuu = new MenuBAR(hadovec);
        menuuu.setVisible(true);
        setJMenuBar(menuuu);

        //herni panel, zde jezdi hadi
        panel1 = new JPanel();
        //doplnkovy panel pro ukazatele skore
        panel2 = new JPanel();

        panel1.setLayout(null);
        panel1.setBounds(0, 0, setx, sety);
        panel1.setBackground(Color.BLUE);
        panel2.setBounds(0, sety, setx, 30);
        panel2.setBackground(Color.GREEN);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

        //vytvori arey pro ukazovani skore
        t1 = new JTextArea("Score(sipky) ==>" + hadovec.snake1.getScore());
        t1.setEnabled(false);
        t1.setBackground(Color.BLACK);
        panel2.add(t1);

        if (proPocetHadu >= 2) {
            t2 = new JTextArea("Score(wasd) ==>" + hadovec.snake2.getScore());
            t2.setEnabled(false);
            t2.setBackground(Color.BLACK);
            panel2.add(t2);
        }

        if (proPocetHadu >= 3) {
            t3 = new JTextArea("Score(ijkl) ==>" + hadovec.snake3.getScore());
            t3.setEnabled(false);
            t3.setBackground(Color.BLACK);
            panel2.add(t3);
        }

        // nove jidlo
        bonusFood = new JButton();
        bonusFood.setEnabled(false);

        setVisible(true);
        setLayout(new BorderLayout());
        getContentPane().add(panel1, BorderLayout.CENTER);
        getContentPane().add(panel2, BorderLayout.SOUTH);

        addKeyListener(hadovec);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //vytvori jednoho hada
    public void createSnake(SSS snejk, Color colour, int delka) {
        for (int i = 0; i < delka; i++) {
            snejk.had[i] = new JButton("lb" + i);
            snejk.had[i].setEnabled(false);
            panel1.add(snejk.had[i]);
            snejk.had[i].setBounds(snejk.hadX[i], snejk.hadY[i], 10, 10);
            snejk.hadX[i + 1] = snejk.hadX[i] - 10;
            snejk.hadY[i + 1] = snejk.hadY[i];
            snejk.delkaHada++;
            snejk.had[i].setBackground(colour);
        }
    }

    public void prekreslit() {
        panel1.repaint();
    }

    //kdyz had zemre, je treba odstranit jeho jidlo
    public void odstranJidloHada(SSS snejk) {
        panel1.remove(snejk.had[snejk.delkaHada - 1]);
    }

    //smaze hady a jidlo z hraci plochy
    public void vycistitHraciPlochu() {
        panel1.removeAll();
    }

    //nastavi hodnotu jedne konkretni textarey
    public void setTextForTextArea(JTextArea textArea, String text) {
        textArea.setText(text);
    }

    //zaktualizuje vsechny ukazatele skore
    public void setAllTheTextAreas() {
        setTextForTextArea(t1, "Score(sipky) ==>" + hadovec.snake1.getScore());
        if (hadovec.getPocethadu() >= 2) {
            setTextForTextArea(t2, "Score(wasd) ==>" + hadovec.snake2.getScore());
        }
        if (hadovec.getPocethadu() >= 3) {
            setTextForTextArea(t3, "Score(ijkl) ==>" + hadovec.snake3.getScore());
        }
    }

    //nastavi ukazatel skore pro konec hry
    public void gameOver() {
        setTextForTextArea(t1, "GAME OVER:  Had1(sipky) ==>" + hadovec.snake1.getScore());
        if (hadovec.getPocethadu() >= 2) {
            setTextForTextArea(t2, "     Had2(wasd) ==>" + hadovec.snake2.getScore());
        }
        if (hadovec.getPocethadu() >= 3) {
            setTextForTextArea(t3, "Had3(ijkl) ==>" + hadovec.snake3.getScore());
        }
    }

    //pripad, kde se hrac pripojil na server
    public void connectedToServer() {
        panel2.remove(t2);
        setTextForTextArea(t1, "Pomoci sipek ovladate zeleneho hada na druhe obrazovce.");
    }
}