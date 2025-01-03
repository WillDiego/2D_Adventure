package main;

import entity.Entity;
import entity.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //Debug
    boolean checkDrawTime = false;
    GamePanel gp;
    Entity entity;
    Main sound;

    public KeyHandler(Entity entity) {
        this.entity = entity;
    }

    public KeyHandler(GamePanel gp ) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
         upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            if (gp.soundOn) {
                gp.stopMusic();
                gp.soundOn = false;
            } else if (!gp.soundOn){
                gp.playMusic(5);
                gp.soundOn = true;
            }
        }
        if (code == KeyEvent.VK_SHIFT) {
            gp.sprint(6);
        }

        //DEBUG
        if (code == KeyEvent.VK_T) {
            if (!checkDrawTime) {
                checkDrawTime = true;

            }
            else if (checkDrawTime) {
                checkDrawTime = false;

            }
        }
//        if (code == KeyEvent.VK_UP) {
//
//            gp.zoomInOut(1);
//        }
//        if (code == KeyEvent.VK_DOWN) {
//
//            gp.zoomInOut(-1);
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            gp.sprint(4);
        }
    }
}
