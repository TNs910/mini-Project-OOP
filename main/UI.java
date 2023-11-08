package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import entity.Entity;
import object.OBJ_Heart;

public class UI {

    Gamepanel gp;
    Graphics2D g2;
    Font maruMoica, purisaB;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    public boolean gameFinished = false;
    public String currentDialog = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0 = first screen , 1 = second screen

    double playTime ; 
    DecimalFormat dFormat = new DecimalFormat("#0.00") ; 

    public UI(Gamepanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/res/font/x12y16pxMaruMonica.ttf");
            maruMoica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // heart
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    } // constructor

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(maruMoica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        // title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // play state
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMonsterLife();

        }
        // pause state
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        // dialog state
        if (gp.gameState == gp.dialogState) {
            drawPlayerLife();
            drawDialogScreen();
        }
        // game over state
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        if(gp.gameState == gp.gameFinished){
            drawGameFinishedScreen();
        }

    }

    public void drawMonsterLife() {
        // Monster HP bar

        for (int i = 0; i < gp.monster[1].length; i++) {

            Entity monster = gp.monster[gp.currentMap][i];

            if (monster != null && monster.inCamera() == true) {
                if (monster.hpBarOn == true && monster.boss == false) {

                    double oneScale = (double) gp.tileSize / monster.maxLife;
                    double hpBarValue = oneScale * monster.life;
                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect(monster.getScreenX() - 1, monster.getScreenY() - 16, gp.tileSize + 2, 12);

                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(monster.getScreenX(), monster.getScreenY() - 15, (int) hpBarValue, 10);

                    monster.hpBarCounter++;

                    if (monster.hpBarCounter > 600) {
                        monster.hpBarCounter = 0;
                        monster.hpBarOn = false;
                    }
                } else if (monster.boss == true) {
                    
                    double oneScale = (double) gp.tileSize * 8 / monster.maxLife;
                    double hpBarValue = oneScale * monster.life;

                    int x = gp.screenWidth/2 - gp.tileSize *4 ; 
                    int y = gp.tileSize*10 ; 

                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect(x-1, y-1 , gp.tileSize*8 + 2, 22);

                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(x, y , (int) hpBarValue, 20);

                    g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
                    g2.setColor(Color.white);
                    g2.drawString(monster.name, x + 4, y - 10);

                }
            }
        }

    }

        public void drawPlayerLife() {

            // gp.player.life = 6 ;

            int x = gp.tileSize / 2;
            int y = gp.tileSize / 2;
            int i = 0;

            // max life
            while (i < gp.player.maxLife / 2) {
                g2.drawImage(heart_blank, x, y, null);
                i++;
                x += gp.tileSize;
            }

            // reset
            x = gp.tileSize / 2;
            y = gp.tileSize / 2;
            i = 0;
            // current life
            while (i < gp.player.life) {
                g2.drawImage(heart_half, x, y, null);
                i++;
                if (i < gp.player.life) {
                    g2.drawImage(heart_full, x, y, null);

                }
                i++;
                x += gp.tileSize;
            }

        }

    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            g2.setColor(new Color(70, 120, 80));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // Title name
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 98F));
            String text = "Jaunt of Urikaka";
            int x = getXforCenterText(text);
            int y = gp.tileSize * 3;
            // Shadow
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);

            // Main color
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            // Urigaga Image

            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2;

            g2.drawImage(gp.player.front, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "GAME START";
            x = getXforCenterText(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
 
            text = "QUIT";
            x = getXforCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

        }

    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenterText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);

    }

    public void drawDialogScreen() {
        // window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialog.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
        // g2.drawString(currentDialog, x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXforCenterText(String text) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;

        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));

        text = " GAME OVER";

        g2.setColor(Color.black);
        x = getXforCenterText(text);
        y = gp.tileSize * 4;

        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenterText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);

        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }

        // back menu
        text = "Quit";
        x = getXforCenterText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }

    }
    public void drawGameFinishedScreen(){
        
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;

        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));

        text = " YOU WIN ";

        g2.setColor(Color.YELLOW);
        x = getXforCenterText(text);
        y = gp.tileSize * 4;

        g2.drawString(text, x, y);
        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);
        
        g2.setFont(g2.getFont().deriveFont(50f));
        
        text = "Quit";
        x = getXforCenterText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);

        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }

       




        
        

    }
}
