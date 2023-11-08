package entity;

import java.util.Random;

import main.Gamepanel;

public class NPC_Slime extends Entity {

    public NPC_Slime(Gamepanel gp) {
        super(gp);
        
        direction = "down";
        speed = 1 ;
       
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        
        getImage();
        setDialog();
    }

    public void getImage() {

        up1 = setup("/res/npc/slime_up1",gp.tileSize,gp.tileSize);
        up2 = setup("/res/npc/slime_up2",gp.tileSize,gp.tileSize);
        down1 = setup("/res/npc/slime_down1",gp.tileSize,gp.tileSize);
        down2 = setup("/res/npc/slime_down2",gp.tileSize,gp.tileSize);
        left1 = setup("/res/npc/slime_left1",gp.tileSize,gp.tileSize);
        left2 = setup("/res/npc/slime_left2",gp.tileSize,gp.tileSize);
        right1 = setup("/res/npc/slime_right1",gp.tileSize,gp.tileSize);
        right2 = setup("/res/npc/slime_right2",gp.tileSize,gp.tileSize);

    }

    public void setDialog(){
        
        dialog[0] = " Hello, I am a slime.";
        dialog[1] = " Are you here to help save the world? ";
        dialog[2] = " I've heard that the Demon King must be \n somewhere in this world. ";
        dialog[3] = " Maybe it's behind some door, maybe... ";
        dialog[4] = " But I have some important  \n information to share. ";
        dialog[5] = " is its property. ";
        dialog[6] = " Good luck, brave one. ";
        
    }
    public void setActoin() {

        if(onPath == true){
            int goalCol = 25 ;
            int goalRow = 27; 

            searchPath(goalCol,goalRow);
        }
        else{
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

    public void speak(){
        
       super.speak();

       onPath = true ; 
    }
}


