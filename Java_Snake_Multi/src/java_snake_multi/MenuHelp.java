package java_snake_multi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * @author mahdal
 */

public class MenuHelp extends JMenu {

    Snake hadovec;

    MenuHelp(Snake hadous) {
        super("Help");
        hadovec = hadous;

        JMenuItem autor = new JMenuItem("Autor");
        autor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Autor: Oldřich Mahdal\n"
                        + "Účel: semestrální práce\n"
                        + "Předmět: ČVUT-FEL programování 2");
            }
        });
        JMenuItem instrukce = new JMenuItem("Instrukce");
        instrukce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, 
                          "Hráč 1 : šipky\n"
                        + "Hráč 2 : w a s d\n"
                        + "Hráč 3 : i j k l\n"
                        + "Nastav si vlastní pravidla (obtížnost, zdi...)\n"
                        + "\n"
                        + "Při každých 20 bodech některého z hadů se objeví bonus");
            }
        });
        JMenuItem server = new JMenuItem("Jak hrát po síti?");
        server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, 
                          "SERVER: v menu Hra vyberte založit síťovou hru,\n"
                        + "zadejte port a počkejte na připojení klienta\n"
                        + "\n"
                        + "KLIENT: v menu Hra vyberte Připojit se ke hře\n"
                        + "zadejte parametry serveru\n"
                        + "\n"
                        + "Hra bude probíhat na obrazovce serveru");
            }
        });
        
        add(autor);
        add(instrukce);
        add(server);
    }
}