package java_snake_multi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author mahdal
 */
public class MenuHerniMod extends JMenu {

    Snake hadovec;

    MenuHerniMod(Snake hadous) {
        super("Herní mód");
        hadovec = hadous;

        JMenuItem skrz = new JMenuItem("Okraje pruchozi");
        skrz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("zdi vypnuty");
                hadovec.zdi = false;
            }
        });
        JMenuItem naraz = new JMenuItem("Okraje = zdi");
        naraz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("zdi aktivni");
                hadovec.zdi = true;
            }
        });
        add(skrz);
        add(naraz);
    }
}