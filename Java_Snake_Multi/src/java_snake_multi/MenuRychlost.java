package java_snake_multi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author mahdal
 */
public class MenuRychlost extends JMenu {

    Snake hadovec;

    MenuRychlost(Snake hadous) {
        super("Obtížnost");

        hadovec = hadous;
        JMenuItem rychlost10 = new JMenuItem("Hardcore");
        rychlost10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hadovec.setSpeed(16);
            }
        });
        add(rychlost10);
        JMenuItem rychlost20 = new JMenuItem("Very Hard");
        rychlost20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hadovec.setSpeed(20);
            }
        });
        add(rychlost20);
        JMenuItem rychlost30 = new JMenuItem("Hard");
        rychlost30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hadovec.setSpeed(30);
            }
        });
        add(rychlost30);
        JMenuItem rychlost45 = new JMenuItem("Normal");
        rychlost45.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hadovec.setSpeed(45);
            }
        });
        add(rychlost45);
        JMenuItem rychlost66 = new JMenuItem("Easy");
        rychlost66.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hadovec.setSpeed(66);
            }
        });
        add(rychlost66);
        JMenuItem rychlost85 = new JMenuItem("Very easy");
        rychlost85.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hadovec.setSpeed(85);
            }
        });
        add(rychlost85);
        JMenuItem rychlost100 = new JMenuItem("Beginner");
        rychlost100.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hadovec.setSpeed(100);
            }
        });
        add(rychlost100);
    }
}