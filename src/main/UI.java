package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    //declare font
    Font publicPixel_35, publicPixel_30, publicPixel_40B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;

        publicPixel_35 = new Font("PublicPixel", Font.PLAIN, 35);
        publicPixel_30 = new Font("PublicPixel", Font.PLAIN, 30);
        publicPixel_40B = new Font("PublicPixel", Font.PLAIN, 45);


        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;

    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2) {

        if (gameFinished) {

            g2.setFont(publicPixel_30);
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(25F));

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*2);
            g2.drawString(text, x, y);

            g2.setFont(publicPixel_40B);
            g2.setColor(Color.yellow);

            text = "CONGRATULATIONS!";
            //get length of congrats
            textLength = (int) (g2.getFontMetrics().getStringBounds(text, g2).getWidth());

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }
        else {
            g2.setFont(publicPixel_30);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/4, gp.tileSize, gp.tileSize, null);
            g2.drawString("x", 70, 56);
            g2.setFont(publicPixel_35);
            g2.drawString(" " + gp.player.hasKey, 73, 56);

            //MESSAGE
            if (messageOn) {

                g2.setFont(g2.getFont().deriveFont(13F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
