package java_snake_multi;

import javax.swing.JMenuBar;

/**
 * @author mahdal
 */
public class MenuBAR extends JMenuBar {

    Snake hadovec;
    
    //konstruktor pro menubar obsahuje dilci menu ...
    public MenuBAR(Snake hadous) {
        hadovec = hadous;
        MenuHra menuHra = new MenuHra(hadovec);
        add(menuHra);
        
        MenuRychlost menuRychlost = new MenuRychlost(hadovec);
        add(menuRychlost);
        
        MenuHerniMod menuMod = new MenuHerniMod(hadovec);
        add(menuMod);
        
        MenuHelp menuHelp = new MenuHelp(hadovec);
        add(menuHelp);
    }
}