package java_snake_multi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * @author mahdal
 */
public class MenuHra extends JMenu {

    Snake hadovec;

    MenuHra(Snake hadous) {
        super("Hra");
        hadovec = hadous;
        JMenuItem newgame = new JMenuItem("Nová hra");
        newgame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hadovec.reset();
            }
        });

        JMenuItem hostServer = new JMenuItem("Vytvorit sitovou hru");
        hostServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hadovec.turnIntoServer()) {
                    JOptionPane.showMessageDialog(null, "Pro sitovou hru musi byt zapnuta hra pro dva hrace");
                }
            }
        });

        JMenuItem connectToServer = new JMenuItem("Pripojit se ke hre");
        connectToServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    hadovec.connectToServer();
                } catch (UnknownHostException ex) {
                    JOptionPane.showMessageDialog(null, "Spatne jmeno serveru");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Nepodarilo se pripojit k serveru");
                }
            }
        });

        newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0));
        final JMenuItem pause = new JMenuItem("Pauza");
        final JMenuItem unpause = new JMenuItem("Odpauzuj");
        unpause.setVisible(false);
        unpause.setEnabled(false);
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setVisible(false);
                pause.setEnabled(false);
                unpause.setVisible(true);
                unpause.setEnabled(true);
                hadovec.pause();
            }
        });
        pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0));
        unpause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setVisible(true);
                pause.setEnabled(true);
                unpause.setVisible(false);
                unpause.setEnabled(false);
                hadovec.unPause();
            }
        });
        unpause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0));
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        add(newgame);
        addSeparator();
        add(hostServer);
        addSeparator();
        add(connectToServer);
        addSeparator();
        add(pause);
        add(unpause);
        addSeparator();
        add(exit);

    }
}