package object;


import entity.Entity;
import main.Gamepanel;

public class OBJ_goldApple extends Entity {


    public OBJ_goldApple(Gamepanel gp) {
        
        super(gp) ;
        
        name = "goldApple";
        down1 = setup("/res/object/goldApple",gp.tileSize,gp.tileSize) ;

    }

}
