package object;


import entity.Entity;
import main.Gamepanel;

public class OBJ_chest extends Entity {
     

    public OBJ_chest(Gamepanel gp){
        super(gp) ;
        name = "chest";
        down1 = setup("/res/object/chest",gp.tileSize,gp.tileSize) ;
       
    }
}
