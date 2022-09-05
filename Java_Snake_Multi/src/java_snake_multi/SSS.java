package java_snake_multi;

import java.awt.Point;
import javax.swing.JButton;

/**
 * @author mahdal
 */
public class SSS {
    int x = 400, y = 250, delkaHada = 0, directionX = 0, directionY = 0, score = 0;
    int[] hadX = new int[300];
    int[] hadY = new int[300];
    JButton[] had = new JButton[x * y]; // pole tlacitek = clanku hada
    Point[] lbp = new Point[300];
    boolean runLeft = false, runRight = true, runUp = true, 
            runDown = true, alive=true, food = false, turning = false;

    public SSS() {
    }
    
    public SSS (int x, int y){
        delkaHada = 0;
        hadX[0] = x;
        hadY[0] = y;
        directionX = 10;
        directionY = 0;
        runLeft = false;
        runRight = false;
        runUp = true;
        runDown = true;
        score = 0;
        alive = true;
        food = false;
    }
    
     public void turnLeft() {
        directionX = -10; // posun doleva o 10 pixelu
        directionY = 0;
        // umoznime pristi zmenu smeru up/down
        runRight = false;
        runUp = true;
        runDown = true;
        turning = true;
    }

    public void turnRight() {
        directionX = +10; // posun doprava o 10 pixelu
        directionY = 0;
        // umoznime pristi zmenu smeru up/down
        runLeft = false;
        runUp = true;
        runDown = true;
        turning = true;
    }

    public void turnDown() {
        directionX = 0;
        directionY = +10; // posun nahoru o 10 pixelu
        // umoznime pristi zmenu smeru right/left
        runUp = false;
        runRight = true;
        runLeft = true;
        turning = true;
    }

    public void turnUp() {
        directionX = 0;
        directionY = -10; // posun dolu o 10 pixelu
        // umoznime pristi zmenu smeru right/left
        runDown = false;
        runRight = true;
        runLeft = true;
        turning = true;
    }
    
    public int getScore() {
        return score;
    }
}