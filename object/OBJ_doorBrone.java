package object;



import entity.Entity;
import main.Gamepanel;

public class OBJ_doorBrone extends Entity {
    

    public OBJ_doorBrone(Gamepanel gp) {
        super(gp) ; 
        name = "doorBrone";
        down1 = setup("/res/object/Door brone",gp.tileSize,gp.tileSize) ;
        collision = true;
        solidArea.x = 0 ;
        solidArea.y = 16 ;
        solidArea.width = 48 ;
        solidArea.height = 32 ;
        solidAreaDefaultX = solidArea.x ;
        solidAreaDefaultY = solidArea.y ;
    }

}
