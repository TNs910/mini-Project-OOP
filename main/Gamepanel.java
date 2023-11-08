package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.SwingUtilities;
import java.awt.Window;

public class Gamepanel extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final String frame = null;
    // ตั้งค่าหน้าจอ
    final int originalTileSize = 16; // 16 *16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 * 48
    public final int maxScreencol = 16;
    public final int maxScreenrow = 12;
    public final int screenWidth = tileSize * maxScreencol;// 768 pixel
    public final int screenHeight = tileSize * maxScreenrow;// 576 pixel

    // WORLD SETTING
    public final int maxWorldcol = 50;
    public final int maxWorldrow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;
    public final int maxWorldWidth = tileSize * maxWorldcol;
    public final int maxWorldHeight = tileSize * maxWorldrow;

    // FPS
    int FPS = 60;
    // system
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound(); // sound effect
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    public Player player = new Player(this, keyH);
    Thread gameThread;

    // entity and object
    
    public Entity obj[][] = new Entity[maxMap][20];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][20];
    ArrayList<Entity> entityList = new ArrayList<Entity>();

    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int gameOverState = 4;
    public final int cutscenState = 5;
    public final int gameFinished = 6;

    public boolean bossBattleOn = false;

    public Gamepanel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        gameState = titleState;

    }

    public void retry() {

        switch (currentMap) {
            case 0:
                player.setDefaultposition();
                player.restoreLife();
                aSetter.setNPC();
                aSetter.setMonster();

                break;
            case 1:
                player.setDefaultposition();
                player.restoreLife();
                aSetter.setNPC();
                aSetter.setMonster();

                break;
            case 2:
                currentMap = 0;
                player.setDefaultposition();
                player.restoreLife();
                aSetter.setNPC();
                aSetter.setMonster();

                break;
        }

        // player.setDefaultposition();
        // player.restoreLife();
        // aSetter.setNPC();
        // aSetter.setMonster();

    }

    public void restart() {

        currentMap = 0;
        player.setDefaultvalues();
        player.setDefaultposition();
        player.restoreLife();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 1 second = 1000000000 nanosecond
        double delta = 0;
        long lasttime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lasttime) / drawInterval;
            timer += (currentTime - lasttime);
            lasttime = currentTime;

            if (delta >= 1) {
                // เพิ่มตัวละคร
                update();
                // วาดตัวละคร
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS : " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {
        if (gameState == playState) {
            // อัพเดทตัวละคร
            player.update();

            // NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
                        monster[currentMap][i].update();
                    }
                    if (monster[currentMap][i].alive == false) {
                        monster[currentMap][i] = null;
                    }

                }
            }

        }
        if (gameState == pauseState) {
            // do nothing
        }
        if (gameState == gameFinished) {
            ui.drawGameFinishedScreen();
            // gameThread = null ;
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        // debug
        long drawStart = 0;
        if (keyH.showDebugText == true) {
            drawStart = System.nanoTime();
        }

        // วาดฉากหลัง
        if (gameState == titleState) {

            ui.draw(g2);
            csManager.draw(g2);
            

        } else {
            // วาดแผนที่
            tileM.draw(g2);

            entityList.add(player);

            // Add to entityList
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            // Sort
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }

            });

        }
        // draw entitys
        for (int i = 0; i < entityList.size(); i++) {
            entityList.get(i).draw(g2);
        }
        // Empty entityList
        entityList.clear();

        // วาด UI
        ui.draw(g2);
        if (keyH.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);

            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX" + player.worldX, x, y);
            y += lineHeight;
            g2.drawString("WorldX" + player.worldY, x, y);
            y += lineHeight;
            g2.drawString("Col" + (player.worldX + player.solidArea.x) / tileSize, x, y);
            y += lineHeight;
            g2.drawString("Row" + (player.worldY + player.solidArea.y) / tileSize, x, y);
            y += lineHeight;
            g2.drawString("Draw Time :  " + passed, x, y);
            y += lineHeight;
            g2.drawString("GodMode" + keyH.godModeOn, x, y);
            y += lineHeight;
            g2.drawString("MAP" + currentMap, x, y);

        }

        g2.dispose();

    }

    // เล่นเพลง
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

    public void Exit() {
        Window frame = SwingUtilities.getWindowAncestor(this); // ดึงหน้าต่าง JFrame จาก JPanel ที่ปัจจุบัน
        if (frame != null) {
            frame.dispose(); // ปิดหน้าต่าง JFrame
        }
    }

    public void endGame() {

        // กำหนดสถานะของเกมว่าจบแล้ว
        gameState = gameFinished;
        stopMusic();

    }
}