package monster;

import java.util.Random;

import entity.Entity;
import main.Gamepanel;

public class MON_miniboss extends Entity {

    Gamepanel gp;

    public MON_miniboss(Gamepanel gp) {
        super(gp);
        this.gp = gp;

        type = 2;
        name = "miniboos";

        speed = 3;
        maxLife = 20;
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

        up1 = setup("/res/monster/dimon_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/dimon_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/dimon_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/dimon_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/dimon_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/dimon_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/dimon_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/dimon_right_2", gp.tileSize, gp.tileSize);

    }

    public void setActoin() {
        // Ai decision
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

   
    
    public void damageReaction() {
        actionLockCounter = 0;
        
        if (gp.player.direction.equals("up")) {
            direction = "down";
        } else if (gp.player.direction.equals("down")) {
            direction = "up" ; 
        } else if (gp.player.direction.equals("left")) {
            direction = "right";
        } else if (gp.player.direction.equals("right")) {
            direction = "left";
        } else {
            direction = "up";
        }
    }


}
