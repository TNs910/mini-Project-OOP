package monster;

import java.util.Random;

import entity.Entity;
import main.Gamepanel;

public class MON_LittleDragon extends Entity {

    Gamepanel gp;

    public MON_LittleDragon(Gamepanel gp) {
        super(gp);
        this.gp = gp;

        type = 2;
        name = "Little Dragon";

        speed = 1;
        maxLife = 8;
        attack = 1;
        life = maxLife;
           
            

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {

        up1 = setup("/res/monster/Dragon_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/Dragon_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/Dragon_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/Dragon_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/Dragon_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/Dragon_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/Dragon_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/Dragon_right2", gp.tileSize, gp.tileSize);

    }

    public void setActoin() {

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if (onPath == true) {
            checkStopchasingOrNot(gp.player, 15, 100);

            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);

        } else {
            if (tileDistance < 5) {

                int i = new Random().nextInt(100) + 1;
                if (i > 50) {
                    onPath = true;
                }
            }
            actionLockCounter++;

            if (actionLockCounter == 120) {
                Random random = new Random();

                int i = random.nextInt(100) + 1; // pick a number between 1 and 100

                if (i <= 25) {
                    direction = "up";

                }
                if (i > 25 && i <= 50) {
                    direction = "down";

                }
                if (i > 50 && i <= 75) {
                    direction = "left";

                }
                if (i > 75 && i <= 100) {
                    direction = "right";

                }

                actionLockCounter = 0;
            }
        }
    }

    // Ai decision

    public void damageReaction() {
        actionLockCounter = 0;

        if (gp.player.direction.equals("up")) {
            direction = "down";
        } else if (gp.player.direction.equals("down")) {
            direction = "up";
        } else if (gp.player.direction.equals("left")) {
            direction = "right";
        } else if (gp.player.direction.equals("right")) {
            direction = "left";
        } else {
            direction = "up";
        }
    }

}
