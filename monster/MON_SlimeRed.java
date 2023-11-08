package monster;

import java.util.Random;

import entity.Entity;
import main.Gamepanel;

public class MON_SlimeRed extends Entity {

    Gamepanel gp;

    public MON_SlimeRed(Gamepanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Slime Red";

        
    
        speed = 1;
        maxLife = 4;
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

        up1 = setup("/res/monster/SlimeRed_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/SlimeRed_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/SlimeRed_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/SlimeRed_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/SlimeRed_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/SlimeRed_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/SlimeRed_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/SlimeRed_right2", gp.tileSize, gp.tileSize);

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
        direction = gp.player.direction;

    }

}
