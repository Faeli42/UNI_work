package java_snake_multi;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * @author mahdal (more info - run app - help)
 */
public class Snake implements KeyListener, Runnable {

    DataInputStream in;
    DataOutputStream out;
    ServerSocket serverSocket;
    Socket client, server;
    ConnectionAccept connectionAccept;
    SSS snake1, snake2, snake3;
    GUI guiii;
    int x = 0, y = 0, speed = 50, snakesAlive = 0;
    private int pocetHadu = 0;
    Point bonusFoodPoint = new Point();
    Thread vlakno;
    boolean bonusCheck = true, gameOver = false, zdi = false, isRunning;
    Random random = new Random(); // umoznuje nahodna cisla (alternativa k Math.random)

    //konstruktor
    public Snake(int setdelka, int setx, int sety) {
        setX(setx);
        setY(sety);
        setPocetHadu(setdelka);
        pocatecniHodnoty();
        guiii = new GUI(setdelka, setx, sety, this);
        createAllTheSnakes();
        client = null;
        server = null;
        serverSocket = null;
        vlakno = new Thread(this);
        vlakno.start();
    }

    public int getPocethadu() {
        return pocetHadu;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    private void setPocetHadu(int a) {
        pocetHadu = a;
    }

    public void setSpeed(int x) {
        speed = x;
    }

    //udelame ze hry server s moznosti prijeti klienta
    public boolean turnIntoServer() {
        if (pocetHadu != 2) {
            return false;
        }
        String str = JOptionPane.showInputDialog(null, "Zadej číslo portu na serveru : ", "Port", 3);
        if (str != null) {
            try {
                int port = Integer.parseInt(str);
                String address = InetAddress.getLocalHost().getHostAddress();
                JOptionPane.showMessageDialog(null, "Bude vytvořen server! (adresa: " + address + ", port: " + port + ")", "YES", 2);
                connectionAccept = new ConnectionAccept(port);
                serverSocket = new ServerSocket(port);
                connectionAccept.setServerSocket(serverSocket);
                connectionAccept.setHadovec(this);
                connectionAccept.start();
                this.pause();
                return true;
            } catch (NumberFormatException | HeadlessException | IOException e) {
                JOptionPane.showMessageDialog(null, "Tvorba serveru selhala", "FAILED", 1);
                return true;
            }
        } else {
            return true;
        }
    }

    //po pripojeni clienta na server spustime serverovou hru
    public void startServerGame(Socket newClient) {
        System.out.println("Got new client");
        client = newClient;
        try {
            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
        } catch (IOException ex) {
            client = null;
        }
        unPause();
    }

    //metoda clienta, -- pripojeni hry k serveru
    public void connectToServer() throws UnknownHostException, IOException {

        String host = JOptionPane.showInputDialog(null, "Zadejte adresu hostujiciho serveru : ", "Adresa", 3);
        if (host != null) {
            String str = JOptionPane.showInputDialog(null, "Zadej číslo portu na serveru : ", "Port", 3);
            try {
                int port = Integer.parseInt(str);
                server = new Socket(host, port);
                System.out.println("Connected to server");
                pause();
                guiii.vycistitHraciPlochu();
                guiii.connectedToServer();
                try {
                    out = new DataOutputStream(server.getOutputStream());
                    in = new DataInputStream(server.getInputStream());
                } catch (IOException ex) {
                    server = null;
                }
            } catch (NumberFormatException | HeadlessException | IOException e) {
                JOptionPane.showMessageDialog(null, "Pripojeni k serveru selhalo", "FAILED", 1);
            }
        }

    }

    //nastaveni pocatecnich podminek, 
    private void pocatecniHodnoty() {
        snake1 = new SSS(80, y / 2);
        if (pocetHadu >= 2) {
            snake2 = new SSS(80, 60);
        }
        if (pocetHadu >= 3) {
            snake3 = new SSS(80, y - 60);
        }
        snakesAlive = pocetHadu;
        gameOver = false;
        bonusCheck = true; //resi bug, bonusove jidlo se pricitalo ke score mockrat
    }

    //tvori hady podle zadani
    private void createAllTheSnakes() {
        guiii.createSnake(snake1, Color.RED, 3);
        if (pocetHadu >= 2) {
            guiii.createSnake(snake2, Color.GREEN, 3);
        }

        if (pocetHadu >= 3) {
            guiii.createSnake(snake3, Color.ORANGE, 3);
        }
    }

    //zapauzování
    public void pause() {
        vlakno.interrupt();
        isRunning = false;
    }

    // odpauzování
    public void unPause() {
        vlakno = new Thread(this);
        vlakno.start();
    }

    //vycisti hraci plochu a spusti novou hru
    public void reset() {
        pause();

        System.out.println("new game");
        pocatecniHodnoty();
        guiii.vycistitHraciPlochu();
        createAllTheSnakes();
        guiii.setAllTheTextAreas();

        unPause();
    }

    //HLAVNI METODA, posunuje hada dopredu ve smeru kam miri, vzdy o 1 krok
    public void moveForward(SSS snejk) {
        locateSnake(snejk);
        crashCheck(snejk);
        hadVenkuZRamuCheck(snejk);
        if (snejk.alive) {
            stepForward(snejk);
            hadNaselJidloCheck(snejk);
            hadNaselBonusCheck(snejk);
            if (snejk.food == false) {
                growAndAddNewFood(snejk);
            } else {
                snejk.had[snejk.delkaHada - 1].setBounds(
                        snejk.hadX[snejk.delkaHada - 1], snejk.hadY[snejk.delkaHada - 1], 10, 10);

            }
        }
        if (snejk.alive == false) {
            guiii.odstranJidloHada(snejk);
        }

        guiii.prekreslit();
    }

    //zjisti, kde je had
    public void locateSnake(SSS snejk) {
        for (int i = 0; i < snejk.delkaHada; i++) {
            snejk.lbp[i] = snejk.had[i].getLocation();
        }
    }

    //zjistuje jestli se nesrazili hadi
    public void crashCheck(SSS snejk) {
        for (int i = 1; i < snejk.delkaHada; i++) {
            if (snejk.lbp[0].equals(snejk.lbp[i])) {
                if (snejk.alive) {
                    hadDies(snejk);
                }
                break;
            }
        }
        if (pocetHadu >= 2) {
            crashOfTwoHadsCheck(snake1, snake2);
            crashOfTwoHadsCheck(snake2, snake1);
        }
        if (pocetHadu >= 3) {
            crashOfTwoHadsCheck(snake1, snake3);
            crashOfTwoHadsCheck(snake3, snake1);
            crashOfTwoHadsCheck(snake3, snake2);
            crashOfTwoHadsCheck(snake2, snake3);
        }
    }

    //kontroluj srazky dvou hadu
    public void crashOfTwoHadsCheck(SSS snejk1, SSS snejk2) {
        if (snejk1.alive) {
            for (int i = 0; i < snejk2.delkaHada - 1; i++) {
                if (snejk1.lbp[0] != null && snejk2.lbp[i] != null) {
                    double headX = snejk1.lbp[0].getX() + snejk1.directionX;
                    double headY = snejk1.lbp[0].getY() + snejk1.directionY;
                    if (headX == snejk2.lbp[i].getX() && headY == snejk2.lbp[i].getY()) {
                        hadDies(snejk1);
                        break;
                    }
                }
            }
        }
    }

    //had zemrel, provedeme potrebne zmeny
    public void hadDies(SSS snejk) {
        snejk.alive = false;
        snakesAlive--;
        System.out.println("zbyva " + snakesAlive + " zivych hadu");
        if (snakesAlive == 0) {
            gameOver();
        }
    }

    //nastal konec hry
    public void gameOver() {
        System.out.println("game over");
        guiii.gameOver();
        vlakno.interrupt();
        gameOver = true;
    }

    //posune hada
    public void stepForward(SSS snejk) {
        //premisti hlavu
        snejk.hadX[0] += snejk.directionX;
        snejk.hadY[0] += snejk.directionY;
        snejk.had[0].setBounds(snejk.hadX[0], snejk.hadY[0], 10, 10);

        //premisti ostatni pole hada o 1 dopredu
        for (int i = 1; i < snejk.delkaHada; i++) {
            snejk.had[i].setLocation(snejk.lbp[i - 1]);
        }
        snejk.turning = false;
    }

    //had nasel a snedl jidlo
    public void hadNaselJidloCheck(SSS snejk) {
        if (snejk.hadX[0] == snejk.hadX[snejk.delkaHada - 1] && snejk.hadY[0] == snejk.hadY[snejk.delkaHada - 1]) {
            snejk.food = false;
            snejk.score += 1;

            guiii.setAllTheTextAreas();
            if (snejk.score % 20 == 0 && bonusCheck == true && snejk.score > 0) {
                addBonusFood();
            }
        }
    }

    //had nasel a snedl bonusove jidlo (100 bodu)
    public void hadNaselBonusCheck(SSS snejk) {
        if (bonusCheck == false) {
            if (bonusFoodPoint.x <= snejk.hadX[0] && bonusFoodPoint.y <= snejk.hadY[0]
                    && bonusFoodPoint.x + 10 >= snejk.hadX[0] && bonusFoodPoint.y + 10 >= snejk.hadY[0]) {
                guiii.panel1.remove(guiii.bonusFood);
                snejk.score += 5;
                guiii.setAllTheTextAreas();
                bonusCheck = true;
            }
        }
    }

    //prida jidlo kdyz bylo snedeno
    public void addBonusFood() {
        guiii.panel1.add(guiii.bonusFood);
        guiii.bonusFood.setBounds((10 * random.nextInt(40)), (10 * random.nextInt(25)), 15, 15);
        bonusFoodPoint = guiii.bonusFood.getLocation();
        bonusCheck = false;
    }

    //pridame novou potravu, nebude na kraji
    public void growAndAddNewFood(SSS snejk) {
        snejk.had[snejk.delkaHada] = new JButton();
        snejk.had[snejk.delkaHada].setEnabled(false);
        guiii.panel1.add(snejk.had[snejk.delkaHada]);
        int a = 10 + (10 * random.nextInt((x - 20) / 10));
        int b = 10 + (10 * random.nextInt((y - 20) / 10));

        checkForFoodInSnake(snejk, a, b);
        snejk.hadX[snejk.delkaHada] = a;
        snejk.hadY[snejk.delkaHada] = b;
        snejk.had[snejk.delkaHada].setBounds(a, b, 10, 10);
        snejk.had[snejk.delkaHada].setBackground(snejk.had[0].getBackground());
        snejk.food = true;
        snejk.delkaHada++;
    }

    //kontroluje, aby se jidlo neobjevovalo v hadovi
    public void checkForFoodInSnake(SSS snejk, int a, int b) {
        for (int i = 0; i < snejk.delkaHada; i++) {
            if (snejk.hadX[i] == a && snejk.hadY[i] == b) {
                a = 10 + (10 * random.nextInt((x - 20) / 10));
                b = 10 + (10 * random.nextInt((y - 20) / 10));
                checkForFoodInSnake(snejk, a, b);
            }
        }
    }

    //pripad, kdyz had vyjede mimo
    public void hadVenkuZRamuCheck(SSS snejk) {
        if (zdi == true) {
            if ((snejk.lbp[0].getX() + snejk.directionX == x + 10 || snejk.lbp[0].getX() + snejk.directionX == -10
                    || snejk.lbp[0].getY() + snejk.directionY == y + 10 || snejk.lbp[0].getY() + snejk.directionY == -10)
                    && snejk.alive == true) {
                hadDies(snejk);
            }
        } else {
            if (snejk.hadX[0] == x) {
                snejk.hadX[0] = 0;
            } else if (snejk.hadX[0] == 0) {
                snejk.hadX[0] = x - 10;
            } else if (snejk.hadY[0] == y) {
                snejk.hadY[0] = 0;
            } else if (snejk.hadY[0] == 0) {
                snejk.hadY[0] = y - 10;
            }
        }
    }
    
    public void sendKeyCode(KeyEvent e) {
        try {
            out.write(e.getKeyCode());
        } catch (IOException ex) {
            server = null;
            System.out.println("Nepodarilo se zapsat keycode");
            try {
                out.close();
            } catch (IOException ex1) {
                System.out.println("Nepodarilo se zavrit OUTstream");
            }
        }
    }

    //obecne ovladani hada
    public void pressKeySnake(int keyCode, SSS snejk, int codeLeft, int codeUp, int codeRight, int codeDown) {
        if (snejk.turning == false && snejk.runLeft == true && keyCode == codeLeft) {
            snejk.turnLeft();
        }
        if (snejk.turning == false && snejk.runUp == true && keyCode == codeUp) {
            snejk.turnUp();
        }
        if (snejk.turning == false && snejk.runRight == true && keyCode == codeRight) {
            snejk.turnRight();
        }
        if (snejk.turning == false && snejk.runDown == true && keyCode == codeDown) {
            snejk.turnDown();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //jsem klient, poslu keycode
        if (server != null) {
            sendKeyCode(e);
            return;
        }
        pressKeySnake(e.getKeyCode(), snake1, 37, 38, 39, 40);
        if (pocetHadu >= 2) {
            pressKeySnake(e.getKeyCode(), snake2, 65, 87, 68, 83);
        }
        if (pocetHadu >= 3) {
            pressKeySnake(e.getKeyCode(), snake3, 74, 73, 76, 75);
        }
    }

    //musi obsahovat vsechny abstrakni metody tridy KeyListener
    @Override
    public void keyReleased(KeyEvent e) {
    }

    //musi obsahovat vsechny abstrakni metody tridy KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            //cyklus pohybu hadem
            if (gameOver != true) {
                moveForward(snake1);
                if (pocetHadu >= 2) {
                    moveForward(snake2);
                }
                if (pocetHadu == 3) {
                    moveForward(snake3);
                }
            }
            // jsem server, prijmu code a budu zatacet hadem
            if (client != null) {
                try {
                    if (in.available() > 0) {
                        int key = in.read();
                        pressKeySnake(key, snake2, 37, 38, 39, 40);
                    }
                } catch (IOException e) {
                    System.out.println(e);
                    client = null;
                    System.out.println("Nepodarilo se precist keycode");
                    try {
                        in.close();//TODO: vyhodit chybu, zavrit stream
                    } catch (IOException ex) {
                        System.out.println("Nepodarilo se zavrit INstream");
                    }
                }
            }
            try {
                //realizuje rychlost pomoci intervalu mezi kroky hada
                Thread.sleep(speed);
            } catch (InterruptedException ie) {
            }
        }
    }
}