package object;


import entity.Entity;
import main.Gamepanel;

public class OBJ_honey extends Entity {


    public OBJ_honey(Gamepanel gp) {
        
        super(gp) ;
        
        name = "honey";
        down1 = setup("/res/object/honey",gp.tileSize,gp.tileSize) ;

    }

}
