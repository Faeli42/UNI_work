/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_snake_multi;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mahdal
 */
public class SSSTest {
    
    public SSSTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testTurnLeft() {
        System.out.println("turnLeft");
        SSS instance = new SSS();
        instance.turnLeft();
        int expResult = -10;
        int result = instance.directionX;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTurnUp() {
        System.out.println("turnUp");
        SSS instance = new SSS();
        instance.turnUp();
        int expResult = 0;
        int result = instance.directionX;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        SSS instance = new SSS();
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }
}