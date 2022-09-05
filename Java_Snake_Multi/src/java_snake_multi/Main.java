package java_snake_multi;

/**
 * @author mahdal
 */
public class Main {

    public static void main(String[] args) {
        //podarilo se nacist?
        boolean success = false;
        //promenne pro určení vzhledu hry
        int decide = 0, x = 0, y = 0;
        try {
            decide = Integer.parseInt(args[0]);
            x = Integer.parseInt(args[1]);
            y = Integer.parseInt(args[2]);
            success = true;
        } catch (Exception e) {
            System.out.println("chyba v argumentu");
        }
        if (success == true) {
            System.out.println("spoustim program");
            if (decide == 1 || decide == 2 || decide == 3) {
                Snake had = new Snake(decide, x, y);
            } else {
                System.out.println("prvni argument musi byt 1 / 2 / 3");
            }
        }

    }
}