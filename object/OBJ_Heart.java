package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Heart extends Entity {
    

    public OBJ_Heart(Gamepanel gp) {
       super(gp) ;
        
       name = "heart";
        image = setup("/res/object/heart_full",gp.tileSize,gp.tileSize);
        image2 = setup("/res/object/heart_half",gp.tileSize,gp.tileSize);
        image3 = setup("/res/object/heart_blank",gp.tileSize,gp.tileSize);


    }

}
