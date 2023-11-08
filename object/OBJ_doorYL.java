package object;



import entity.Entity;
import main.Gamepanel;

public class OBJ_doorYL extends Entity {
    

    public OBJ_doorYL(Gamepanel gp) {
        super(gp) ; 
        name = "doorYL";
        down1 = setup("/res/object/door_yellow",gp.tileSize,gp.tileSize) ;
        collision = true;
        solidArea.x = 0 ;
        solidArea.y = 16 ;
        solidArea.width = 48 ;
        solidArea.height = 32 ;
        solidAreaDefaultX = solidArea.x ;
        solidAreaDefaultY = solidArea.y ;
    }

}
