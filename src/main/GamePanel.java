package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.Tile;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.security.PublicKey;

public class GamePanel extends JPanel implements  Runnable {

    public int checkerZoom = 0;
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public int tileSize = originalTileSize * scale; // 48x48 tile
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;
    public boolean soundOn = true;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //how fast the game updates
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; //10 = how many objects displayed at once (only 10 cause more = slow)


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();

        playMusic(5);
    }

    public void sprint(int newSpeed) {
        if (player.sprintAllowed)
        player.speed = newSpeed;

    }
//    public void zoomInOut(int i) {
//
//        if (checkerZoom > -27) {
//
//            checkerZoom +=i;
//            System.out.println(checkerZoom);
//
//            int oldWorldWidth = tileSize * maxWorldCol; //2400
//            tileSize += i;
//            int newWorldWidth = tileSize * maxWorldCol; //2350
//
//            player.speed = (double) newWorldWidth / 600;
//
//            double multiplier = (double) newWorldWidth / oldWorldWidth; //0.97916666
//
//            double newPlayerWorldX = player.worldX * multiplier;
//            double newPlayerWorldY = player.worldY * multiplier;
//
//            player.worldX = newPlayerWorldX;
//            player.worldY = newPlayerWorldY;
//        }
//        else if ((i == 1) && (checkerZoom == -27)) {
//            checkerZoom +=i;
//            System.out.println(checkerZoom);
//
//            int oldWorldWidth = tileSize * maxWorldCol; //2400
//            tileSize += i;
//            int newWorldWidth = tileSize * maxWorldCol; //2350
//
//            player.speed = (double) newWorldWidth / 600;
//
//            double multiplier = (double) newWorldWidth / oldWorldWidth; //0.97916666
//
//            double newPlayerWorldX = player.worldX * multiplier;
//            double newPlayerWorldY = player.worldY * multiplier;
//
//            player.worldX = newPlayerWorldX;
//            player.worldY = newPlayerWorldY;
//        }
//        else {
//
//        }
//    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    //LOWER DOWN MEANS THE FRONT
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //tile
        tileM.draw(g2);

        //object
        for (int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //player
        player.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose();
    }
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {

        music.stop();
    }
    public void playSE(int i) {

//        if (soundOn){
//            sound.stop();
//        }
        se.setFile(i);
        se.play();
//        if (soundOn) {
//            sound.setFile(5);
//            sound.play();
//        }
    }
}
