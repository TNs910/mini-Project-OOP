package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Gamepanel;
import main.KeyHandler;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    // object
    int keyYL = 0;
    int keyDemon = 0;
    int keyBrone = 0;

    public Player(Gamepanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultvalues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultvalues() {
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 24;
        speed = 4;
        direction = "down";

        // Character stats
        maxLife = 8;
        life = maxLife; // 2 = 1 heart , 4 = 2 hearts , 6 = 3 hearts
        attack = 2;
    }

    public void setDefaultposition() {

        switch (gp.currentMap) {
            case 0:
                worldX = gp.tileSize * 24;
                worldY = gp.tileSize * 24;
                direction = "down";

                break;
            case 1:
                worldX = gp.tileSize * 24;
                worldY = gp.tileSize * 14;
                direction = "down";

                break;
        }
    }

    public void restoreLife() {
        life = maxLife;
        invincible = false;
    }

    public void getPlayerImage() {

        up1 = setup("/res/Player/urigaga_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/Player/urigaga_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/Player/urigaga_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/Player/urigaga_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/Player/urigaga_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/Player/urigaga_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/Player/urigaga_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/Player/urigaga_right_2", gp.tileSize, gp.tileSize);
        front = setup("/res/Player/Urigaga_front", gp.tileSize, gp.tileSize);

    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/res/Player/Attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/res/Player/Attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/res/Player/Attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/res/Player/Attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/res/Player/Attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/res/Player/Attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/res/Player/Attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/res/Player/Attack_right_2", gp.tileSize * 2, gp.tileSize);

    }

    public void update() {

        if (attacking == true) {
            attacking();
        }

        else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // check collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interractNPC(npcIndex);
            // monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonter(monsterIndex);

            // event collision
            gp.eHandler.checkEvent();

            // if collisionOn is false , สามารถเดินได้

            if (collisionOn == false && keyH.enterPressed == false) {

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
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        spriteCounter++; // for blinking

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 70) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (keyH.godModeOn == false) {
            if (life <= 0) {
                gp.gameState = gp.gameOverState;
                gp.ui.commandNum = -1;
                gp.playSE(12);
                gp.stopMusic();
            }
        }

    }

    public void pickUpObject(int i) {

        if (i != 999) {

            String objectName = gp.obj[gp.currentMap][i].name;

            switch (objectName) {
                case "keyYL":
                    gp.playSE(8);
                    keyYL++;
                    gp.obj[gp.currentMap][i] = null;
                    break;
                case "doorYL":
                    if (keyYL > 0) {
                        gp.playSE(2);
                        gp.obj[gp.currentMap][i] = null;
                        keyYL--;
                    }
                    break;
                case "keyDemon":
                    gp.playSE(8);
                    keyDemon++;
                    gp.obj[gp.currentMap][i] = null;
                    break;
                case "doorDemon":
                    if (keyDemon > 0) {
                        gp.playSE(2);
                        gp.obj[gp.currentMap][i] = null;
                        keyDemon--;
                    }
                    break;
                case "keyBrone":
                    gp.playSE(8);
                    keyBrone++;
                    gp.obj[gp.currentMap][i] = null;
                    break;
                case "doorBrone":
                    if (keyBrone > 0) {
                        gp.playSE(2);
                        gp.obj[gp.currentMap][i] = null;
                        keyBrone--;
                    }
                    break;
                case "honey":
                    gp.playSE(11);
                    gp.obj[gp.currentMap][i] = null;
                    gp.player.speed += 0.5;
                    gp.player.restoreLife();

                    break;
                case "goldApple":
                    gp.playSE(17);
                    gp.obj[gp.currentMap][i] = null;
                    gp.player.maxLife += 2;
                    gp.player.speed += 1;
                    gp.player.attack += 4;
                    gp.player.restoreLife();
                    break;
                case "mini Demon":
                    gp.playSE(18);
                    gp.obj[gp.currentMap][i] = null;
                    gp.endGame();
                    break;

            }
        }

    }

    public void interractNPC(int i) {

        if (gp.keyH.enterPressed == true) {

            if (i != 999) {
                gp.playSE(8);
                gp.gameState = gp.dialogState;
                gp.npc[gp.currentMap][i].speak();

            } else {
                gp.playSE(7);
                attacking = true;
            }
        }

    }

    public void contactMonter(int i) {
        if (i != 999) {
            if (invincible == false) {
                gp.playSE(6);
                life -= 1;
                invincible = true;
            }

        }

    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (gp.monster[gp.currentMap][i].invincible == false) {
                gp.playSE(5);
                gp.monster[gp.currentMap][i].life -= attack;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                }

            }

        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

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
                    tempScreenY = screenY - gp.tileSize;
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
                    tempScreenX = screenX - gp.tileSize;
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
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));

        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        // reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

}