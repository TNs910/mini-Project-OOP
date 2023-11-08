package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Gamepanel;
import main.UtilityTool;

public class Entity {

    Gamepanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, front;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,
            attackRight2;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    String dialog[] = new String[20];
    public BufferedImage image, image2, image3;
    

    // STATE
    public int worldX, worldY;
    public int speed;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogIndex = 0;
    public boolean collision = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean boss;
    public boolean temp = false ;
    
    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int attack = 0;
    public int shootAvailableCounter = 0;

    // CHARACTER ATTRIBUTE
    public String name;
    public int type; // 0 = player , 1 = npc , 2 = monster
    // Character stats
    public int maxLife;
    public int life;
    public int maxspeed;
    public int maxattack;

    


    public Entity(Gamepanel gp) {

            this.gp = gp;
           
      
    }

    public int getScreenX() {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        return screenX;
    }

    public int getScreenY() {
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        return screenY;
    }

    public void setActoin() {
    }

    public void damageReaction() {

    }

    public int getCenterX() {
        int centerX = worldX + left1.getWidth() / 2;
        return centerX;
    }

    public int getCenterY() {
        int centerY = worldX + up1.getHeight() / 2;
        return centerY;

    }

    public int getXdistance(Entity target) {
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;

    }

    public int getYdistance(Entity target) {
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;

    }

    public int getTileDistance(Entity target) {
        int tileDistance = (getXdistance(target) + getYdistance(target));
        return tileDistance;
    }

    public int getGoalcol(Entity target) {
        int goalCol = (target.worldX + target.solidArea.x) / gp.tileSize;
        return goalCol;
    }

    public int getGoalRow(Entity target) {
        int goalRow = (target.worldY + target.solidArea.x) / gp.tileSize;
        return goalRow;
    }

    public void speak() {
        if (dialog[dialogIndex] == null) {
            dialogIndex = 0;

        }
        gp.ui.currentDialog = dialog[dialogIndex];
        dialogIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;

            case "down":
                direction = "up";
                break;

            case "left":
                direction = "right";
                break;

            case "right":
                direction = "left";
                break;

        }

    }

    public void checkCollision() {

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            damagePlayer(attack);
        }

    }

    public void moveTowardPlayer(int interval) {
        actionLockCounter++;

        if (actionLockCounter == interval) {
            if (getXdistance(gp.player) > getYdistance(gp.player)) {
                if (gp.player.getCenterX() < getCenterX()) {
                    direction = "left";
                } else {
                    direction = "right";
                }
            } else if (getXdistance(gp.player) < getYdistance(gp.player)) {
                if (gp.player.getCenterY() < getCenterY()) {
                    direction = "up";
                } else {
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }
    }

    public void update() {
        
        
        if (attacking == true) {
            attacking();
        }

        else {
            setActoin();
            checkCollision();
            

            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }

            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            if (invincible == true) {
                invincibleCounter++;
                if (invincibleCounter > 40) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }

        }

    }

    public void checkStopchasingOrNot(Entity target, int distance, int rate) {

        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }

        }

    }

    public void damagePlayer(int attack) {

        if (gp.player.invincible == false) {
            gp.playSE(6);

            int damage = attack;
            if (damage < 0) {
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
        }

    }

    public void attacking() {

        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // save current position
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust position and solid area
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            if (type == 2) {
                if (gp.cChecker.checkPlayer(this) == true) {
                    damagePlayer(attack);
                }

            } else {
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex);
            }
            // check monster collision

            // After checking , reset position and solid area
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }

    public void checkAttackOrNot(int rate, int straight, int holozontal) {

        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch (direction) {
            case "up":
                if (gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < holozontal) {
                    targetInRange = true;
                }
                break;

            case "down":
                if (gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < holozontal) {
                    targetInRange = true;
                }
                break;

            case "left":
                if (gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < holozontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < holozontal) {
                    targetInRange = true;
                }
                break;

        }

        if (targetInRange == true) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shootAvailableCounter = 0;
            }
        }
    }

    public boolean inCamera() {
        boolean inCamera = false;
        if (worldX + gp.tileSize * 5 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize * 5 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            inCamera = true;
        }
        return inCamera;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if (inCamera() == true) {

            int tempScreenX = getScreenX();
            int tempScreenY = getScreenY();

            switch (direction) {
                case "up":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                    }
                    if (attacking == true) {
                        tempScreenY = getScreenY() - up1.getHeight();
                        if (spriteNum == 1) {
                            image = attackUp1;
                        }
                        if (spriteNum == 2) {
                            image = attackUp2;
                        }
                    }
                    break;
                case "down":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = attackDown1;
                        }
                        if (spriteNum == 2) {
                            image = attackDown2;
                        }
                    }
                    break;
                case "left":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                    }
                    if (attacking == true) {
                        tempScreenX = getScreenX() - left1.getWidth();
                        if (spriteNum == 1) {
                            image = attackLeft1;
                        }
                        if (spriteNum == 2) {
                            image = attackLeft2;
                        }
                    }
                    break;
                case "right":
                    if (attacking == false) {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                    }
                    if (attacking == true) {
                        if (spriteNum == 1) {
                            image = attackRight1;
                        }
                        if (spriteNum == 2) {
                            image = attackRight2;
                        }
                    }
                    break;
            }

            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changAlpha(g2, 0.4F);
            }
            if (dying == true) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX, tempScreenY, null);
            changAlpha(g2, 1F);
        }

    }

    public void dyingAnimation(Graphics2D g2) {

        dyingCounter++;
        int i = 5;

        if (dyingCounter < i) {
            gp.playSE(10);
            changAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {
            changAlpha(g2, 1f);
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
            changAlpha(g2, 0f);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
            changAlpha(g2, 1f);
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
            changAlpha(g2, 0f);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
            changAlpha(g2, 1f);
        }
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
            changAlpha(g2, 0f);
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
            changAlpha(g2, 1f);
        }
        if (dyingCounter > i * 8) {

            dying = false;
            alive = false;
        }

    }

    public void changAlpha(Graphics2D g2, float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    public void setAttributes(int multiplier) {
        speed = this.maxspeed * multiplier;
        life = this.maxLife * multiplier;
        attack = this.maxattack * multiplier;
    }

    // public void updateAttributes(int map, int Life, int Speed, int Attack) {
    // if (map != gp.currentMap) { // ตรวจสอบว่า gp.currentMap ได้เปลี่ยนแปลงหรือไม่
    // switch (map) {
    // case 0:
    // // ปรับแต่ง life ตามที่คุณต้องการเมื่อ gp.currentMap เปลี่ยน
    // maxLife = Life; // เปลี่ยน life เป็นค่าตามต้องการ
    // speed = Speed ;
    // attack = Attack;
    // life = maxLife;
    // break;
    // case 1:
    // life = Life * 2; // เปลี่ยน life ตามต้องการ หรือปรับตามโลจิกของเกม
    // speed = Speed ;
    // attack = Attack;
    // life = maxLife;
    // break;

    // }
    // }
    // }

    public void searchPath(int goalCol, int goalRow) {

    }

    

}
