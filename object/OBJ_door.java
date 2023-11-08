package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_door extends Entity {


    public OBJ_door(Gamepanel gp) {
        super(gp);

        name = "doorDemon";
        down1 = setup("/res/object/door",gp.tileSize,gp.tileSize) ;
        collision = true ;

        solidArea.x = 0 ;
        solidArea.y = 16 ;
        solidArea.width = 48 ;
        solidArea.height = 32 ;
        solidAreaDefaultX = solidArea.x ;
        solidAreaDefaultY = solidArea.y ;
    }

}
