package entity;

import java.util.Random;

import main.Gamepanel;

public class NPC_Fox extends Entity {

    public NPC_Fox(Gamepanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
       
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        
        getImage();
        setDialog();
    }

    public void getImage() {

        up1 = setup("/res/npc/Fox_up1",gp.tileSize,gp.tileSize);
        up2 = setup("/res/npc/Fox_up2",gp.tileSize,gp.tileSize);
        down1 = setup("/res/npc/Fox_down1",gp.tileSize,gp.tileSize);
        down2 = setup("/res/npc/Fox_down2",gp.tileSize,gp.tileSize);
        left1 = setup("/res/npc/Fox_left1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/npc/Fox_left2",gp.tileSize,gp.tileSize);
        right1 = setup("/res/npc/Fox_right1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/npc/Fox_right2",gp.tileSize,gp.tileSize);

    }

    public void setDialog(){
        
        dialog[0] = " Hi, I am a Foxxxxx.";
        dialog[1] = " If you want to save the world.";
        dialog[2] = " Find the keys. The keys have different \n colors. ";
        dialog[3] = " The color of the key can mean something. ";
        dialog[4] = " And let me warn you about one thing. ";
        dialog[5] = " Don't mess with red. ";
        dialog[6] = " Look for the gold color first. \n Then something else will follow. ";
        dialog[7] = " Good luck ! ";
    }
    public void setActoin() {

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

    public void speak(){
        
       super.speak();
    }
}


