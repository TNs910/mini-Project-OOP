package monster;

import java.util.Random;

import entity.Entity;
import main.Gamepanel;


public class MON_Demon extends Entity {

    Gamepanel gp;

    public MON_Demon(Gamepanel gp) {
        super(gp);
        this.gp = gp;
        boss = true ; 
        type = 2;
        name = "Demon Lord";

        speed = 1;
        maxLife = 40;
        life = maxLife;
        attack = 2 ; 
      

        int size = gp.tileSize * 5 ; 
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48 ;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;

        getImage();
        getAttackImage();
    }

    public void getImage() {

        int i = 5 ;

        up1 = setup("/res/monster/Demon_up_1", gp.tileSize * i, gp.tileSize * i);
        up2 = setup("/res/monster/Demon_up_2", gp.tileSize * i, gp.tileSize * i);
        down1 = setup("/res/monster/Demon_down_1", gp.tileSize * i, gp.tileSize * i);
        down2 = setup("/res/monster/Demon_down_2", gp.tileSize * i, gp.tileSize * i);
        left1 = setup("/res/monster/Demon_left_1", gp.tileSize * i, gp.tileSize * i);
        left2 = setup("/res/monster/Demon_left_2", gp.tileSize * i, gp.tileSize * i);
        right1 = setup("/res/monster/Demon_right_1", gp.tileSize * i, gp.tileSize * i);
        right2 = setup("/res/monster/Demon_right_2", gp.tileSize * i, gp.tileSize * i);

    }

    public void getAttackImage() {
        
        int i = 5;

        attackUp1 = setup("/res/monster/Demon_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
        attackUp2 = setup("/res/monster/Demon_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
        attackDown1 = setup("/res/monster/Demon_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
        attackDown2 = setup("/res/monster/Demon_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
        attackLeft1 = setup("/res/monster/Demon_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
        attackLeft2 = setup("/res/monster/Demon_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
        attackRight1 = setup("/res/monster/Demon_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
        attackRight2 = setup("/res/monster/Demon_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);

    }
   
    
  

    public void setActoin() {
        // Ai decision
        
        if(getTileDistance(gp.player) < 10){
            moveTowardPlayer(60);
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
        

        if(attacking == false){
            checkAttackOrNot(60, gp.tileSize*20, gp.tileSize*5);
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


